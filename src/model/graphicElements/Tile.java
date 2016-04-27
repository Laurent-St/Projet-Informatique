package model.graphicElements;

import model.Game;

public class Tile {

	private int[] reference = { 0, 0 };
	private boolean isWalkable = true; // pour savoir si on peut marcher dessus
	private boolean isPoisonous = false;
	private String type;
	private Game game;

	public Tile(int[] ref, String type, Game game) {
		// System.out.println("constru Tile");
		this.game=game;
		reference[0] = ref[0];
		reference[1] = ref[1];
		this.type = type;
	}

	public boolean getIsPoisonous() {
		return isPoisonous;
	}
	public void setIsPoisonous(boolean isPoisonous){
		this.isPoisonous=isPoisonous;
	}

	public boolean getIsWalkable() {
		return isWalkable;
	}

	public void setIsWalkable(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}

	public int[] getReference() {
		return reference;
	}
	
	public void setReference(int[] ref) {
		this.reference = ref;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public void doorOpen(){}

	public void envenom() {}
}

