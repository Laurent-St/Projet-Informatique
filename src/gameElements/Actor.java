package gameElements;


public class Actor extends GameObject {
	protected String name;
	protected int damage;
	protected int health;
	protected int maxHealth;

public Actor(String name,int damage, int health, int position){
	super(position);
	setName(name);
	setDamage(damage);
	setHealth(health);
}
	
public String getName(){
	return name;
}
public void setName(String name){
	if (name instanceof String){
		this.name=name;
	}else{
		throw new RuntimeException("Le nom doit �tre une cha�ne de caract�res");
	}
}

public int getDamage(){
	return damage;
}

public void setDamage(int damage){
	if (damage>=0){
		this.damage=damage;
	}else{
		this.damage=0;
		throw new RuntimeException("D�g�ts n�gatifs: valeur mise � 0");
	}
}

public int getHealth(){
	return health;
}
public void setHealth(int health){
	if (health>=0){
		this.health=health;
	}else{
		this.health=0;
	}
}
public int getMaxHealth(){
	return maxHealth;
}
public void setMaxHealth(int maxHealth){
	if (maxHealth>=0){
		this.maxHealth=maxHealth;
	}else{
		this.maxHealth=0;
	}
}
/*
public void move(){
	// a compl�ter!
}
*/
public void basicAttack(Actor target){
	target.setHealth(target.getHealth()-damage);
}
public void die(){}	//m�thode abstraite qui va �tre impl�ment�e dans Player et Monster

}