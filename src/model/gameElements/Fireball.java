package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Fireball extends Projectile {
	
	private static int damage = 20;
	private static Rectangle hitbox = new Rectangle(7,5,16,26);
	private static double speed = 5;
	
	public Fireball(double x, double y, String direction, Game game, Player attachedPlayer) {
		super("Fireball", x, y, damage, direction, speed, "src/model/gameElements/fireball.png", hitbox, game, attachedPlayer);
	}
	
	public void attack() {
		this.setX(this.getXdouble()-16);
		this.setY(this.getYdouble()-16);
		super.attack();
	}
	
}
