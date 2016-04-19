package gameElements;

import java.awt.Rectangle;

import gui.level.LevelPanel;

public class Axe extends HandWeapon {
	
	private static Rectangle axeHitBox = new Rectangle(0,0,20,20);
	
	public Axe(String name, int x, int y, int damage, int range, String image_url, LevelPanel level, Player attachedPlayer) {
		super(name, x, y, damage, range, image_url, axeHitBox, level, attachedPlayer);
	}
}
