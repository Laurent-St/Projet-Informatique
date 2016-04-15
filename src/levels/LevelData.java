package levels;

import java.awt.Rectangle;

import gui.Tile;
import gui.TileLibrary;
import gui.Wall;

public class LevelData {
	
	private Tile[][] tiles;
	private int levelWidth = 48;
	private int levelHeight = 34;
	
	
	public LevelData() {
		tiles = new Tile[levelHeight][levelWidth];
	}

	public Tile[][] getTilesData() {
		return tiles;
	}
	
	public int getLevelWidth() {
		return this.levelWidth;
	}
	
	public int getLevelHeight() {
		return this.levelHeight;
	}
	
	public void fill(Rectangle rect, Tile tile) {
		for(int i=(int) rect.getX(); i<rect.getX()+rect.getWidth(); i++) {
			for(int j=(int) rect.getY(); j<rect.getY()+rect.getHeight(); j++) {
				setTileAt(i,j,tile);
			}
		}
	}
	
	public void makeBorder() {
		setTileAt(0,0, new Wall(TileLibrary.WALL_CORNER_TL));
		setTileAt(0,getLevelHeight()-1, new Wall(TileLibrary.WALL_CORNER_BL));
		setTileAt(getLevelWidth()-1,0, new Wall(TileLibrary.WALL_CORNER_TR));
		setTileAt(getLevelWidth()-1,getLevelHeight()-1, new Wall(TileLibrary.WALL_CORNER_BR));

		fill(new Rectangle(1,0,getLevelWidth()-2,1), new Wall(TileLibrary.WALL_H));
		fill(new Rectangle(1,getLevelHeight()-1,getLevelWidth()-2,1), new Wall(TileLibrary.WALL_H));
		fill(new Rectangle(0,1,1,getLevelHeight()-2), new Wall(TileLibrary.WALL_V));
		fill(new Rectangle(getLevelWidth()-1,1,1,getLevelHeight()-2), new Wall(TileLibrary.WALL_V));
	}
	
	public void setTileAt(int x, int y, Tile tile) {
		tiles[y][x] = tile;
	}
	
	public Tile getTileAt(int x, int y) {
		return(tiles[y][x]);
	}
	
}
