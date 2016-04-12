package gameElements;

import java.awt.Dimension;

import animation.CharacterAnimation;
import gui.level.LevelPanel;

public class Actor extends GameObject {
	protected String name;
	protected int damage;
	protected int health;
	protected int maxHealth;
	private Dimension hitbox;

	private int speed;
	private String movingState;

	public Actor(String name, int x, int y, int damage, int health, int speed, LevelPanel level, Dimension hitbox) {
		super(x, y, name, level);
		setName(name);
		setDamage(damage);
		setHealth(health);
		setActorHitbox(hitbox);
		
		this.speed = speed;
		setMoving("null");
		Thread animationThread = new Thread(new CharacterAnimation(this));
		animationThread.start();
	}

	public String getName() {
		return name;
	}
	
	public void setActorHitbox(Dimension d) {
		this.hitbox = d;
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
	
	public int getSpeed() {
		return this.speed;
	}
	
	public String getMovingState() {
		return movingState;
	}
	
	public void setMoving(String state) {
		this.movingState = state;
		System.out.println(state);
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

}