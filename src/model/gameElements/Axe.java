package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Axe extends HandWeapon {
	
	private static Rectangle axeHitBox = new Rectangle(0,0,20,20);
	
	public Axe(String name, int x, int y, int damage, int range, String image_url, Game game, Player attachedPlayer) {
		super(name, x, y, damage, range, image_url, axeHitBox, game, attachedPlayer);
	}
}
