package model;


import model.graphicElements.*;
import view.FontLoader;
import view.GamePanel;
import view.GameWindow;
import view.level.Level;
import view.level.LevelPanel;
import view.menu.MainMenu;

import java.awt.Rectangle;
import java.util.ArrayList;

import controls.PlayerControls;
import model.gameElements.*;

public class Game {
	
	private GameWindow gameWindow;
	private Player player;
	private ArrayList<Monster> monsters= new ArrayList<Monster>();
	private int numberOfMonsters=5;
	private GamePanel gamePanel;
	private ArrayList<GameObject> gameobjects;
	
	private Level level1;
	private Level level2;
	
	private LevelPanel currentLevelPanel;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		initLevelGraphics();
		//initMenu();
		initLevelActors(); //FONCTION DE TEST
		
	}
	
	public void initLevelGraphics() {
		FontLoader.loadGameFont();	//Permet d'utiliser la police du jeu
		gameWindow = new GameWindow(); //Affiche la fenêtre principale
		//gamePanel = new GamePanel(); //Lance le contenu de la fenetre --> déjà appelé dans gameWindow!
		level1= new Level(this); //Ajoute un level au contenu
		level1.activate();
		currentLevelPanel = level1;
		gameWindow.repaint();
		gameWindow.getGamePanel().repaint();
		level1.repaint();
		
		
		
	}
	
	public void initMenu() {
		new MainMenu(gameWindow.getGamePanel());
	}
	
	public LevelPanel getLevelPanel(){
		return currentLevelPanel;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
//	public void initLevel1() {
//		TileLibrary.initImage();
//		Level level1 = new Level(gameWindow.getGamePanel());
//		level1.activate();
//		
//		player = new Player("Couillon", 1, 1, 1, level1);
//		gameWindow.getFocusOwner().addKeyListener(new PlayerControls(player));
//		
//		//GameObject go = new Axe("axe",25, 45,10,10, "src/gameElements/axe.png", level);
//		//level.setComponentZOrder(go, 0);
//		
//		//Monster monster=new Monster("src/gameElements/zombie.png", 80, 100, 50, 200, 0.5, level1);
//		
//		ArrayList<Monster> monsters=level1.getMonsters();
//		for (int i=0; i<monsters.size(); i++){
//			level1.setComponentZOrder(monsters.get(i), 0);
//		}
//		level1.setComponentZOrder(player, 0);
//		
//	}
	
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
	
}
		
	




