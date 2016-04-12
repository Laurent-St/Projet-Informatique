package gameElements;

import java.awt.Dimension;
import java.awt.Graphics;

import animation.Count;
import animation.PlayerUpdater;
import gui.level.LevelPanel;

public class Actor extends GameObject {
	protected String name;
	protected int damage;
	protected int health;
	protected int maxHealth;
	private Dimension hitbox;
	
	private Count animationCount;

	private int speed;
	private String movingState;
	private String orientation;

	public Actor(String name, int x, int y, int damage, int health, int speed, LevelPanel level, Dimension hitbox) {
		super(x, y, name, level);
		setName(name);
		setDamage(damage);
		setHealth(health);
		setActorHitbox(hitbox);
		movingState = "null";
		
		this.speed = speed;
		Thread playerThread = new Thread(new PlayerUpdater(this));
		animationCount = new Count(4,100);
		setMoving("null");
		setOrientation("south");

		playerThread.start();
	}

	public String getName() {
		return name;
	}
	
	public void setActorHitbox(Dimension d) {
		this.hitbox = d;
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
	
	public int getSpeed() {
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
		int speed = getSpeed();
		if(ms=="up") {
			this.setY(this.getY()-speed);
		} else if (ms=="down") {
			this.setY(this.getY()+speed);
		} else if(ms=="right") {
			this.setX(this.getX()+speed);
		} else if (ms=="left") {
			this.setX(this.getX()-speed);
		}
	}
	
	protected void paintComponent(Graphics g) {
		
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
		g.drawImage(this.getImage(), getX(), getY(), getX()+32, getY()+32,
				32*xcount, ycount*32, 32*xcount+32, ycount*32+32,
				null);
	}

}