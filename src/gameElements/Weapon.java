package gameElements;

import java.awt.Rectangle;

import gui.level.LevelPanel;

public class Weapon extends CollectableObject {
	private int damage;
	private int range;
	//range=1 correspond à la case d'à côté(arme au cac), range>1 correspond à une arme à distance.


public Weapon(String name, int x,int y, int damage, int range, String image_url, Rectangle hitbox, LevelPanel level){
	super(name, x, y, image_url, hitbox, level );
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
		throw new RuntimeException("Dégâts négatifs: valeur mise à 0");
	}
}
public int getRange(){
	return range;
}
public void setRange(int range){
	if (range<1){
		throw new RuntimeException("Portée négative");
	}else{
		this.range=range;
	}
}
}