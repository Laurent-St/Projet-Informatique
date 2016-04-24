package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Projectile extends Weapon implements Runnable{
	
	private Thread movingWeaponThread;
	private String direction;
	private double speed;
	private boolean travelling;
	private boolean dead;
	
	public Projectile(String name, double x, double y, int damage, String direction, double speed, String image_url, Rectangle hitbox, Game game, Player attachedPlayer) {
		super(name,  x,  y,  damage,  image_url,  hitbox, game,  attachedPlayer);
		getGame().addProjectile(this);
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
	
	public String getDirection() {
		return this.direction;
	}

	@Override
	public void run() {
		while(getGame().getCurrentMap().isPositionWalkable(getX(), getY(), getHitbox()) 
				&& getAttachedPlayer().isPositionOccupied(getX(), getY(), this, false)==false) {
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
		getGame().removeProjectile(this);
	}

}
