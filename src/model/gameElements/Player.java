/*
 * Classe qui g�re le comportement du joueur: sa collecte d'objet, ses attaques, son exp�rience, sa mort, ses
 * interactions avec les portes. La classe Player comporte un Thread.
 */

package model.gameElements;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import model.Game;
import model.graphicElements.DeadMessage;
import model.graphicElements.Door;
import model.graphicElements.LevelUP;
import model.graphicElements.Tile;
import view.level.StatsPanel;

public class Player extends Actor implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Inventory inventory;
	private int level=1;
	private int experience=0;
	private int experienceForLevel=30;
	private static double playerSpeed  = 1.8;
	private static Rectangle playerHitbox = new Rectangle(8,0,15,31);
	
	//le joueur peut �quiper 2 types d'arme simultan�ment
	private HandWeapon handWeapon;
	private ThrowableWeapon throwableWeapon;
	private StatsPanel statsPanel;

	public Player(int damage, int health, int mana, Game game) {
		super("src/model/gameElements/characterWarrior.png",40,30,damage,health, mana, playerSpeed, game, playerHitbox);
		inventory = new Inventory(5,this);
		setLevel(1);
		setDamage(damage);
		setMoving("null");
		setExperienceForLevel(50+getLevel()*20);
		
		Thread playerThread = new Thread(this);
		playerThread.start();	
	}

	public void reloadAction(Game game) {	//utilis� pour restaurer la sauvegarde
		Thread actorThread = new Thread(this);
		actorThread.start();
		if(getHandWeapon()!=null) {
			getHandWeapon().activateCountThread();
			getHandWeapon().reloadAction();
		}
		if(getThrowableWeapon()!=null) {
			getThrowableWeapon().reloadAction(getGame());
		}
		for(CollectableObject co : getInventory().getExistingContent()) {
			co.reloadAction(game);
		}
		super.reloadAction(game);
	}
	
	public void move(){
		
		//Celle-ci diff�re du move() des actors car le joueur peut prendre des d�gats de tuiles empoisonn�es et peut se t�l�porter d'un niveau � l'autre � travers les portes
		
		super.move();
		Point footPosition = getFootPosition();
		int x = (int)Math.floor(footPosition.getX()/20);
		int y = (int)Math.floor(footPosition.getY()/20);
		Tile currentTile=getGame().getCurrentMap().getTileAt(x,y);
		if (currentTile.getIsPoisonous()){	//d�tection de cases empoisonn�es
			currentTile.envenom();
		} else if(currentTile.canTeleportToLevel()) {	//m�canisme de t�l�portation apr�s avoir franchi une porte
			getGame().changeLevel(currentTile.getLinkedLevel());
		}
	}
	public void setStatsPanel(StatsPanel sp) {
		this.statsPanel = sp;
	}
	
	public void updateStatsPanel() {
		
		//Mise � jour de son panneau de statistiques
		
		if(statsPanel!=null) {
			statsPanel.update();
		}
	}

	public int getExperience() {
		return experience;
	}
	

	public void gainXP(int xpReward) {
		this.setExperience(getExperience()+xpReward);
	}

	public void setExperience(int experience) {
		
		//D�finition de l'exp�rience du joueur
		//Permet un appel r�cursif si plusieurs niveaux sont pass�s en une fois
		
		this.experience = experience;
		if(experience>=getMaxExperienceForLevel()) {
			int lastMXP = getMaxExperienceForLevel();
			levelup();
			setExperience(experience-lastMXP);
		}
		updateStatsPanel();
	}

	public int getMaxExperienceForLevel() {
		return this.experienceForLevel;
	}

	public void setExperienceForLevel(int xp) {
		this.experienceForLevel = xp;
	}
	
	public void setHealth(int h) {
		super.setHealth(h);
		updateStatsPanel();
	}
	
	public void setMaxHealth(int mh) {
		super.setMaxHealth(mh);
		updateStatsPanel();
	}

	
	public void setMana(int mana) {
		super.setMana(mana);
		updateStatsPanel();
	}
	
	public void setMaxMana(int maxMana) {
		super.setMaxMana(maxMana);
		updateStatsPanel();
	}

	public HandWeapon getHandWeapon() {
		return handWeapon;
	}
	
	public ThrowableWeapon getThrowableWeapon() {
		return throwableWeapon;
	}

	public void equipHandWeapon(HandWeapon weapon) {
		this.handWeapon = weapon;
	}
	public void equipThrowableWeapon(ThrowableWeapon weapon) {
		this.throwableWeapon = weapon;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public void setLevel(int level) {
		this.level = level;
		updateStatsPanel();
	}

	public void levelup() {
		setLevel(getLevel() + 1);
		setMaxHealth(getMaxHealth() + 10);
		setMaxMana(getMaxMana() + 10);
		setDamage(getDamage() + 5);
		setExperienceForLevel(50+getLevel()*20);
		updateStatsPanel();
		new LevelUP(getX(),getY()-32,getGame(),getGame().getCurrentMap());	//animation quand on monte d'un niveau
	}
	
	public int getLevel() {
		return this.level;
	}

	public void die() {
		//remarque: le joueur lache ses 2 armes quand il meurt, et il lache aussi tout le contenu de son inventaire �
		//des positions al�aoires autour de lui.
		this.setHealth(getMaxHealth());
		this.setMana(getMaxMana());
		this.setExperience(0);
		if(getHandWeapon()!=null) {
			getGame().getCurrentMap().addCollectableObject(this.getHandWeapon());
			getHandWeapon().setX(getXdouble()+5);
			getHandWeapon().setY(getYdouble()+5);
			handWeapon = null;
		}
		if(getThrowableWeapon()!=null) {
			getGame().getCurrentMap().addCollectableObject(this.getThrowableWeapon());
			getThrowableWeapon().setX(getXdouble()-5);
			getThrowableWeapon().setY(getYdouble()-5);
			throwableWeapon = null;
		}
		@SuppressWarnings("unchecked") //pour supprimer l'avertissement de la m�thode clone().
		ArrayList<CollectableObject> cos = (ArrayList<CollectableObject>) getInventory().getExistingContent().clone();
		for (int i=1;i<=cos.size();i++){
			getInventory().dropObject(i);
			CollectableObject co = cos.get(i-1);
			Random rand = new Random();
			int dx = rand.nextInt(20)-10;
			int dy = rand.nextInt(20)-10;
			co.setX(co.getXdouble()+dx);
			co.setY(co.getYdouble()+dy);
		}
		new DeadMessage(this.getX()-16,this.getY()-16,getGame(),getGame().getCurrentMap()); //animation
		getGame().getCurrentMap().tryToTeleport(this, new Point(40,30));
	}
	
	public void drinkPotion(Potion potion) {
		if (potion instanceof HealthPotion) {
			setHealth(getHealth() + potion.getValue());
			inventory.removeFromInventory(potion);
		} else if (potion instanceof ManaPotion) {
			setMana(getMana() + potion.getValue());
			inventory.removeFromInventory(potion);
		}
	}
	
	public void attackHand() {
		if (getHandWeapon()!=null){
			getHandWeapon().attack();
		}
	}
	
	public void attackSpecial() {
		if(getThrowableWeapon()!=null) {
			getThrowableWeapon().attack();
		}
	}
	
	public void tryInteraction() {

		//M�thode appel�e par le contr�leur lors de l'utilisation de la touche d'interaction
		
		collect();
		openDoors();
	}
	
	public void collect(){
		
		//Essaye de ramasser un objet sur la map
		
		CollectableObject res=getGame().getCurrentMap().detectAnObject(this.getX(),this.getY(), this.getHitbox());
		if (res!=null && inventory.isFull()==false){
			inventory.setInInventory(res);
			getGame().getCurrentMap().removeCollectableObject(res);
		}
	}
	
	public void openDoors() {
		
		//Essaye d'ouvrir la porte qui se trouve devant le joueur (si il s'agit d'une porte)
		
		Tile facingTile = null;
		Tile currentTile;
		Point footPosition = getFootPosition();
		int x = (int)Math.floor(footPosition.getX()/20);
		int y = (int)Math.floor(footPosition.getY()/20);
		
		currentTile = getGame().getCurrentMap().getTileAt(x, y);		
		if(getOrientation()=="up") {
			facingTile = getGame().getCurrentMap().getTileAt(x, y-1);
		} else if(getOrientation()=="down") {
			facingTile = getGame().getCurrentMap().getTileAt(x, y+1);
		} else if(getOrientation()=="left") {
			facingTile = getGame().getCurrentMap().getTileAt(x-1, y);
		} else if (getOrientation()=="right") {
			facingTile = getGame().getCurrentMap().getTileAt(x+1, y);
		}
		
		if(facingTile instanceof Door){
			Door door = (Door) facingTile;
			door.doorOpen();
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.move();
		}
	}
}