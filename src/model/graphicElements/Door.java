package model.graphicElements;

import model.Game;

public class Door extends Tile{
	private static String name="door";
	public Door(Game game) {
		super(TileLibrary.DOOR_CLOSED, "door", game);
		setIsWalkable(false);		//la porte est fermée à la base
	}
	
	public void doorOpen(){
		setIsWalkable(true);
		setReference(TileLibrary.DOOR_OPEN);
	}
	public void doorClosed(){
		setIsWalkable(false);
		setReference(TileLibrary.DOOR_CLOSED);
	}
	
}
