package model.graphicElements;

import model.Game;

public class Door extends Tile{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name="door";
	private boolean isOpen = false;
	private int linkedLevel;
	public Door(int linkedLevel, Game game) {
		super(TileLibrary.DOOR_CLOSED, "door", game);
		this.linkedLevel = linkedLevel;
		setIsWalkable(false);		//la porte est fermée à la base
	}
	
	public void doorOpen(){
		setIsWalkable(true);
		isOpen = true;
		setReference(TileLibrary.DOOR_OPEN);
	}
	public void doorClosed(){
		setIsWalkable(false);
		isOpen = false;
		setReference(TileLibrary.DOOR_CLOSED);
	}
	public boolean canTeleportToLevel() {
		return isOpen;
	}
	public int getLinkedLevel() {
		return linkedLevel;
	}
	
}
