package model.gameElements;

import java.awt.Graphics;
import java.awt.Rectangle;

import model.Game;

public class FireballWeapon extends ThrowableWeapon {
	
	private static Rectangle hitbox = new Rectangle(-16,-16,32,32);
	private static int damage = 20;

	
	public FireballWeapon(Game game, Player attachedPlayer) {
		super("Fireballs",0, 0, damage, "src/model/gameElements/fireball.png", hitbox,game,attachedPlayer);
	}
	
	public void attack() {
		Player p = getAttachedPlayer();
		Rectangle ph = p.getHitbox();
		Fireball f = new Fireball(0, 0, p.getOrientation(), getGame(), p);
		super.attack(f);
	}
	
	protected void paintComponent(Graphics g) {
		
	}

}
