package model.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import model.Game;
import model.gameElements.Actor;
import model.gameElements.CollectableObject;
import model.gameElements.GameObject;
import model.gameElements.Monster;
import model.gameElements.Player;
import model.gameElements.Projectile;
import model.gameElements.Zombie;
import model.graphicElements.Door;
import model.graphicElements.Floor;
import model.graphicElements.Tile;
import model.graphicElements.TileLibrary;
import model.graphicElements.Wall;

public class Map implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int levelNum;
	private static int levelWidth = 48;
	private static int levelHeight = 34;
	private static Rectangle levelBounds = new Rectangle(0,0,levelWidth-1,levelHeight-1);
	private Tile[][] tiles;
	private ArrayList<Monster> monsters= new ArrayList<Monster>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<CollectableObject> collectableObjects=new ArrayList<CollectableObject>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private ArrayList<GameObject> specialFX = new ArrayList<GameObject>();
	private Game game;
	
	public Map(int levelNum, Game game) {
		this.game=game;
		this.levelNum = levelNum;
		tiles = new Tile[levelHeight][levelWidth];
	}
	
	public Game getGame(){return game;}
	
	public void reloadAction(Game game){
		this.game = game;
		this.projectiles = new ArrayList<Projectile>();
		for(int i=0;i<tiles.length;i++) {
			for(int j=0;j<tiles[0].length;j++) {
				getTileAt(i,j).setGame(game);
			}
		}
		for(Monster m : monsters) {
			m.reloadAction(game);
		}
		for(GameObject go : gameObjects) {
			go.reloadAction(game);
		}
		for(CollectableObject co : collectableObjects) {
			co.reloadAction(game);
		}
	}
	
	public ArrayList<GameObject> getGameObjects() {return this.gameObjects;}
	
	public ArrayList<CollectableObject> getCollectableObjects(){return collectableObjects;}
	
	public void addCollectableObject(CollectableObject co) {collectableObjects.add(co);}
	
	public void removeCollectableObject(CollectableObject co) {collectableObjects.remove(co);}
	
	public ArrayList<GameObject> getSpecialFX(){return specialFX;}
	
	public void addSpecialFX(GameObject sfx) {specialFX.add(sfx);}
	
	public void removeSpecialFX(GameObject sfx) {specialFX.remove(sfx);}
	
	public ArrayList<Monster> getMonsters(){return monsters;}
	
	public void addMonster(Monster m) {monsters.add(m);}
	
	public void removeMonster(Monster m) {monsters.remove(m);}
	
	public void addGameObject(GameObject o) {gameObjects.add(o);}
	
	public void removeGameObject(GameObject o) {gameObjects.remove(o);}
	
	public ArrayList<Projectile> getProjectiles() {return this.projectiles;}
	
	public void addProjectile(Projectile o) {projectiles.add(o);}
	
	public void removeProjectile(Projectile o) {projectiles.remove(o);}
	
	public Rectangle getLevelBounds() {
		return levelBounds;
	}
	
	public Tile[][] getTilesData() {
		return tiles;
	}
	
	public int getLevelWidth() {
		return levelWidth;
	}
	
	public int getLevelHeight() {
		return levelHeight;
	}
	
	public int getLevelNum() {
		return levelNum;
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
		
		setTileAt(x,y, new Wall(TileLibrary.WALL_CORNER_BR, game));
		setTileAt(x,y+height, new Wall(TileLibrary.WALL_CORNER_TR, game));
		setTileAt(x+width,y, new Wall(TileLibrary.WALL_CORNER_BL, game));
		setTileAt(x+width,y+height, new Wall(TileLibrary.WALL_CORNER_TL, game));

		fill(new Rectangle(x+1,y,width-1,1), new Wall(TileLibrary.WALL_H, game));
		fill(new Rectangle(x+1,y+height,width-1,1), new Wall(TileLibrary.WALL_H, game));
		fill(new Rectangle(x,y+1,1,height-1), new Wall(TileLibrary.WALL_V, game));
		fill(new Rectangle(x+width,y+1,1,height-1), new Wall(TileLibrary.WALL_V, game));
	}
	
	public void initDoors() {
		Door door1 = new Door(getLevelNum()-1,getGame());
		door1.doorOpen();
		Door door2 = new Door(getLevelNum()+1,getGame());
		if(getLevelNum()!=1) {
			setTileAt(2,0,door1);
			setTileAt(1,0,new Wall(TileLibrary.WALL_H_RIGHT, getGame()));
			setTileAt(3,0,new Wall(TileLibrary.WALL_H_LEFT, getGame()));
		}
		if(getLevelNum() !=10) {
			setTileAt(getLevelWidth()-3, getLevelHeight()-1, door2);
			setTileAt(getLevelWidth()-4, getLevelHeight()-1,new Wall(TileLibrary.WALL_H_RIGHT, getGame()));
			setTileAt(getLevelWidth()-2, getLevelHeight()-1,new Wall(TileLibrary.WALL_H_LEFT, getGame()));
		}
	}
	
	public void setTileAt(int x, int y, Tile tile) {
		if(x>=0 && y>=0 && x<=getLevelWidth()-1 && y<=getLevelHeight()-1) {
			tiles[y][x] = tile;
		}
	}
	
	public Tile getTileAt(int x, int y) {
		if(x<0 || y<0 || x>getLevelWidth()-1 || y>getLevelHeight()-1) {
			//System.out.println("new Floor");
			return new Floor(game);
			
		} else {
			//System.out.println("new tile");
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

	public CollectableObject detectAnObject(int x, int y, Rectangle hitbox){	//la hitbox sera celle du Player
		Rectangle testHitbox=new Rectangle(x, y, (int)hitbox.getWidth(), (int)hitbox.getHeight());
		int i=0;
		while(i<getCollectableObjects().size()){
			CollectableObject o = collectableObjects.get(i);
			Rectangle testHitbox2 = new Rectangle(o.getX(), o.getY(), (int)o.getHitbox().getWidth(), (int)o.getHitbox().getHeight());
			if(testHitbox.intersects(testHitbox2)) {
				return o;
			}
			i++;
		}
		return null;
	}
	
	public boolean isPositionOccupied(double x, double y, GameObject source, boolean playerTransparent){
		ArrayList<Monster> monsters=new ArrayList<Monster>();
		monsters=getGame().getCurrentMap().getMonsters();
		Player player = getGame().getPlayer();
		
		boolean res=false;  //default result is a free position
		Rectangle testHitbox=new Rectangle((int)x,(int)y, (int)source.getHitbox().getWidth(), (int)source.getHitbox().getHeight());
		int i=0;
		while(i<monsters.size() && res==false){
			Monster m = monsters.get(i);
			if (m != source){
				Rectangle testHitbox2 = new Rectangle(m.getX(), m.getY(), (int)m.getHitbox().getWidth(), (int)m.getHitbox().getHeight());
				if(testHitbox2.intersects(testHitbox)) {
					res = true;
				}
			}
			i++;
		}
		
		if(source!=player && playerTransparent) {
			Rectangle testHitboxPlayer = new Rectangle(player.getX(), player.getY(), (int)player.getHitbox().getWidth(), (int)player.getHitbox().getHeight());
			if(testHitboxPlayer.intersects(testHitbox)) {
				res = true;
			}
		}
		
		return res;
	}

	public void initActorsAndObjects() {
		// A REDEFINIR DANS LES SOUS-CLASSES
	}

	public ArrayList<Actor> getActorsInRectangle(int x, int y, Rectangle hitbox, Actor reference) {
		ArrayList<Actor> detectedActors = new ArrayList<Actor>();
		@SuppressWarnings("unchecked")
		ArrayList<Actor> actors = (ArrayList<Actor>) getGame().getCurrentMap().getMonsters().clone();
		actors.add(getGame().getPlayer());
		
		Rectangle testHitbox=new Rectangle(x+(int)hitbox.getX(),y+(int)hitbox.getY(), (int)hitbox.getWidth(), (int)hitbox.getHeight());
		for (Actor a : actors) {
			Rectangle testHitbox2 = new Rectangle(a.getX(), a.getY(), (int)a.getHitbox().getWidth(), (int)a.getHitbox().getHeight());
			if(testHitbox2.intersects(testHitbox) && reference!=a) {
				detectedActors.add(a);
				System.out.println("Actor touch�");
			}
		}
		return detectedActors;
	}
	
	public void tryToTeleport(GameObject object, Point point) {
		int x = point.x;
		int y = point.y;
		int step = 1;
		int inv = 1;
		while(isPositionOccupied(x,y,object,false) || !isPositionWalkable(x,y,object.getHitbox())) {
			x += step*inv;
			y += step*inv;
			step++;
			inv*=-1;
		}
		object.setX(x);
		object.setY(y);
	}

	public void stopAllThreads() {
		for(Projectile p: getProjectiles()) {
			p.interruptThread();
		}
	}
	
	public void resumeAllThreads() {
		for(Projectile p: getProjectiles()) {
			p.resumeThread();
		}
	}
	public void generateZombies(int max){
		for (int i=0; i<max; i++){
			Random rnd = new Random();
			Monster newMonster;
			do {
				int randX = rnd.nextInt(920);
				int randY = rnd.nextInt(640);
				newMonster= new Zombie(randX, randY,
						8+getLevelNum()*2, 98+getLevelNum()*2, 0.45+(long)getLevelNum()/20.0, 10,
						getGame(), new Rectangle(8,0,15,31));
			} while(isPositionWalkable(newMonster.getX(), newMonster.getY(), newMonster.getHitbox())==false ||
					isPositionOccupied(newMonster.getX(), newMonster.getY(), newMonster, true));
			
			addMonster(newMonster);
			System.out.println("Monstre ajout�");
		}
	}
	
	
}
