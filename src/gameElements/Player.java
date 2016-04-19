package gameElements;

import java.awt.Rectangle;
import gui.level.LevelPanel;

public class Player extends Actor implements Runnable {
	private Inventory inventory;
	private int level;
	private Weapon weapon;
	private int mana;
	private int maxMana;
	private int experience;
	private int maxExperienceForLevel;
	
	private static double playerSpeed  = 1.5;
	private static Rectangle playerHitbox = new Rectangle(8,0,15,31);

	public Player(String name, int damage, int health, int mana, LevelPanel gameLevel) {
		super("src/gameElements/characterWarrior.png",20,20,0,0, playerSpeed, gameLevel, playerHitbox);
		Inventory inventory = new Inventory(5);
		//equipWeapon(weapon);
		setLevel(1);
		setMana(mana);
		//setExperience(0);

		setMoving("null");
		
		Thread actorThread = new Thread(this);
		actorThread.start();	
		
		//TEST DE l'EPEE
		Sword sword = new Sword("epee",20,20,this.getLevelPanel(), this);
		equipWeapon(sword);
		getLevelPanel().add(sword);
		getLevelPanel().setComponentZOrder(sword, 0);
		sword.setVisible(true);
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
		return experience;
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

	public Weapon getWeapon() {
		return weapon;
	}

	public void equipWeapon(Weapon weapon) {
		/*
		 * on consid�re que le joueur a une valeur de degats de base et que le
		 * fait d'equiper une arme augmente simplement la valeur de degats du
		 * joueur. MAIS IL FAUDRA TENIR COMPTE DES TYPES D'ARMES.
		 */
		this.weapon = weapon;
		setDamage(damage + weapon.getDamage());
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
			this.repaint();
		}
	}
}