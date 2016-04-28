package model.graphicElements;

import model.Game;

public class Floor extends Tile {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Floor(Game game) {
		super(TileLibrary.FLOOR, "floor", game);
		//System.out.println("new Floor");
		setIsWalkable(true);
	}

}
