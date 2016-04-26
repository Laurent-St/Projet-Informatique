package model.gameElements;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import model.Game;
//import model.graphicElements.PoisonousTile;
import model.graphicElements.Tile;
import view.StatsPanel;

public class Player extends Actor implements Runnable{
	private Inventory inventory;
	private int level=1;
	private int mana=50;
	private int maxMana=100;
	private int experience=0;
	private int maxExperienceForLevel=100;
	
	private static double playerSpeed  = 1.5;
	private static Rectangle playerHitbox = new Rectangle(8,0,15,31);
	
	private HandWeapon handWeapon;
	private ThrowableWeapon throwableWeapon;
	private StatsPanel statsPanel;

	public Player(int damage, int health, int mana, Game game) {
		super("src/model/gameElements/characterWarrior.png",50,50,10,100, playerSpeed, game, playerHitbox);
		inventory = new Inventory(5,this);
		setLevel(1);
		setMana(100);
		maxMana = 100;

		setMoving("null");
		
		Thread actorThread = new Thread(this);
		actorThread.start();	
		System.out.println("new Player");
		
		//TEST DE l'EPEE
		Sword sword = new Sword("epee",20,20,this.getGame(), this);
		equipHandWeapon(sword);
		
		//TEST DE LA FIREBALL
		FireballWeapon fw = new FireballWeapon(getGame(), this);
		equipThrowableWeapon(fw);
	}
	
	public void move(){
		super.move();
		Point footPosition = getFootPosition();
		int x = (int)Math.floor(footPosition.getX()/20);
		int y = (int)Math.floor(footPosition.getY()/20);
		Tile currentTile=getGame().getCurrentMap().getTileAt(x,y);
		//if (currentTile instanceof PoisonousTile){
		if (currentTile.getIsPoisonous()){
//			PoisonousTile poisonousTile=(PoisonousTile)currentTile;
//			poisonousTile.startPoisonThread();
			currentTile.envenom();
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

	public void setExperience(int experience) {
		if (experience < 0) {
			throw new RuntimeException("Exp�rience n�gative");
		} else if (this.experience + experience >= maxExperienceForLevel) {
			levelup();
			setExperience(this.experience + experience - maxExperienceForLevel);
		} else {
			this.experience = experience;
		}
	}

	public int getMaxExperienceForLevel() {
		return 100;
	}

	public void setMaxExperienceForLevel(int maxExperienceForLevel) {
		if (maxExperienceForLevel < 0) {
			throw new RuntimeException("Exp�rience maximale n�gative");
		} else {
			this.maxExperienceForLevel = maxExperienceForLevel;
			updateStatsPanel();
		}
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
		if (mana < 0) {
			throw new RuntimeException("Valeur de mana n�gative");
		} else {
			this.mana = mana;
			if(this.mana>getMaxMana()) {
				this.mana = getMaxMana();
			}
			updateStatsPanel();
		}
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		if (maxMana < 0) {
			throw new RuntimeException("Valeur de mana maximale n�gative");
		} else {
			this.maxMana = maxMana;
			updateStatsPanel();
		}
	}

	public HandWeapon getHandWeapon() {
		return handWeapon;
	}
	
	public ThrowableWeapon getThrowableWeapon() {
		return throwableWeapon;
	}

	public void equipHandWeapon(HandWeapon weapon) {
		this.handWeapon = weapon;
		
		setDamage(damage + weapon.getDamage());
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
		setLevel(level + 1);
		setMaxHealth(maxHealth + 10);
		setMaxMana(maxMana + 10);
		setDamage(damage + 5);
		updateStatsPanel();
		// unlockNewAbility(); DEBLOCAGE DE SORTS ET TALENTS
	}
	
	public int getLevel() {
		return this.level;
	}

	public void die() {
		setHealth(0);
	}
	
	public void drinkPotion(Potion potion) {
		if (potion instanceof HealthPotion) {
			setHealth(health + potion.getValue());
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