package gui;

public class Door extends Tile{
	private static String name="door";
	public Door() {
		super(Tile.DOOR_OPEN[0],Tile.DOOR_OPEN[1]);
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
