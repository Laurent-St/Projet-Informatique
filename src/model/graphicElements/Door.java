/*
 * Tile porte, permet la téléportation du joueur entre les niveaux
 * Permet une interaction avec le joueur (ouverture de porte)
 * Doit être liée à un numéro de niveau pour permettre la téléportation
 */

package model.graphicElements;

import model.Game;

public class Door extends Tile{

	private static final long serialVersionUID = 1L;
	private static String name="door";
	private boolean isOpen = false;
	private int linkedLevel;
	
	public Door(int linkedLevel, Game game) {
		super(TileLibrary.DOOR_CLOSED, name, game);
		
		//Initialisation de la porte avec le numéro de niveau lié
		
		this.linkedLevel = linkedLevel;
		setIsWalkable(false);		//la porte est fermée à la base
	}
	
	public void doorOpen(){
		//Ouverture de la porte avec changement graphique
		setIsWalkable(true);
		isOpen = true;
		setReference(TileLibrary.DOOR_OPEN);
	}
	public void doorClosed(){
		//Fermeture de la porte avec changement graphique
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
