package model.graphicElements;

import model.Game;

public class Wall extends Tile {
	
	public Wall(int[] reference, Game game){
		super(reference,"wall", game);
		setIsWalkable(false);
	}
	
}
