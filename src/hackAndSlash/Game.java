package hackAndSlash;

import gameElements.Axe;
import gameElements.GameObject;
import gameElements.Monster;
import gameElements.Player;

import java.awt.Dimension;

import controls.PlayerControls;
import gui.FontLoader;
import gui.GameWindow;
import gui.TileLibrary;
import gui.level.LevelPanel;
import gui.menu.MainMenu;
import levels.Level;
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
		TileLibrary.initImage();
		LevelPanel level = new LevelPanel(new Level(),gameWindow.getGamePanel());
		level.activate();
		
		Player player = new Player("Couillon", 1, 1, 1, level);
		gameWindow.getFocusOwner().addKeyListener(new PlayerControls(player));
		level.setComponentZOrder(player, 0);
		
		//GameObject go = new Axe("axe",25, 45,10,10, "src/gameElements/axe.png", level);
		//level.setComponentZOrder(go, 0);
		
		Monster monster=new Monster("src/gameElements/zombie.png", 78, 105, 50, 200, 0.25, level);
		level.setComponentZOrder(monster, 0);
	}

}
