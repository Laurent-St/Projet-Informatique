package model.gameElements;

import java.awt.Rectangle;

import animation.CountTimer;
import animation.CountTimerListener;
import model.Game;

public class HandWeapon extends Weapon implements CountTimerListener  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int range;
	private CountTimer attackAnimationCount;
	
	public HandWeapon(String name, int x, int y, int damage, int manaConsumption, int range, String image_url, Rectangle hitbox,
			Game game, Player attachedPlayer) {
		super(name, x, y, damage, manaConsumption, image_url, hitbox, game, attachedPlayer);
		
		setAttackRange(range);
		attackAnimationCount = new CountTimer(5,1000,this);
		attackAnimationCount.stop();
		attackAnimationCount.setCount(4);
	}
	
	public void activateCountThread(){	//utilisé pour la sérialisation
		attackAnimationCount.activeCountThread();
	}
	
	public void setAttackRange(int range) {
		if (range < 1) {
			throw new RuntimeException("Portée négative");
		} else {
			this.range = range;
		}
	}
	
	public int getRange() {
		return range;
	}
	
	public void attack() {
		if(enoughMana()) {
			attackAnimationCount = null;
			attackAnimationCount = new CountTimer(5,50,this);
			updatePosition();
			int range = getRange();
			Rectangle hitboxWithRange = new Rectangle(-range,-range,(int)getHitbox().getWidth()+2*range,(int)getHitbox().getHeight()+2*range);
			inflictDirectDamage(getGame().getCurrentMap().getActorsInRectangle(getX(), getY(), hitboxWithRange, getAttachedActor()));
			super.attack();
		}
	}
	
	public void updatePosition() {
		Actor a = getAttachedActor();
		if (a.getOrientation()=="left") {
			setX(a.getX()-10);
			setY(a.getY());
		} else if (getAttachedActor().getOrientation()=="right"){
			setX(a.getX()+10);
			setY(a.getY());
		} else if (getAttachedActor().getOrientation()=="down") {
			setX(a.getX());
			setY(a.getY()+25);
		} else if (getAttachedActor().getOrientation()=="up") {
			setX(a.getX());
			setY(a.getY()-20);
		}
	}
	
	public int getAnimationCount() {
		return attackAnimationCount.getCount();
	}
	
	public void inventorySelectAction() {
		if(getAttachedActor() instanceof Player) {
			Player p = (Player) getAttachedActor();
			HandWeapon currentWeapon = p.getHandWeapon();
			p.equipHandWeapon(this);
			p.getInventory().removeFromInventory(this);
			if(currentWeapon!=null) {
				p.getInventory().setInInventory(currentWeapon);
			}
		}
	}

	@Override
	public void atCount(CountTimer ck) {
		updatePosition();
		if (ck.getCount()==4) {
			ck.stop();
			ck.setCount(4);
		}
	}

	public void reloadAction() {
		attackAnimationCount = new CountTimer(5,1000,this);
		attackAnimationCount.stop();
		attackAnimationCount.setCount(4);
		super.reloadAction(getGame());
	}
}
