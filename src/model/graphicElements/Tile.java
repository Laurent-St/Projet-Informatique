package model.graphicElements;

public class Tile {

	private int[] reference={0,0};
	private boolean isWalkable;		//pour savoir si on peut marcher dessus
	private String type;
	
	public Tile(int[] ref, String type){
		//System.out.println("constru Tile");
		reference[0] = ref[0];
		reference[1] = ref[1];
		this.type = type;
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
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
}
