package gui;

public class Wall extends Tile {
	
	public Wall(int[] reference){
		super(reference[0], reference[1]);
		setIsWalkable(false);
	}
	
}
