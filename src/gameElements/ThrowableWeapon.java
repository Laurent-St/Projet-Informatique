package gameElements;

import java.awt.Rectangle;

import gui.level.LevelPanel;

public class ThrowableWeapon extends Weapon {

	public ThrowableWeapon(String name, int x, int y, int damage, String image_url, Rectangle hitbox, LevelPanel level,
			Player attachedPlayer) {
		super(name, x, y, damage, image_url, hitbox, level, attachedPlayer);
		// TODO Auto-generated constructor stub
	}
	
	public void attack(Projectile p) {
		getLevelPanel().setComponentZOrder(p, 0);
		p.activate();
	}
	
	

}
