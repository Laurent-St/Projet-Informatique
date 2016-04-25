package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class HealthPotion extends Potion {
	
	private static String imageUrl = "src/model/gameElements/potionHealth.png";
	
	public HealthPotion(double x, double y, int value, Game game){
		super("Health Potion",x,y,value,imageUrl,game);
	}
}
