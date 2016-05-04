package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Fireball extends Projectile {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int fireballDamage = 80;
	private static Rectangle fireballHitbox = new Rectangle(7,5,16,26);
	private static double speed = 5;
	
	public Fireball(double x, double y, String direction, Game game, Actor a) {
		super("Fireball", x, y, fireballDamage, direction, speed, "src/model/gameElements/fireball.png", fireballHitbox, game, a);
	}	
}
