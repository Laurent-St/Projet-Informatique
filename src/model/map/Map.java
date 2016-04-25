package model.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import model.Game;
import model.gameElements.Axe;
import model.gameElements.CollectableObject;
import model.gameElements.GameObject;
import model.gameElements.Monster;
import model.gameElements.Player;
import model.gameElements.Projectile;
import model.graphicElements.Floor;
import model.graphicElements.Tile;
import model.graphicElements.TileLibrary;
import model.graphicElements.Wall;

public class Map {
	private static int levelWidth = 48;
	private static int levelHeight = 34;
	private static Rectangle levelBounds = new Rectangle(0,0,levelWidth-1,levelHeight-1);
	private Tile[][] tiles;
	private int numberOfMonsters=5;
	private ArrayList<Monster> monsters= new ArrayList<Monster>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<CollectableObject> collectableObjects=new ArrayList<CollectableObject>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private Game game;
	
	public Map() {
		//this.game=game;
		tiles = new Tile[levelHeight][levelWidth];
		//initMonsters();
	}
	
	public Game getGame(){
		return game;
	}
	
	public void initMonsters(){
		for (int i=0; i<numberOfMonsters; i++){
			Random rnd = new Random();
			Monster newMonster;
			do {
				int randX = rnd.nextInt(920);
				int randY = rnd.nextInt(640);
				newMonster= new Monster("src/model/gameElements/zombie.png", randX, randY, 50, 200, 0.5, getGame(), new Rectangle(8,0,15,31));
			} while(isPositionWalkable(newMonster.getX(), newMonster.getY(), newMonster.getHitbox())== false ||
					newMonster.isPositionOccupied(newMonster.getX(), newMonster.getY(), newMonster, true));
			
			monsters.add(newMonster);
			System.out.println("Monstre ajouté");
		}
	}
	
	//public void initGameObjects(){
		//Axe axe= new Axe("hache", 180, 200, game, game.getPlayer());
		//collectableObjects.add(axe);
		//axe.setX(180);
		//axe.setY(200);
		//addGameObject(axe);
	//}
	public ArrayList<GameObject> getGameObjects() {
		return this.gameObjects;
	}
	public ArrayList<CollectableObject> getCollectableObjects(){
		return collectableObjects;
	}
	
	public ArrayList<Monster> getMonsters(){
		return monsters;
	}
	
	public void addGameObject(GameObject o) {
		gameObjects.add(o);
	}
	
	public void removeGameObject(GameObject o) {
		gameObjects.remove(o);
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return this.projectiles;
	}
	
	public void addProjectile(Projectile o) {
		projectiles.add(o);
	}
	
	public void removeProjectile(Projectile o) {
		projectiles.remove(o);
	}
	
	public Rectangle getLevelBounds() {
		return levelBounds;
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
			System.out.println("new Floor");
			return new Floor();
			
		} else {
			System.out.println("new tile");
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

	public boolean isPositionWalkable(double x, double y, Rectangle hitbox) {
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

	public ArrayList<CollectableObject> detectAnObject(int x, int y, Rectangle hitbox){	//la hitbox sera celle du Player
		Rectangle testHitbox=new Rectangle((int)x,(int)y, (int)hitbox.getWidth(), (int)hitbox.getHeight());
		ArrayList<CollectableObject> res=new ArrayList<CollectableObject>();
		int i=0;
		while(i<collectableObjects.size()){
			CollectableObject o = collectableObjects.get(i);
			//if (m != source){
			//Rectangle testHitbox2 = new Rectangle(m.getX(), m.getY(), (int)m.getHitbox().getWidth(), (int)m.getHitbox().getHeight());
			if(testHitbox.intersects(o.getHitbox())) {
				res.add(o);
			}
		//}
			i++;
		}
		return res;
		
//		if(source!=player && playerTransparent) {
//			Rectangle testHitboxPlayer = new Rectangle(player.getX(), player.getY(), (int)player.getHitbox().getWidth(), (int)player.getHitbox().getHeight());
//			if(testHitboxPlayer.intersects(testHitbox)) {
//				res = true;
//			}
//		}
//		
//		return res;
//	}
}
	
}
