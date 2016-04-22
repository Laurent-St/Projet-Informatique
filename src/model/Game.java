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

import controls.PlayerControls;
import model.gameElements.*;

public class Game {
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private LevelPanel levelPanel;
	
	private Player player;
	private int numberOfMonsters=5;
	private ArrayList<Monster> monsters= new ArrayList<Monster>();
	private ArrayList<GameObject> gameobjects;
	
	private RandomMap level1;
	private RandomMap level2;
	
	private Map currentMap;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
				
		initLevel();
		initGraphics();
		initLevelActors(); //FONCTION DE TEST
		
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
	
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public void initLevelActors(){
		player = new Player("Couillon", 1, 1, 1, this);
		for (int i=0; i<numberOfMonsters; i++){
			Monster newMonster= new Monster("src/model/gameElements/zombie.png", 70+20*i, 70+80*i, 50, 200, 0.5, this, new Rectangle(8,0,15,31));
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
	
}
		
	




