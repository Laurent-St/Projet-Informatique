package hackAndSlash;

import gameElements.GameObject;
import gameElements.Monster;

import java.awt.Dimension;

import controls.PlayerControls;
import gameElements.Player;

import gui.FontLoader;
import gui.GameWindow;
import gui.Tile;
import gui.level.LevelPanel;
import gui.menu.MainMenu;
import levels.Level1;

public class Game {
	
	private static GameWindow gameWindow;
	
	public static void main(String[] args) {
		initGraphics();
		//initMenu();
		initLevel1(); //FONCTION DE TEST
	}
	
	public static void initGraphics() {
		FontLoader.loadGameFont();	//Permet d'utiliser la police du jeu
		gameWindow = new GameWindow();
	}
	
	public static void initMenu() {
		new MainMenu(gameWindow.getGamePanel());
	}
	
	public static void initLevel1() {
		Tile.initImage();
		LevelPanel level = new LevelPanel(new Level1(),gameWindow.getGamePanel());
		level.activate();
		new GameObject(25, 45, "src/gameElements/axe.png",level);
		
		//Player player = new Player("Couillon", 1, 1, 1, level);
		//gameWindow.getFocusOwner().addKeyListener(new PlayerControls(player));
		//level.setComponentZOrder(player, 0);
		
		GameObject go = new GameObject(25, 45, "src/gameElements/axe.png",level);
		level.setComponentZOrder(go, 0);
		
		Monster monster=new Monster("gros monstre", 80, 230, 50, 200, 5, level,new Dimension(3,2));
		level.setComponentZOrder(monster, 0);
	}

}
