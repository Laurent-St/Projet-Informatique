package model.gameElements;

import java.awt.Rectangle;
import java.util.ArrayList;

import animation.Count;
import model.Game;
import model.map.Map;
import view.level.LevelPanel;


public class Actor extends GameObject {
	protected String name;
	protected int damage;
	protected int health;
	protected int maxHealth;
	
	private Count animationCount;

	private double speed;
	private String movingState;
	private String orientation;

	public Actor(String name, double x, double y, int damage, int health, double speed, Game game, Rectangle hitbox) {
		super(x, y, name, hitbox, game);		//le name est "image_url" de GameObject
		setName(name);
		setDamage(damage);
		setHealth(health);
		movingState = "null";
		
		this.speed = speed;
		
		animationCount = new Count(4,90);
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
			throw new RuntimeException("Le nom doit ï¿½tre une chaï¿½ne de caractï¿½res");
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
			throw new RuntimeException("Dï¿½gï¿½ts nï¿½gatifs: valeur mise ï¿½ 0");
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
	
	public int getAnimationCount() {
		return this.animationCount.getCount();
	}

	
	public void basicAttack(Actor target) {
		target.setHealth(target.getHealth() - damage);
	}

	public void die() {
	} // mï¿½thode abstraite qui va ï¿½tre implï¿½mentï¿½e dans Player et Monster
	
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
			if(terrain.isPositionAvailable(newX,newY,getHitbox())&& isPositionOccupied(newX, newY, getHitbox())==false){
				//System.out.print("Nouvelle position");
				this.setX(newX);
				this.setY(newY);
			}
		}
	}
	
	public boolean isPositionOccupied(double x, double y, Rectangle hitbox){
		ArrayList<Monster> monsters=new ArrayList<Monster>();
		monsters=getGame().getCurrentMap().getMonsters();
		boolean res=false;  //default result is a free position
		Rectangle testHitbox=new Rectangle((int)x,(int)y, (int)hitbox.getWidth(), (int)hitbox.getHeight());
		int i=0;
		while(i<monsters.size() && res==false){
			System.out.println("While fonctionne");
			if (testHitbox.intersects(monsters.get(i).getHitbox())){
				res=true;
				//System.out.println("Position occupée");
			}
			i++;
		}
		return res;
	}
}