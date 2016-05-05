/*
 * Tile porte, permet la t�l�portation du joueur entre les niveaux
 * Permet une interaction avec le joueur (ouverture de porte)
 * Doit �tre li�e � un num�ro de niveau pour permettre la t�l�portation
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
		
		//Initialisation de la porte avec le num�ro de niveau li�
		
		this.linkedLevel = linkedLevel;
		setIsWalkable(false);		//la porte est ferm�e � la base
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
