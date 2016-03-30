package gameElements;

public class Player extends Actor{
	private Inventory inventory;
	private int level;
	private Weapon weapon;


public Player(String name, int damage, int health, Inventory inventory,int level,Weapon weapon){
	super(name,damage,health);
}

public Inventory getInventory(){
	return inventory;
}
public int getLevel(){
	return level;
}
public void setLevel(int level){
	this.level=level;
}

public Weapon getWeapon(){
	return weapon;
}
public void setWeapon(Weapon weapon){
	this.weapon=weapon;
}

public void levelup(){
	setLevel(level+1);
	setHealth(health+10);
	setDamage(damage+5);
	//unlockNewAbility();		DEBLOCAGE DE SORTS ET TALENTS
}
public void die(){
	setHealth(0);
	Tomb tomb=new Tomb();
	//resetPosition(debut du niveau)
}
public void collect(CollectableObject object){
	setInInventory(object);
}

public void open(Door door){
}


}