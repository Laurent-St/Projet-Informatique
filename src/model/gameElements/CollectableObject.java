package model.gameElements;

import java.awt.Rectangle;

import view.level.LevelPanel;

public class CollectableObject extends GameObject {
	private String name;

	public CollectableObject(String name, double x, double y, String image_url, Rectangle hitbox, LevelPanel levelPanel) {
		super(x, y, image_url, hitbox, levelPanel);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name instanceof String) {
			this.name = name;
		} else {
			throw new RuntimeException("Le nom doit être une chaîne de caractères");
		}
	}
}