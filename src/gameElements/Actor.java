package gameElements;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import animation.Count;
import animation.PlayerUpdater;
import gui.level.LevelPanel;

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
		super(x, y, name, hitbox, level);
		setName(name);
		setDamage(damage);
		setHealth(health);
		movingState = "null";
		
		this.speed = speed;
		Thread actorThread = new Thread(new PlayerUpdater(this));
		animationCount = new Count(4,100);
		setMoving("null");
		setOrientation("south");

		actorThread.start();
		
		
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
			throw new RuntimeException("Le nom doit �tre une cha�ne de caract�res");
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
			throw new RuntimeException("D�g�ts n�gatifs: valeur mise � 0");
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
	 * public void move(){ // a compl�ter! }
	 */
	public void basicAttack(Actor target) {
		target.setHealth(target.getHealth() - damage);
	}

	public void die() {
	} // m�thode abstraite qui va �tre impl�ment�e dans Player et Monster
	
	public void move() {
		String ms = getMovingState();
		double speed = getSpeed();
		if(ms=="up") {
			this.setY(this.getYdouble()-speed);
		} else if (ms=="down") {
			this.setY(this.getYdouble()+speed);
		} else if(ms=="right") {
			this.setX(this.getXdouble()+speed);
		} else if (ms=="left") {
			this.setX(this.getXdouble()-speed);
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