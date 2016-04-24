package model.gameElements;

import java.awt.Rectangle;

import model.Game;
import view.StatsPanel;

public class Player extends Actor implements Runnable{
	private Inventory inventory;
	private int level;
	private int mana;
	private int maxMana;
	private int experience;
	private int maxExperienceForLevel;
	
	private static double playerSpeed  = 1.5;
	private static Rectangle playerHitbox = new Rectangle(8,0,15,31);
	
	private HandWeapon handWeapon;
	private ThrowableWeapon throwableWeapon;
	private Weapon equipedWeapon;
	
	private StatsPanel statsPanel;

	public Player(String name, int damage, int health, int mana, Game game) {
		super("src/model/gameElements/characterWarrior.png",50,50,0,0, playerSpeed, game, playerHitbox);
		inventory = new Inventory(5);
		//equipWeapon(weapon);
		setLevel(1);
		setMana(mana);
		//setExperience(0);

		setMoving("null");
		
		Thread actorThread = new Thread(this);
		actorThread.start();	
		System.out.println("new Player");
		
		//TEST DE l'EPEE
		Sword sword = new Sword("epee",20,20,this.getGame(), this);
		//equipHandWeapon(sword);
		
		//TEST DE LA FIREBALL
		FireballWeapon fw = new FireballWeapon(getGame(), this);
		equipThrowableWeapon(fw);
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
		}
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		if (mana < 0) {
			throw new RuntimeException("Valeur de mana n�gative");
		} else {
			this.mana = mana;
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
		}
	}

	public HandWeapon getHandWeapon() {
		return handWeapon;
	}
	
	public ThrowableWeapon getThrowableWeapon() {
		return throwableWeapon;
	}

	public void equipHandWeapon(HandWeapon weapon) {
		/*
		 * on consid�re que le joueur a une valeur de degats de base et que le
		 * fait d'equiper une arme augmente simplement la valeur de degats du
		 * joueur. MAIS IL FAUDRA TENIR COMPTE DES TYPES D'ARMES.
		 */
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
	}

	public void levelup() {
		setLevel(level + 1);
		setMaxHealth(maxHealth + 10);
		setMaxMana(maxMana + 10);
		setDamage(damage + 5);
		// unlockNewAbility(); DEBLOCAGE DE SORTS ET TALENTS
	}

	public void die() {
		setHealth(0);
		//Tomb tomb = new Tomb("Tombe de " + name, position, inventory, experience);
		// resetPosition(debut du niveau)
	}

	public void collect(CollectableObject object) {
		inventory.setInInventory(object);
	}

	// public void open(){}
	public void drinkPotion(Potion potion) {
		if (potion instanceof HealthPotion) {
			setHealth(health + potion.getValue());
		} else { // si c'est une potion de mana
			setMana(mana + potion.getValue());
		}
	}
	
	public void attack() {
		if(getThrowableWeapon()!=null) {
			getThrowableWeapon().attack();
		} else if (getHandWeapon()!=null){
			getHandWeapon().attack();
		}
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