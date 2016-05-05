/*
 * Superclasse abstraite de tous les objets pouvant �tre ramass�s. (contient sp�cifiquement la fonction inventorySelectAction())
 */

package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public abstract class CollectableObject extends GameObject {

	private static final long serialVersionUID = 1L;
	private String name;

	public CollectableObject(String name, double x, double y, String image_url, Rectangle hitbox, Game game) {
		super(x, y, image_url, hitbox, game);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name instanceof String) {
			this.name = name;
		} else {
			throw new RuntimeException("Le nom doit �tre une cha�ne de caract�res");
		}
	}

	public void inventorySelectAction(){
		//A  red�finir selon le type d'objet
	}
}