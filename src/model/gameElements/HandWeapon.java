package model.gameElements;

import java.awt.Rectangle;

import animation.CountTimer;
import animation.CountTimerListener;
import model.Game;

public class HandWeapon extends Weapon implements CountTimerListener  {
	
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
			super.attack();
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
		if (ck.getCount()==4) {
			ck.stop();
			ck.setCount(4);
		}
	}
}
