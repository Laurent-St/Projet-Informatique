package model.gameElements;

import java.awt.Graphics;
import java.awt.Rectangle;

import view.level.LevelPanel;

public class Fireball extends Projectile {
	
	private static int damage = 20;
	private static Rectangle hitbox = new Rectangle(3,3,27,27);
	private static double speed = 5;
	
	public Fireball(double x, double y, String direction, LevelPanel level, Player attachedPlayer) {
		super("Fireball", x, y, damage, direction, speed, "src/model.gameElements/fireball.png", hitbox, level, attachedPlayer);
	}
	
	protected void paintComponent(Graphics g) {
		if(isTravelling() || isDead()) {
			super.paintComponent(g);
		} else {
			g.drawImage(getImage(), 0, 0, 63,63,0,32,63,95,null);
		}
	}
	
	public void attack() {
		this.setX(this.getXdouble()-16);
		this.setY(this.getYdouble()-16);
		super.attack();
	}
	
}
