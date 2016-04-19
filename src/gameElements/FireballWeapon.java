package gameElements;

import java.awt.Rectangle;

import gui.level.LevelPanel;

public class FireballWeapon extends ThrowableWeapon {
	
	private static Rectangle hitbox = new Rectangle(0,0,32,32);

	
	public FireballWeapon(LevelPanel levelPanel, Player attachedPlayer) {
		super("fireballs",hitbox,levelPanel,attachedPlayer);
	}

}
