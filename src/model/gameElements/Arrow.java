package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Arrow extends Projectile {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int arrowDamage = 30;
	private static Rectangle arrowHitbox = new Rectangle(8,8,16,16);
	private static double speed = 8;
	
	public Arrow(double x, double y, String direction, Game game, Player attachedPlayer) {
		super("Arrow", x, y, arrowDamage, direction, speed, "src/model/gameElements/bow.png", arrowHitbox, game, attachedPlayer);
	}	
}
