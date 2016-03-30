package gameElements;

public class Player extends Actor{
	private Inventory inventory;
	private int level;
	private Weapon weapon;


public Player(String name, int damage, int health,int position,int level,Weapon weapon){
	super(name,damage,health,position);
	Inventory inventory=new Inventory(5);
	equipWeapon(weapon);
	setLevel(1);
}

public Weapon getWeapon(){
	return weapon;
}
public void equipWeapon(Weapon weapon){
	/*on considère que le joueur a une valeur de degats de base et que le fait d'equiper une arme augmente
	 * simplement la valeur de degats du joueur. MAIS IL FAUDRA TENIR COMPTE DES TYPES D'ARMES.
	 */
	this.weapon=weapon;
	setDamage(damage+weapon.getDamage());	
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
	inventory.setInInventory(object);
}

//public void open(){}


}