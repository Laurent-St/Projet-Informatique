package model.gameElements;

import java.awt.Graphics;
import java.awt.Rectangle;

import view.level.LevelPanel;
import model.Game;

public class Projectile extends Weapon implements Runnable{
	
	private Thread movingWeaponThread;
	private String direction;
	private double speed;
	private boolean travelling;
	private boolean dead;
	
	public Projectile(String name, double x, double y, int damage, String direction, double speed, String image_url, Rectangle hitbox, Game game, Player attachedPlayer) {
		super(name,  x,  y,  damage,  image_url,  hitbox, game,  attachedPlayer);
		this.direction = direction;
		this.speed = speed;
		this.travelling = true;
		this.dead = false;
		
	}
	
	public void activate() {
		double x = getAttachedPlayer().getXdouble()+getAttachedPlayer().getHitbox().getCenterX()-getHitbox().getCenterX();
		double y = getAttachedPlayer().getYdouble()+getAttachedPlayer().getHitbox().getCenterY()-getHitbox().getCenterY();
		this.setX(x);
		this.setY(y);
		movingWeaponThread = new Thread(this);
		movingWeaponThread.start();
	}
	
	public boolean isTravelling() {
		return this.travelling;
	}
	
	public boolean isDead() {
		return this.dead;
	}

	@Override
	public void run() {
		//RAJOUTER && getAttachedPlayer().isPositionOccupied(getX(), getY(), getHitbox())
		while(getLevelPanel().isPositionAvailable(getX(), getY(), getHitbox()) ) {
			if(this.direction=="up") {
				setY(getYdouble()-this.speed);
			} else if (this.direction=="down") {
				setY(getYdouble()+this.speed);
			} else if (this.direction=="right") {
				setX(getXdouble()+this.speed);
			} else if (this.direction=="left") {
				setX(getXdouble()-this.speed);
			}
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		attack();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		delete();
		
	}
	
	public void attack() {
		System.out.println("exploded");
		this.travelling = false;
	}

	private void delete() {
		this.dead = true;
	}
	
//	protected void paintComponent(Graphics g) {
//		if(isTravelling()) {
//			if(this.direction=="up") {
//				g.drawImage(getImage(), 0, 0, 31,31,0,0,31,31,null);
//			} else if (this.direction=="right") {
//				g.drawImage(getImage(), 0, 0, 31,31,32,0,63,31,null);
//			} else if (this.direction=="down") {
//				g.drawImage(getImage(), 0, 0, 31,31,64,0,95,31,null);
//			} else if (this.direction=="left") {
//				g.drawImage(getImage(), 0, 0, 31,31,96,0,127,31,null);
//			}
//		}
//	}

}