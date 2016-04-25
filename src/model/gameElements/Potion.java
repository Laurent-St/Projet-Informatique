package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Potion extends CollectableObject {
	private int value=0;
	private static Rectangle hitbox = new Rectangle(3,5,25,20);
	
	public Potion(String name, double x, double y, int value, String imageUrl, Game game) {
		super(name, x, y, imageUrl, hitbox, game);
		setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void inventorySelectAction() {
		getGame().getPlayer().drinkPotion(this);
	}
}
