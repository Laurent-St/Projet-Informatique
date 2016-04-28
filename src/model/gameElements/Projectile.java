package model.gameElements;

import java.awt.Rectangle;
import java.util.ArrayList;

import model.Game;

public class Projectile extends Weapon implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Thread movingWeaponThread;
	private String direction;
	private double speed;
	private boolean travelling;
	private boolean dead;
	private boolean threadSuspended = false;
	
	public Projectile(String name, double x, double y, int damage, String direction, double speed, String image_url, Rectangle hitbox, Game game, Player attachedPlayer) {
		super(name,  x,  y,  damage, 0,  image_url,  hitbox, game,  attachedPlayer);
		this.direction = direction;
		this.speed = speed;
		this.travelling = true;
		this.dead = false;
	}
	
	public void activate() {
		threadSuspended = false;
		double x = getAttachedPlayer().getXdouble()+getAttachedPlayer().getHitbox().getCenterX()-getHitbox().getCenterX();
		double y = getAttachedPlayer().getYdouble()+getAttachedPlayer().getHitbox().getCenterY()-getHitbox().getCenterY();
		this.setX(x);
		this.setY(y);
		getGame().getCurrentMap().addProjectile(this);
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
		ArrayList<Monster> hitMonsters = new ArrayList<Monster>();
		while(true) {
			if(threadSuspended==false) {
				hitMonsters = getGame().getCurrentMap().getMonstersInRectangle(getX(), getY(), getHitbox());
				if(getGame().getCurrentMap().isPositionWalkable(getX(), getY(), getHitbox()) 
				&& hitMonsters.size()==0) {
					if(this.direction=="up") {
						setY(getYdouble()-this.speed);
					} else if (this.direction=="down") {
						setY(getYdouble()+this.speed);
					} else if (this.direction=="right") {
						setX(getXdouble()+this.speed);
					} else if (this.direction=="left") {
						setX(getXdouble()-this.speed);
					}
				} else {
					explode(hitMonsters);
					this.threadSuspended = true;
				}
			}
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public void explode(ArrayList<Monster> hitMonsters) {
		inflictDirectDamage(hitMonsters);
		System.out.println("exploded");
		this.travelling = false;
		this.dead = true;
		getGame().getCurrentMap().removeProjectile(this);
	}
	
	public void attack() {
		
	}

	public void interruptThread() {
		threadSuspended = true;
	}
	
	public void resumeThread() {
		threadSuspended = false;
	}
}
