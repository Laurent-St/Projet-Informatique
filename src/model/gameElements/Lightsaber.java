/*
 * Arme surpuissante (Lightsaber)
 */

package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Lightsaber extends HandWeapon {

	private static final long serialVersionUID = 1L;
	private static String imageUrl = "src/model/gameElements/lightsaber.png";
	private static Rectangle hitbox = new Rectangle(0,0,32,32);
	private static int damage = 50;
	private static int range = 4;
	private static int manaLightsaber = 0;

	public Lightsaber(String name, int x, int y, Game game, Player attachedPlayer) {
		super(name, x, y, damage, manaLightsaber, range, imageUrl, hitbox, game, attachedPlayer);
		
	}

}
