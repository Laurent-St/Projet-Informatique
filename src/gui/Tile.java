package gui;

public class Tile {
<<<<<<< HEAD
	private final static int tileSize = 20;
	
	public final static int[] FLOOR = {0,0};
	public final static int[] WALL_H_LEFT = {1,0};
	public final static int[] WALL_H = {2,0};
	public final static int[] WALL_H_RIGHT = {3,0};
	public final static int[] WALL_V_TOP = {4,0};
	public final static int[] WALL_V = {5,0};
	public final static int[] WALL_V_BOTTOM = {6,0};
	public final static int[] WALL_PLOT = {7,0};
	public final static int[] WALL_CORNER_BL = {8,0};
	public final static int[] WALL_CORNER_BR = {9,0};
	public final static int[] WALL_CORNER_TL = {10,0};
	public final static int[] WALL_CORNER_TR = {11,0};
	public final static int[] WALL_CROSS = {12,0};
	public final static int[] WALL_T_LEFT = {13,0};
	public final static int[] WALL_T_RIGHT = {14,0};
	public final static int[] WALL_T_BOTTOM = {15,0};
	public final static int[] WALL_T_UP = {16,0};
	public final static int[] DOOR_CLOSED ={17,0};
	
=======
>>>>>>> origin/master
	
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
