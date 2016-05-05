/*
 * Arme à distance, permet d'envoyer plusieurs projectiles
 * Celle-ci ne s'affiche jamais sur le terrain (sauf dans l'inventaire)
 */

package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class ThrowableWeapon extends Weapon {

	private static final long serialVersionUID = 1L;

	public ThrowableWeapon(String name, int x, int y, int damage, int manaConsumption, String image_url, Rectangle hitbox, Game game,
			Actor attachedActor) {
		super(name, x, y, damage, manaConsumption, image_url, hitbox, game, attachedActor);
	}
	
	public void attack(Projectile p) {
		
		//Fonction invoquée lors de l'utilisation de l'arme: Lance un projctile (à différencier selon le type de l'arme à distance)
		
		if(enoughMana()) {
			p.activate();	//active le Thread du projectile
			super.attack();
		}
	}
	
	public void inventorySelectAction() {
		//Enlève l'arme d'une des 5 cases de l'inventaire et la place dans la case d'utilisation d'arme à distance
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
