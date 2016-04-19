package levels;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import gameElements.Monster;
import gui.Floor;
import gui.Tile;
import gui.TileLibrary;
import gui.Wall;
import gui.level.LevelPanel;

public class LevelData {
	
	private Tile[][] tiles;
	private int levelWidth = 48;
	private int levelHeight = 34;
	private Rectangle levelBounds;
	private LevelPanel levelPanel;
	private ArrayList<Monster> monsters;
	
	public LevelData(LevelPanel levelPanel) {
		this.levelPanel = levelPanel;
		tiles = new Tile[levelHeight][levelWidth];
		levelBounds = new Rectangle(0,0,getLevelWidth()-1,getLevelHeight()-1);
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
	
	public Rectangle getLevelBounds() {
		return this.levelBounds;
	}
	public ArrayList<Monster> getMonsters(){
		return monsters;
	}
	
	public void fill(Rectangle rect, Tile tile) {
		for(int i=(int) rect.getX(); i<rect.getX()+rect.getWidth(); i++) {
			for(int j=(int) rect.getY(); j<rect.getY()+rect.getHeight(); j++) {
				setTileAt(i,j,tile);
			}
		}
	}
	
	public void makeBorder(Rectangle bounds) {
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();
		int height = (int) bounds.getHeight();
		int width = (int) bounds.getWidth();
		
		setTileAt(x,y, new Wall(TileLibrary.WALL_CORNER_TL));
		setTileAt(x,y+height, new Wall(TileLibrary.WALL_CORNER_BL));
		setTileAt(x+width,y, new Wall(TileLibrary.WALL_CORNER_TR));
		setTileAt(x+width,y+height, new Wall(TileLibrary.WALL_CORNER_BR));

		fill(new Rectangle(x+1,y,width-1,1), new Wall(TileLibrary.WALL_H));
		fill(new Rectangle(x+1,y+height,width-1,1), new Wall(TileLibrary.WALL_H));
		fill(new Rectangle(x,y+1,1,height-1), new Wall(TileLibrary.WALL_V));
		fill(new Rectangle(x+width,y+1,1,height-1), new Wall(TileLibrary.WALL_V));
	}
	
	public void setTileAt(int x, int y, Tile tile) {
		tiles[y][x] = tile;
	}
	
	public Tile getTileAt(int x, int y) {
		if(x<0 || y<0 || x>getLevelWidth()-1 || y>getLevelHeight()-1) {
			return new Floor();
		} else {
			return(tiles[y][x]);
		}
	}
	
	public int[] determineWallOrientation(Point pos) {
		int[] res = TileLibrary.WALL_PLOT;
		boolean top = getTileAt(pos.x,pos.y-1).getType()=="wall";
		boolean bottom = getTileAt(pos.x,pos.y+1).getType()=="wall";
		boolean left = getTileAt(pos.x-1,pos.y).getType()=="wall";
		boolean right = getTileAt(pos.x+1,pos.y).getType()=="wall";
		
		if(top && left && right && bottom) res = TileLibrary.WALL_CROSS;
		else if(top && left && right && !bottom) res = TileLibrary.WALL_T_UP;
		else if(top && left && !right && bottom) res = TileLibrary.WALL_T_LEFT;
		else if(top && !left && right && bottom) res = TileLibrary.WALL_T_RIGHT;
		else if(!top && left && right && bottom) res = TileLibrary.WALL_T_BOTTOM;
		else if(top && left && !right && !bottom) res = TileLibrary.WALL_CORNER_TL;
		else if(top && !left && right && !bottom) res = TileLibrary.WALL_CORNER_TR;
		else if(!top && left && right && !bottom) res = TileLibrary.WALL_H;
		else if(!top && left && !right && bottom) res = TileLibrary.WALL_CORNER_BL;
		else if(top && !left && !right && bottom) res = TileLibrary.WALL_V;
		else if(!top && !left && right && bottom) res = TileLibrary.WALL_CORNER_BR;
		else if(top && !left && !right && !bottom) res = TileLibrary.WALL_V_BOTTOM;
		else if(!top && left && !right && !bottom) res = TileLibrary.WALL_H_RIGHT;
		else if(!top && !left && right && !bottom) res = TileLibrary.WALL_H_LEFT;
		else if(!top && !left && !right && bottom) res = TileLibrary.WALL_V_TOP;
		
		return res;
	}
	
	public boolean onBorder(Point pt) {
		return(pt.x==0 || pt.x==getLevelWidth()-1 || pt.y == 0 || pt.y==getLevelHeight()-1);
	}

	public boolean isPositionAvailable(double x, double y, Rectangle hitbox) {
		int tileSize = TileLibrary.getTileSize();
		int xmin = (int) Math.floor((x+hitbox.getX())/tileSize);
		int xmax = (int) Math.ceil((x+hitbox.getMaxX())/tileSize);
		int ymin = (int) Math.floor((y+hitbox.getY())/tileSize);
		int ymax = (int) Math.ceil((y+hitbox.getMaxY())/tileSize);

		for(int i=xmin;i<xmax;i++) {
			for(int j=ymin;j<ymax;j++) {
				if(getTileAt(i,j).getIsWalkable()==false) {
					return false;
				}
			}
		}
		return true;
	}
	
}
