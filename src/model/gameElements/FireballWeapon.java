package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class FireballWeapon extends ThrowableWeapon {
	
	private static Rectangle hitbox = new Rectangle(-16,-16,32,32);
	private static int damage = 20;
	private static int fireballMana = 20;

	
	public FireballWeapon(Game game, Player attachedPlayer) {
		super("Fireballs",0, 0, damage, fireballMana, "src/model/gameElements/fireball.png", hitbox,game,attachedPlayer);
	}
	
	public void attack() {
		if(enoughMana()) {
			Player p = getAttachedPlayer();
			Fireball f = new Fireball(0, 0, p.getOrientation(), getGame(), p);
			super.attack(f);
		}
	}
	
}
