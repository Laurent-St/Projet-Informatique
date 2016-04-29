package model.gameElements;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import model.Game;
//import model.graphicElements.PoisonousTile;
import model.graphicElements.Tile;
import view.StatsPanel;

public class Player extends Actor implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Inventory inventory;
	private int level=1;
	private int mana;
	private int maxMana;
	private int experience=0;
	private int experienceForLevel=30;
	private static double playerSpeed  = 1.8;
	private static Rectangle playerHitbox = new Rectangle(8,0,15,31);
	
	private HandWeapon handWeapon;
	private ThrowableWeapon throwableWeapon;
	private StatsPanel statsPanel;

	public Player(int damage, int health, int mana, Game game) {
		super("src/model/gameElements/characterWarrior.png",40,30,damage,health, playerSpeed, game, playerHitbox);
		inventory = new Inventory(5,this);
		setLevel(1);
		setMaxMana(mana);
		setMana(mana);
		setDamage(damage);
		setMoving("null");
		setExperienceForLevel(50+getLevel()*20);
		
		Thread actorThread = new Thread(this);
		actorThread.start();	
		System.out.println("new Player");
		
		//EPEE PAR DEFAUT
		Sword sword = new Sword("epee",0,0,getGame(), this);
		equipHandWeapon(sword);
		
		//FIREBALL PAR DEFAUT
		FireballWeapon fw = new FireballWeapon(getGame(), this);
		equipThrowableWeapon(fw);
	}

	public void reloadAction(Game game) {
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
		super.move();
		Point footPosition = getFootPosition();
		int x = (int)Math.floor(footPosition.getX()/20);
		int y = (int)Math.floor(footPosition.getY()/20);
		Tile currentTile=getGame().getCurrentMap().getTileAt(x,y);
		if (currentTile.getIsPoisonous()){
			currentTile.envenom();
		} else if(currentTile.canTeleportToLevel()) {
			getGame().changeLevel(currentTile.getLinkedLevel());
		}
	}
	public void setStatsPanel(StatsPanel sp) {
		this.statsPanel = sp;
	}
	
	public void updateStatsPanel() {
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

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
		if(this.mana>getMaxMana()) {
			this.mana = getMaxMana();
		} else if(this.mana<0) {
			this.mana = 0;
		}
		updateStatsPanel();
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
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
		// unlockNewAbility(); DEBLOCAGE DE SORTS ET TALENTS
	}
	
	public int getLevel() {
		return this.level;
	}

	public void die() {
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
		ArrayList<CollectableObject> cos = (ArrayList<CollectableObject>) getInventory().getExistingContent().clone();
		for (CollectableObject object: cos){
			getInventory().dropObject(object);
			Random rand = new Random();
			int dx = rand.nextInt(20)-10;
			int dy = rand.nextInt(20)-10;
			object.setX(object.getXdouble()+dx);
			object.setY(object.getYdouble()+dy);
		}
		this.setX(40);
		this.setY(30);
	}
	
	public void drinkPotion(Potion potion) {
		if (potion instanceof HealthPotion) {
			setHealth(getHealth() + potion.getValue());
			inventory.removeFromInventory(potion);
		} else if (potion instanceof ManaPotion) {
			setMana(mana + potion.getValue());
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
		collect();
		openDoors();
	}
	
	public void collect(){
		CollectableObject res=getGame().getCurrentMap().detectAnObject(this.getX(),this.getY(), this.getHitbox());
		if (res!=null && inventory.isFull()==false){
			inventory.setInInventory(res);
			getGame().getCurrentMap().removeCollectableObject(res);
		}
	}
	
	public void openDoors() {
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
		
		if(facingTile!=null){facingTile.doorOpen();};
		currentTile.doorOpen();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.move();
		}
	}
}