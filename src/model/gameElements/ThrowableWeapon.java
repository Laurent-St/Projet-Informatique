package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class ThrowableWeapon extends Weapon {

	public ThrowableWeapon(String name, int x, int y, int damage, String image_url, Rectangle hitbox, Game game,
			Player attachedPlayer) {
		super(name, x, y, damage, image_url, hitbox, game, attachedPlayer);
		// TODO Auto-generated constructor stub
	}
	
	public void attack(Projectile p) {
		p.activate();
	}
	
	

}
