package model.graphicElements;

public class Floor extends Tile {
	
	public Floor() {
		super(TileLibrary.FLOOR, "floor");
		setIsWalkable(true);
	}

}
