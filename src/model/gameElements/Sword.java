/*
 * Arme de main de type épée
 */

package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Sword extends HandWeapon {
	
	private static final long serialVersionUID = 1L;
	private static String imageUrl = "src/model/gameElements/epee.png";
	private static Rectangle hitbox = new Rectangle(0,0,32,32);
	private static int damage = 20;
	private static int range = 2;
	private static int manaSword = 0;

	public Sword(String name, int x, int y, Game game, Player attachedPlayer) {
		super(name, x, y, damage, manaSword, range, imageUrl, hitbox, game, attachedPlayer);
		// TODO Auto-generated constructor stub
	}

}
