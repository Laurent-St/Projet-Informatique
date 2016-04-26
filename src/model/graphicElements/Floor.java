package model.graphicElements;

import model.Game;

public class Floor extends Tile {
	
	public Floor(Game game) {
		super(TileLibrary.FLOOR, "floor", game);
		//System.out.println("new Floor");
		setIsWalkable(true);
	}

}
