package model;

import model.graphicElements.*;
import model.map.Map;
import model.map.RandomMap;
import view.FontLoader;
import view.GamePanel;
import view.GameWindow;
import view.level.LevelPanel;
import view.menu.InstructionsMenu;
import view.menu.MainMenu;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import controls.PlayerControls;
import model.gameElements.*;

public class Game implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient GameWindow gameWindow;
	private transient GamePanel gamePanel;
	private transient LevelPanel levelPanel;
	private transient MainMenu mainMenu;
	private transient InstructionsMenu instructionsMenu;
	private transient Thread monsterAI;
	
	private Player player;
	private transient PlayerControls playerControls;
	
	private ArrayList<Map> levels = new ArrayList<Map>(); 
	private Map currentMap;
	
	public static void main(String[] args) {
		FontLoader.loadGameFont();
		TileLibrary.initImage();
		new Game();
	}
	
	public Game() {
		initGUI();
		initMenu();
	}
	
	public void play(boolean loadGame) {
		if(!loadGame) {
			initLevel();
			initPlayer();
			initAI();
			getCurrentMap().initActorsAndObjects();
		} else {
			restoreGame();
			initAI();
		}
		
		gamePanel.removeMenus();
		initGraphics();
		initControls();
		
		gamePanel.initInventoryWindow(player.getInventory());
		gamePanel.initStatsPanel(player);
	}
	
	public void initGUI() {
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel); //Affiche la fenêtre principale
	}
	
	public void initMenu(){
		instructionsMenu = new InstructionsMenu(this);
		mainMenu = new MainMenu(this);
		gamePanel.setMenu(mainMenu);
	}
	public void initLevel() {
		RandomMap level1 = new RandomMap(1,this);
		levels.add(level1);
		setCurrentMap(level1);
	}
	
	public void initGraphics() {
		levelPanel = new LevelPanel(this, getCurrentMap());
	}

	public void initControls() {
		playerControls = new PlayerControls(player);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		gamePanel.addKeyListener(playerControls);
	}
	
	
	public void initPlayer(){
		player = new Player(0, 100, 100, this);
		player.getInventory().setInInventory(new HealthPotion(0,0,50,this));
		player.getInventory().setInInventory(new ManaPotion(0,0,50,this));
	}
	
	public void initAI() {
		monsterAI = new Thread(new MonsterAIRunnable(this));
		monsterAI.start();
	}

	public GameWindow getGameWindow() {	
		return gameWindow;
	}
	
	public Map getCurrentMap() {
		return this.currentMap;
	}

	private void setCurrentMap(Map map) {
		this.currentMap = map;	
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public Player getPlayer() {
		return this.player;
	}
	
	public void backToMainMenu() {
		gamePanel.setMenu(mainMenu);
	}
	
	public void switchToInstructionsMenu() {
		gamePanel.setMenu(instructionsMenu);
	}

	public void changeLevel(int levelNum) {
		System.out.println("go to "+String.valueOf(levelNum));
		getCurrentMap().stopAllThreads();
		if(getCurrentMap().getLevelNum()<levelNum) {
			getCurrentMap().tryToTeleport(player, new Point(40,30));
		} else {
			getCurrentMap().tryToTeleport(player, new Point(900,620));
		}
		
		if(levelNum>levels.size()) {
			RandomMap newLevel = new RandomMap(levelNum,this);
			levels.add(newLevel);
			newLevel.initActorsAndObjects();
			setCurrentMap(newLevel);
		} else {
			setCurrentMap(levels.get(levelNum-1));
			getCurrentMap().resumeAllThreads();
		}
		
		levelPanel.setMap(getCurrentMap());
	}
	
	public void saveGame(){
		try{
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("save_game.serial"));
		oos.writeObject(this);
		oos.flush();
		oos.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public void restoreGame(){
		try{
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("save_game.serial"));
			Game savedGame = (Game) ois.readObject();
			this.levels = savedGame.levels;
			this.currentMap = savedGame.currentMap;
			this.player = savedGame.player;
			player.reloadAction(this);
			for(Map m : levels) {
				m.reloadAction(this);
			}
			ois.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
		
	




