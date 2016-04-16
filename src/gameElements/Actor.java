package gameElements;

import java.awt.Graphics;
import java.awt.Rectangle;

import animation.Count;
import gui.level.LevelPanel;
import levels.LevelData;

public class Actor extends GameObject {
	protected String name;
	protected int damage;
	protected int health;
	protected int maxHealth;
	
	private Count animationCount;

	private double speed;
	private String movingState;
	private String orientation;

	public Actor(String name, double x, double y, int damage, int health, double speed, LevelPanel level, Rectangle hitbox) {
		super(x, y, name, hitbox, level);		//le name est "image_url" de GameObject
		setName(name);
		setDamage(damage);
		setHealth(health);
		movingState = "null";
		
		this.speed = speed;
		
		animationCount = new Count(4,100);
		setMoving("null");
		setOrientation("south");
		
		
	}

	public String getName() {
		return name;
	}
	
	public void setOrientation(String val) {
		this.orientation = val;
	}
	
	public String getOrientation() {
		return orientation;
	}

	public void setName(String name) {
		if (name instanceof String) {
			this.name = name;
		} else {
			throw new RuntimeException("Le nom doit être une chaîne de caractères");
		}
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		if (damage >= 0) {
			this.damage = damage;
		} else {
			this.damage = 0;
			throw new RuntimeException("Dégâts négatifs: valeur mise à 0");
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health >= 0) {
			this.health = health;
		} else {
			this.health = 0;
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

	/*
	 * public void move(){ // a compléter! }
	 */
	public void basicAttack(Actor target) {
		target.setHealth(target.getHealth() - damage);
	}

	public void die() {
	} // méthode abstraite qui va être implémentée dans Player et Monster
	
	public void move() {
		String ms = getMovingState();
		if(ms!="null") {
			double speed = getSpeed();
			LevelData terrain = getLevel().getLevelData();
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
			if(terrain.isPositionAvailable(newX,newY,getHitbox())) {
				this.setX(newX);
				this.setY(newY);
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		
		getLevel().repaint();
		int xcount = 0;
		if(getMovingState()!="null") {
			xcount = animationCount.getCount();
		}
		int ycount = 0;
		if(getOrientation()=="up") {
			ycount = 2;
		} else if (getOrientation()=="left") {
			ycount = 1;
		} else if (getOrientation()=="right") {
			ycount = 3;
		}
		//g.drawImage(this.getImage(), getX(), getY(), getX()+32, getY()+32,
		//		32*xcount, ycount*32, 32*xcount+32, ycount*32+32,
		//		null);
		g.drawImage(this.getImage(), 0, 0, 31, 31,
				32*xcount, ycount*32, 32*xcount+32, ycount*32+32,
				null);
	}

}