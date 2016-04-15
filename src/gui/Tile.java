package gui;

public class Tile {

	private int[] reference={0,0};
	public boolean isWalkable;		//pour savoir si on peut marcher dessus	
	
	public Tile(int[] ref){
		reference[0] = ref[0];
		reference[1] = ref[1];
	}
	public boolean getIsWalkable(){
		return isWalkable;
	}
	public void setIsWalkable(boolean isWalkable){
		this.isWalkable=isWalkable;
	}
	public int[] getReference() {
		return reference;
	}
}
