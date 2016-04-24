package model;

import model.graphicElements.*;
import model.map.Map;
import model.map.RandomMap;
import view.FontLoader;
import view.GamePanel;
import view.GameWindow;
import view.level.LevelPanel;
import view.menu.MainMenu;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import controls.PlayerControls;
import model.gameElements.*;

public class Game {
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private LevelPanel levelPanel;
	
	private Player player;
	private PlayerControls playerControls;
	private int numberOfMonsters=10;
	private ArrayList<Monster> monsters= new ArrayList<Monster>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	private RandomMap level1;
	private RandomMap level2;
	
	private Map currentMap;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
				
		initLevel();
		initLevelActors();
		initGraphics();
		initControls();
		
	}
	
	public void initLevel() {
		level1= new RandomMap();
		currentMap = level1;
		
	}
	
	public void initGraphics() {
		FontLoader.loadGameFont();
		TileLibrary.initImage();
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel); //Affiche la fenêtre principale
		levelPanel = new LevelPanel(this, getCurrentMap());
	}
	
	public void initMenu() {
		new MainMenu(gameWindow.getGamePanel());
	}
	
	public void initControls() {
		playerControls = new PlayerControls(player);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		gamePanel.addKeyListener(playerControls);
	}
	
	
	public void initLevelActors(){
		player = new Player("Couillon", 1, 1, 1, this);
		for (int i=0; i<numberOfMonsters; i++){
			Random rnd = new Random();
			Monster newMonster;
			do {
				int randX = rnd.nextInt(920);
				int randY = rnd.nextInt(640);
				newMonster= new Monster("src/model/gameElements/zombie.png", randX, randY, 50, 200, 0.5, this, new Rectangle(8,0,15,31));
			} while(getCurrentMap().isPositionWalkable(newMonster.getX(), newMonster.getY(), newMonster.getHitbox())== false ||
					newMonster.isPositionOccupied(newMonster.getX(), newMonster.getY(), newMonster, true));
			
			monsters.add(newMonster);
			System.out.println("Monstre ajouté");
		}
	}

	public GameWindow getGameWindow() {	
		return gameWindow;
	}
	
	public Map getCurrentMap() {
		return this.currentMap;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

	public Player getPlayer() {
		return this.player;
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return this.gameObjects;
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
	
}
		
	




