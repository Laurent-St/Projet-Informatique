/*
 * Arme à distance qui crée des Fireball et les envoie (en redéfinissant la fonction attack() de ThrowableWeapon).
 */

package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class FireballWeapon extends ThrowableWeapon {
	
	private static final long serialVersionUID = 1L;
	private static Rectangle hitbox = new Rectangle(0,0,32,32);
	private static int damage = 20;
	private static int fireballMana = 20;

	
	public FireballWeapon(Game game, Actor attachedActor) {
		super("Fireballs",0, 0, damage, fireballMana, "src/model/gameElements/fireball.png", hitbox,game,attachedActor);
	}
	
	public void attack() {
		Actor a = getAttachedActor();
		Fireball f = new Fireball(0, 0, a.getOrientation(), getGame(), a);
		super.attack(f);
	}
	
}
