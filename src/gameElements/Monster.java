package gameElements;

public class Monster extends Actor {
	//qu'est-ce qui diff�rencie clairement les Monster du Player, � part le skin?
	public Monster(String name,int damage, int health, int position){
		super(name, damage, health, position);
	}
}
