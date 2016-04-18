package gameElements;

import java.awt.Rectangle;

import gui.level.LevelPanel;

public class Sword extends Weapon {
	
	private static String imageUrl = "src/gameElements/epee.png";
	private static Rectangle hitbox = new Rectangle(0,0,32,32);
	private static int damage = 10;
	private static int range = 2;

	public Sword(String name, int x, int y, LevelPanel level, Player attachedPlayer) {
		super(name, x, y, damage, range, imageUrl, hitbox, level, attachedPlayer);
		// TODO Auto-generated constructor stub
	}

}
