package hackAndSlash;

import gui.FontLoader;
import gui.GameWindow;
import gui.LevelPanel;
import gui.MainMenu;
import gui.Tile;
import levels.Level1;

public class Game {
	
	private static GameWindow gameWindow;
	
	public static void main(String[] args) {
		initGraphics();
		initMenu();
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
	}

}
