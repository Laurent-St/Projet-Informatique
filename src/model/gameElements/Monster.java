package model.gameElements;

import java.awt.Rectangle;
import model.Game;

public class Monster extends Actor {

	private static final long serialVersionUID = 1L;
	private int count;
	private int xpReward;
	private boolean dead;

	public Monster(String name, double x, double y, int damage, int health, int mana, double speed, int xp, Game game, Rectangle hitbox) {
		super(name, x, y, damage, health, mana, speed, game, hitbox);
		this.xpReward = xp;
		count = 0;
		dead = false;

	}
	
	public void die() {
		dead = true;
	}
	
	public void randomDrop() {
		//A REDEFINIR
	}
	
	public void tryAttack() {
		Player player = getGame().getPlayer();
		int range = 10;
		
		Rectangle testHitbox1 = new Rectangle(getX()+(int)getHitbox().getMinX()-range,
				getY()+(int)getHitbox().getMinY()-range,
				(int)getHitbox().getWidth()+range*2,
				(int)getHitbox().getHeight()+range*2);
		Rectangle testHitbox2 = new Rectangle(player.getX()+(int)player.getHitbox().getMinX(),
				player.getY()+(int)player.getHitbox().getMinY(),
				(int)player.getHitbox().getWidth(),
				(int)player.getHitbox().getHeight());
		if(testHitbox1.intersects(testHitbox2)) {
			System.out.print("Player tabassé");
			basicAttack(player);
		}
				
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	public boolean isDead() {
		return dead;
	}
	
	public int getXPReward() {
		return this.xpReward;
	}
}
