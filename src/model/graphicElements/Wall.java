/*
 * Tile correspondant à un mur
 * Demande un int[] dans le constructeur (issu de la classe statique TileLibrary) afin de donner une orientation au mur
 */

package model.graphicElements;

import model.Game;

public class Wall extends Tile {
	
	private static final long serialVersionUID = 1L;

	public Wall(int[] reference, Game game){
		super(reference,"wall", game);
		setIsWalkable(false); //Mur n'est pas walkable
	}
	
}
