/*
 * Classe abstraite d�finissant le comportement des cases de la map
 * Celles-ci sont stock�es dans des Map
 * Elles sont li�es � un int[] qui est donn� par la classe statique TileLibrary (association du type de tile � son mod�le graphique)
 */

package model.graphicElements;

import java.io.Serializable;

import model.Game;

public abstract class Tile implements Serializable {

	private static final long serialVersionUID = 1L;
	private int[] reference = { 0, 0 };
	private boolean isWalkable = true; // pour savoir si on peut marcher dessus
	private boolean isPoisonous = false;
	private String type;
	private Game game;

	public Tile(int[] ref, String type, Game game) {
		
		//initialisation de la Tile
		
		this.game=game;
		reference[0] = ref[0];
		reference[1] = ref[1];
		this.type = type;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return this.game;
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
		
		//Renvoie la r�f�rence liant la tile � son mod�le graphique
		
		return reference;
	}
	
	public void setReference(int[] ref) {
		
		//int[] ref donn� par TileLibrary
		
		this.reference = ref;
	}

	public String getType() {
		return type;
	}

	public void envenom() {};
	
	public boolean canTeleportToLevel() {
		//Si la tile permet de t�l�porter le joueur d'un niveau � l'autre
		return false;
	}
	
	public int getLinkedLevel() {
		//Dans le cas o� la case permet de t�l�porter le joueur � un autre niveau sp�cifi� (utile pour Door)
		return 0;
	}
}

