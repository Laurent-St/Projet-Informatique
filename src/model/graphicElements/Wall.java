package model.graphicElements;

public class Wall extends Tile {
	
	public Wall(int[] reference){
		super(reference,"wall");
		setIsWalkable(false);
	}
	
}
