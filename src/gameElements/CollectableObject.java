package gameElements;

import java.awt.Rectangle;

import gui.level.LevelPanel;

public class CollectableObject extends GameObject {
	private String name;

	public CollectableObject(String name, double x, double y, String image_url, Rectangle hitbox, LevelPanel level) {
		super(x, y, image_url, hitbox, level);
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