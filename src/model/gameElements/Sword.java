package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Sword extends HandWeapon {
	
	private static String imageUrl = "src/model/gameElements/epee.png";
	private static Rectangle hitbox = new Rectangle(0,0,32,32);
	private static int damage = 10;
	private static int range = 2;

	public Sword(String name, int x, int y, Game game, Player attachedPlayer) {
		super(name, x, y, damage, range, imageUrl, hitbox, game, attachedPlayer);
		// TODO Auto-generated constructor stub
	}

}
