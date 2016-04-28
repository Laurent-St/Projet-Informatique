package model.gameElements;

import java.awt.Point;
import java.awt.Rectangle;
import animation.Count;
import model.Game;
import model.map.Map;


public class Actor extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int damage;
	protected int health = 100;
	protected int maxHealth = 100;
	
	private Count animationCount;

	private double speed;
	private String movingState;
	private String orientation;

	public Actor(String imageUrl, double x, double y, int damage, int health, double speed, Game game, Rectangle hitbox) {
		super(x, y, imageUrl, hitbox, game);
		setDamage(damage);
		setMaxHealth(health);
		setHealth(health);
		this.setMoving("null");
		
		this.speed = speed;
		
		animationCount = new Count(4,90);
		setMoving("null");
		setOrientation("south");
		
	}
	
	public void setOrientation(String val) {
		this.orientation = val;
	}
	
	public String getOrientation() {
		return orientation;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		if (damage >= 0) {
			this.damage = damage;
		} else {
			this.damage = 0;
			throw new RuntimeException("D�g�ts n�gatifs: valeur mise � 0");
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		if(this.health >= getMaxHealth()) {
			this.health = getMaxHealth();
		} else if (this.health<=0) {
			this.health = 0;
			die();
		}
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		if (maxHealth >= 0) {
			this.maxHealth = maxHealth;
		} else {
			this.maxHealth = 0;
		}
	}
	
	public Point getFootPosition() {
		Point p = new Point();
		double x = getX()+getHitbox().getCenterX();
		double y = getY()+getHitbox().getMaxY();
		p.setLocation(x, y);
		return p;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public String getMovingState() {
		return movingState;
	}
	
	public void setMoving(String state) {
		this.movingState = state;
		if(state!="null") {
			setOrientation(state);
		}
	}
	
	public int getAnimationCount() {
		return this.animationCount.getCount();
	}
	
	public void launchAnimationCount(){
		animationCount.activeCountThread();
	}
	
	public void reloadAction(Game game) {
		launchAnimationCount();
		super.reloadAction(game);
	}
	
	public void basicAttack(Actor target) {
		target.setHealth(target.getHealth() - damage);
	}

	public void die() {
	} // m�thode abstraite qui va �tre impl�ment�e dans Player et Monster
	
	public void move() {
		String ms = getMovingState();
		if(ms!="null") {
			double speed = getSpeed();
			Map terrain = getGame().getCurrentMap();
			double newX = getXdouble();
			double newY = getYdouble();
			
			if(ms=="up") {
				newY-=speed;
			} else if (ms=="down") {
				newY+=speed;
			} else if(ms=="right") {
				newX+=speed;
			} else if (ms=="left") {
				newX-=speed;
			}
			if(terrain.isPositionWalkable(newX,newY,getHitbox())&& terrain.isPositionOccupied(newX, newY, this, true)==false){
				//System.out.print("Nouvelle position");
				this.setX(newX);
				this.setY(newY);
			}
		}
	}
}