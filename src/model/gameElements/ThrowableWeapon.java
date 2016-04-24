package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class ThrowableWeapon extends Weapon {

	public ThrowableWeapon(String name, int x, int y, int damage, int manaConsumption, String image_url, Rectangle hitbox, Game game,
			Player attachedPlayer) {
		super(name, x, y, damage, manaConsumption, image_url, hitbox, game, attachedPlayer);
		// TODO Auto-generated constructor stub
	}
	
	public void attack(Projectile p) {
		if(enoughMana()) {
			p.activate();
			super.attack();
		}
	}
	
	public void inventorySelectAction() {
		ThrowableWeapon currentWeapon = getAttachedPlayer().getThrowableWeapon();
		getAttachedPlayer().equipThrowableWeapon(this);
		getAttachedPlayer().getInventory().removeFromInventory(this);
		if(currentWeapon!=null) {
			getAttachedPlayer().getInventory().setInInventory(currentWeapon);
		}
	}
	
	

}
