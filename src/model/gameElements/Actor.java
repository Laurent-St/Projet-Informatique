/*
 * Classe reprenant le joueur et les monstres. C'est ici que la fonction de d�placement move() est g�r�e.
 */

package model.gameElements;

import java.awt.Point;
import java.awt.Rectangle;
import animation.Count;
import model.Game;
import model.graphicElements.Slash;
import model.map.Map;

public abstract class Actor extends GameObject {

	private static final long serialVersionUID = 1L;
	private int damage;
	private int health;
	private int maxHealth;
	private int mana;
	private int maxMana;
	
	private Count animationCount;

	private double speed;
	private String movingState;	//direction dans laquelle l'Actor se d�place
	private String orientation; //direction dans laquelle se trouve l'image de l'Actor
	private int actorSize = 32;

	public Actor(String imageUrl, double x, double y, int damage, int health, int mana, double speed, Game game, Rectangle hitbox) {
		super(x, y, imageUrl, hitbox, game);
		setDamage(damage);
		setMaxHealth(health);
		setHealth(health);
		setMaxMana(mana);
		setMana(mana);
		this.setMoving("null");
		
		this.speed = speed;
		
		animationCount = new Count(4,90);
		setMoving("null");
		setOrientation("south");	//choix arbitraire
		
	}
	
	public int getActorSize() {
		return this.actorSize;
	}
	
	public void setActorSize(int size) {
		this.actorSize = size;
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
			throw new RuntimeException("Degats negatifs, valeur mise � 0");
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		int lastHealth = getHealth();
		this.health = health;
		if(this.health > getMaxHealth()) {
			this.health = getMaxHealth();
		} else if (this.health<=0) {
			this.health = 0;
			die();
		}
		
		if(lastHealth>getHealth()) {
			new Slash(getX(),getY(),getGame(),getGame().getCurrentMap());	//animation quand l'Actor subit des d�g�ts
		}
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void setMana(int mana) {
		this.mana = mana;
		if(this.mana>getMaxMana()) {
			this.mana = getMaxMana();
		} else if(this.mana<0) {
			this.mana = 0;
		}
	}
	
	public int getMana() {
		return this.mana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	
	public Point getFootPosition() {	//position des pieds de l'actor, utile pour la d�tection de case empoisonn�es.
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
		
		//D�fini la direction de d�placement de l'Actor et met � jour son orientation corporelle
		
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
	
	public void reloadAction(Game game) {	//utilis� pour la restauration de la sauvegarde
		launchAnimationCount();
		super.reloadAction(game);
	}
	
	public void basicAttack(Actor target) {
		
		//Inflige des d�gats directs � l'acteur sp�cifi�
		
		target.setHealth(target.getHealth() - getDamage());
	}

	public abstract void die();
	
	public void move() {
		
		//Est appel� par le thread g�rant l'Actor
		//L'actor bouge dans sa direction � condition que la position atteinte soit libre
		
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
			if(terrain.isPositionWalkable(newX,newY,getHitbox())&& terrain.isPositionOccupied(newX, newY, this)==false){
				this.setX(newX);
				this.setY(newY);
			}
		}
	}
}