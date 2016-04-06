package levels;

import java.awt.Rectangle;

import gui.Tile;

public class LevelData {
	
	private int[][][] tilesData;
	private int levelWidth = 50;
	private int levelHeight = 40;
	
	
	public LevelData() {
		tilesData = new int[levelHeight][levelWidth][2];
	}

	public int[][][] getTilesData() {
		return tilesData;
	}

	public void setTilesData(int[][][] tilesData) {
		this.tilesData = tilesData;
	}
	
	public int getLevelWidth() {
		return this.levelWidth;
	}
	
	public int getLevelHeight() {
		return this.levelHeight;
	}
	
	public void fill(Rectangle rect, int[] type) {
		for(int i=(int) rect.getX(); i<rect.getX()+rect.getWidth(); i++) {
			for(int j=(int) rect.getY(); j<rect.getY()+rect.getHeight(); j++) {
				setTileAt(i,j,type);
			}
		}
	}
	
	public void makeBorder() {
		setTileAt(0,0,Tile.WALL_CORNER_TL);
		setTileAt(0,getLevelHeight()-1,Tile.WALL_CORNER_BL);
		setTileAt(getLevelWidth()-1,0,Tile.WALL_CORNER_TR);
		setTileAt(getLevelWidth()-1,getLevelHeight()-1,Tile.WALL_CORNER_BR);

		fill(new Rectangle(1,0,getLevelWidth()-2,1), Tile.WALL_H);
		fill(new Rectangle(1,getLevelHeight()-1,getLevelWidth()-2,1), Tile.WALL_H);
		fill(new Rectangle(0,1,1,getLevelHeight()-2),Tile.WALL_V);
		fill(new Rectangle(getLevelWidth()-1,1,1,getLevelHeight()-2),Tile.WALL_V);
	}
	
	public void setTileAt(int x, int y, int[] type) {
		tilesData[y][x] = type;
	}
	
	public int[] getTileAt(int x, int y) {
		return(tilesData[y][x]);
	}
	
}
