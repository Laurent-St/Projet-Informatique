package model.graphicElements;

import model.Game;

public class Wall extends Tile {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Wall(int[] reference, Game game){
		super(reference,"wall", game);
		setIsWalkable(false);
	}
	
}
