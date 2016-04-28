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
	
	public Projectile(String name, double x, double y, int damage, String direction, double speed, String image_url, Rectangle hitbox, Game game, Player attachedPlayer) {
		super(name,  x,  y,  damage, 0,  image_url,  hitbox, game,  attachedPlayer);
		getGame().getCurrentMap().addProjectile(this);
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
	public void activateProjectileThread(){	//sera aussi utilisé pour la restauration de sauvegarde
		movingWeaponThread=new Thread(this);
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
		while(getGame().getCurrentMap().isPositionWalkable(getX(), getY(), getHitbox()) 
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
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hitMonsters = getGame().getCurrentMap().getMonstersInRectangle(getX(), getY(), getHitbox());
		}
		
		explode(hitMonsters);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		delete();
		
	}
	
	public void explode(ArrayList<Monster> hitMonsters) {
		inflictDirectDamage(hitMonsters);
		System.out.println("exploded");
		this.travelling = false;
	}
	
	public void attack() {
		
	}

	private void delete() {
		this.dead = true;
		getGame().getCurrentMap().removeProjectile(this);
	}

	public void interruptThread() {
		movingWeaponThread.suspend();
	}
	
	public void resumeThread() {
		movingWeaponThread.resume();
	}

}
