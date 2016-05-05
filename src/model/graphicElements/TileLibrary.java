/*
 * Classe statique permettant de
 * 	-Donner � la vue l'image des diff�rentes tiles
 * 	-D'attribuer � chaque tile une r�f�rence liant son type � son image pr�sente dans Tiles.png, sous la forme d'un int[]
 * 
 * Au d�but du jeu, appeler la fonction TileLibrary.initImage(); afin de charger l'image des tiles
 */

package model.graphicElements;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class TileLibrary {
	private final static int tileSize = 20;
	
	public final static int[] FLOOR = {0,0};
	public final static int[] WALL_H_LEFT = {1,0};
	public final static int[] WALL_H = {2,0};
	public final static int[] WALL_H_RIGHT = {3,0};
	public final static int[] WALL_V_TOP = {4,0};
	public final static int[] WALL_V = {5,0};
	public final static int[] WALL_V_BOTTOM = {6,0};
	public final static int[] WALL_PLOT = {7,0};
	public final static int[] WALL_CORNER_TR = {8,0};
	public final static int[] WALL_CORNER_TL = {9,0};
	public final static int[] WALL_CORNER_BR = {10,0};
	public final static int[] WALL_CORNER_BL = {11,0};
	public final static int[] WALL_CROSS = {12,0};
	public final static int[] WALL_T_LEFT = {13,0};
	public final static int[] WALL_T_RIGHT = {14,0};
	public final static int[] WALL_T_BOTTOM = {15,0};
	public final static int[] WALL_T_UP = {16,0};
	public final static int[] DOOR_OPEN ={18,0};
	public final static int[] DOOR_CLOSED ={17,0};
	public final static int[] POISON_TRAP={19,0};
	
	private static Image tilesImage;
	
	public static void initImage() {
		
		//Initialise l'image des tiles
		
		try {
			tilesImage = ImageIO.read(new File("src/model/graphicElements/Tiles.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Image getTilesImage() {
		return tilesImage;
	}
	
	public static int getTileSize() {
		return tileSize;
	}
	
	public static Rectangle getBoundsOf(Tile tile) {
		
		//Utile pour la vue
		//Renvoie un rectangle correspondant � la sous-image de Tiles.png correspondant � la Tile donn�e en param�tre
		
		int[] reference = tile.getReference();
		return new Rectangle(tileSize*reference[0],tileSize*reference[1],
				tileSize,tileSize);
	}
}
