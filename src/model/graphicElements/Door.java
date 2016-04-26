package model.graphicElements;

import model.Game;

public class Door extends Tile{
	private static String name="door";
	public Door(Game game) {
		super(TileLibrary.DOOR_OPEN, "door", game);
		setIsWalkable(false);		//la porte est fermée à la base
	}
	
	public void doorOpen(){
		setIsWalkable(true);
		//ATTENTION il faut changer d'image!
	}
	public void doorClosed(){
		setIsWalkable(false);
	}
	
}
