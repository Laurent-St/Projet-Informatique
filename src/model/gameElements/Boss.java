/*
 * Monstre de type boss
 */

package model.gameElements;

import java.awt.Rectangle;

import model.Game;
import model.PoisonRunnable;
import model.PoisonningObject;

public class Boss extends Monster implements PoisonningObject {

	
	private static final long serialVersionUID = 1L;
	private PoisonRunnable poisonRunnable;
	private transient Thread poisonThread;
	private boolean poisonThreadRunning = false;

	public Boss(double x, double y, int damage, int health, double speed, int xp, Game game) {
		super("src/model/gameElements/bunnyboss.png", x, y, damage, health, 0, speed, xp, game, new Rectangle(5,0,45,60));
		poisonThread=null;
		poisonRunnable = new PoisonRunnable(game,this);
		setActorSize(64);
	}
	
	public void fireBallAttack(){	
		
		//envoie une boule de feu dans chaque direction
		
		Fireball fb1= new Fireball(getX(), getY(), "up", getGame(), this);
		Fireball fb2= new Fireball(getX(), getY(), "down", getGame(), this);
		Fireball fb3= new Fireball(getX(), getY(), "left", getGame(), this);
		Fireball fb4= new Fireball(getX(), getY(), "right", getGame(), this);
		fb1.activate();
		fb2.activate();
		fb3.activate();
		fb4.activate();	
	}
	
	public void poisonAttack(){	
		
		//inflige du poison au joueur
		
		Player player = getGame().getPlayer();
		int range = 10;
		
		Rectangle testHitbox1 = new Rectangle(getX()+(int)getHitbox().getMinX()-range,
				getY()+(int)getHitbox().getMinY()-range,
				(int)getHitbox().getWidth()+range*2,
				(int)getHitbox().getHeight()+range*2);
		Rectangle testHitbox2 = new Rectangle(player.getX()+(int)player.getHitbox().getMinX(),
				player.getY()+(int)player.getHitbox().getMinY(),
				(int)player.getHitbox().getWidth(),
				(int)player.getHitbox().getHeight());
		
		//réutilise le code de la fonction envenom() de PoisonousTile
		if(testHitbox1.intersects(testHitbox2)) {
			System.out.print("Player empoisonné");
			if(poisonThread==null){
				setPoisonThreadRunning(true);
				poisonThread = new Thread(poisonRunnable);
				poisonThread.start();	
			}
			if (!poisonThreadRunning) {
				setPoisonThreadRunning(true);
				poisonThread = new Thread(poisonRunnable);
				poisonThread.start();
			}
		}
	}
	public void setPoisonThreadRunning(boolean isPoisonThreadRunning){
		poisonThreadRunning=isPoisonThreadRunning;
	}	
	
	public void die(){
		super.die();
		epicDrop();	// le Boss lache d'office un sabre laser
		if(getGame().getCurrentMap().getLevelNum()==10) {
			getGame().getCurrentMap().generateZombies(100);
		}
		getGame().getPlayer().gainXP(getXPReward());
	}
	
	public void epicDrop(){
		getGame().getCurrentMap().addCollectableObject(new Lightsaber("Lightsaber",getX(),getY(),getGame(),getGame().getPlayer()));
	}
	
}
