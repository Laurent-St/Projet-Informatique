package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Axe extends HandWeapon {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Rectangle axeHitBox = new Rectangle(0,0,32,32);
	private static String imageUrl = "src/model/gameElements/axe.png";
	private static int damage = 10;
	private static int range = 2;
	private static int manaAxe = 0;
	
	public Axe(String name, int x, int y, Game game, Player attachedPlayer) {
		super(name, x, y, damage, manaAxe, range, imageUrl, axeHitBox, game, attachedPlayer);
		//setX(x);
		//setY(y);
	}
}
