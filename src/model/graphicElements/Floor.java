package model.graphicElements;

public class Floor extends Tile {
	
	public Floor() {
		super(TileLibrary.FLOOR, "floor");
		System.out.println("new Floor");
		setIsWalkable(true);
	}

}
