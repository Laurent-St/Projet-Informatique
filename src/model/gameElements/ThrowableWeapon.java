package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class ThrowableWeapon extends Weapon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ThrowableWeapon(String name, int x, int y, int damage, int manaConsumption, String image_url, Rectangle hitbox, Game game,
			Actor attachedActor) {
		super(name, x, y, damage, manaConsumption, image_url, hitbox, game, attachedActor);
		// TODO Auto-generated constructor stub
	}
	
	public void attack(Projectile p) {
		if(enoughMana()) {
			p.activate();
			super.attack();
		}
	}
	
	public void inventorySelectAction() {
		if(getAttachedActor() instanceof Player) {
			Player p = (Player) getAttachedActor();
			ThrowableWeapon currentWeapon = p.getThrowableWeapon();
			p.equipThrowableWeapon(this);
			p.getInventory().removeFromInventory(this);
			if(currentWeapon!=null) {
				p.getInventory().setInInventory(currentWeapon);
			}
		}
	}
	
	

}
