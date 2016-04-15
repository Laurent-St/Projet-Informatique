package gui;

public class Door extends Tile{
	private static String name="door";
	public Door() {
		super(TileLibrary.DOOR_OPEN);
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
