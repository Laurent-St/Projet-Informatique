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
			inflictDirectDamage(getGame().getCurrentMap().getMonstersInRectangle(getX(), getY(), getHitbox()));
			super.attack();
		}
	}
	
	public void updatePosition() {
		Player p = getAttachedPlayer();
		if (p.getOrientation()=="left") {
			setX(p.getX()-10);
			setY(p.getY());
		} else if (getAttachedPlayer().getOrientation()=="right"){
			setX(p.getX()+10);
			setY(p.getY());
		} else if (getAttachedPlayer().getOrientation()=="down") {
			setX(p.getX());
			setY(p.getY()+25);
		} else if (getAttachedPlayer().getOrientation()=="up") {
			setX(p.getX());
			setY(p.getY()-20);
		}
	}
	
	public int getAnimationCount() {
		return attackAnimationCount.getCount();
	}
	
	public void inventorySelectAction() {
		HandWeapon currentWeapon = getAttachedPlayer().getHandWeapon();
		getAttachedPlayer().equipHandWeapon(this);
		getAttachedPlayer().getInventory().removeFromInventory(this);
		if(currentWeapon!=null) {
			getAttachedPlayer().getInventory().setInInventory(currentWeapon);
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
