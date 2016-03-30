package gameElements;

public class Weapon extends CollectableObject {
	private int damage;
	private int range;
	//range=1 correspond � la case d'� c�t�(arme au cac), range>1 correspond � une arme � distance.


public Weapon(String name, int position, int damage, int range){
	super(name,position);
	setDamage(damage);
	setRange(range);
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
public int getRange(){
	return range;
}
public void setRange(int range){
	if (range<=1){
		throw new RuntimeException("Port�e n�gative");
	}else{
		this.range=range;
	}
}
}