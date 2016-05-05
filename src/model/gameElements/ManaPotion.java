/*
 * Potion de mana
 */

package model.gameElements;

import model.Game;

public class ManaPotion extends Potion {

	private static final long serialVersionUID = 1L;
	private static String imageUrl = "src/model/gameElements/potionMana.png";

	public ManaPotion(double x, double y, int value, Game game){
		super("Mana Potion",x,y,value,imageUrl,game);
	}
}
