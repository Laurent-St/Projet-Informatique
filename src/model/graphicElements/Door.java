package model.graphicElements;

public class Door extends Tile{
	private static String name="door";
	public Door() {
		super(TileLibrary.DOOR_OPEN, "door");
		setIsWalkable(false);		//la porte est ferm�e � la base
	}
	
	public void doorOpen(){
		setIsWalkable(true);
		//ATTENTION il faut changer d'image!
	}
	public void doorClosed(){
		setIsWalkable(false);
	}
	
}