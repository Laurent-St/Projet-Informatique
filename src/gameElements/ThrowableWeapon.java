package gameElements;

import java.awt.Rectangle;

import gui.level.LevelPanel;

public class ThrowableWeapon extends Weapon {
	
	public ThrowableWeapon(String name, Rectangle hitbox, LevelPanel level, Player attachedPlayer) {
		super(name, 0, 0, 20, "src/gameElements/fireball.png", hitbox,
			level, attachedPlayer);
	}
	
	

}
