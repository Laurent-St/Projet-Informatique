/*
 * Classe abstraite
 * Les projectiles sont lancés quand le Player utilise sa Throwable Weapon. Ils implémentent l'interface Runnable
 * Ils contiennent un Thread qui gère le déplacement du projectile
 */

package model.gameElements;

import java.awt.Rectangle;
import java.util.ArrayList;

import model.Game;

public abstract class Projectile extends Weapon implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private transient Thread movingWeaponThread;
	private String direction;
	private double speed;
	private boolean travelling;
	private boolean dead;
	private boolean threadSuspended = false;
	
	public Projectile(String name, double x, double y, int damage, String direction, double speed, String image_url, Rectangle hitbox, Game game, Actor a) {
		super(name,  x,  y,  damage, 0,  image_url,  hitbox, game,  a);
		this.direction = direction;
		this.speed = speed;
		this.travelling = true;
		this.dead = false;
	}
	
	public void activate() {
		
		//Méthode appelée lors de l'utilisation de l'arme du joueur afin de "lancer" le projectile
		
		threadSuspended = false;
		//la position de départ du projectile est celle de l'Actor qui le lance (le Player ou le Boss)
		double x = getAttachedActor().getXdouble()+getAttachedActor().getHitbox().getCenterX()-getHitbox().getCenterX();
		double y = getAttachedActor().getYdouble()+getAttachedActor().getHitbox().getCenterY()-getHitbox().getCenterY();
		this.setX(x);
		this.setY(y);
		getGame().getCurrentMap().addProjectile(this);
		
		//Lancement du thread de déplacement du projectile
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
		//Déplacement du projectile en vérifiant s'il touche des Actors ou des murs (la fonction de détection d'Actor 
		//est getActorsInRectangle, définie dans Map
		ArrayList<Actor> hitActors = new ArrayList<Actor>();
		while(true) {
			if(threadSuspended==false) {
				hitActors = getGame().getCurrentMap().getActorsInRectangle(getX(), getY(), getHitbox(), getAttachedActor());
				if(getGame().getCurrentMap().isPositionWalkable(getX(), getY(), getHitbox()) 
				&& hitActors.size()==0) {
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
					//Code executé lors de la détection de collisions
					explode(hitActors);
					this.threadSuspended = true;
				}
			}
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void explode(ArrayList<Actor> hitActors) {
		
		//Méthode appelée lors de l'impact du projectile, inflige des dommages aux acteurs spécifiés en paramtère
		
		inflictDirectDamage(hitActors);
		this.travelling = false;
		this.dead = true;
		getGame().getCurrentMap().removeProjectile(this);
	}
	
	public void attack() {}; //Afin de ne pas appeler la méthode attack() du parent

	
	//Méthodes utiles lors du changement de map
	public void interruptThread() {
		threadSuspended = true;
	}
	
	public void resumeThread() {
		threadSuspended = false;
	}
}
