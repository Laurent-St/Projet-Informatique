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
//	private ArrayList<Monster> monsters= new ArrayList<Monster>();
//	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
//	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	private RandomMap level1;
	private RandomMap level2;
	
	private Map currentMap;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		
		initLevel();
		initPlayer();
		getCurrentMap().initActorsAndObjects();
		initGraphics();
		initControls();
		
	}
	
	public void initLevel() {
		level1= new RandomMap(this);
		//level1.setGame(this);
		currentMap = level1;
		
	}
	
	public void initGraphics() {
		FontLoader.loadGameFont();
		TileLibrary.initImage();
		gamePanel = new GamePanel(this);
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
	
	
	public void initPlayer(){
		player = new Player(1, 1, 1, this);
		player.getInventory().setInInventory(new HealthPotion(0,0,50,this));
		player.getInventory().setInInventory(new ManaPotion(0,0,50,this));
		player.getInventory().setInInventory(new ManaPotion(0,0,50,this));
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

	public Player getPlayer() {
		return this.player;
	}
	
}
		
	




