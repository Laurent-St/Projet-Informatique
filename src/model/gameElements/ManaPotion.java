package model.gameElements;

import model.Game;

public class ManaPotion extends Potion {

	private static String imageUrl = "src/model/gameElements/potionMana.png";

	public ManaPotion(double x, double y, int value, Game game){
		super("Mana Potion",x,y,value,imageUrl,game);
	}
}
