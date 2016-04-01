package hackAndSlash;

import gui.FontLoader;
import gui.GameWindow;
import gui.MainMenu;

public class Game {
	
	private static GameWindow gameWindow;
	
	public static void main(String[] args) {
		initGraphics();
		initMenu();
	}
	
	public static void initGraphics() {
		FontLoader.loadGameFont();	//Permet d'utiliser la police du jeu
		gameWindow = new GameWindow();
	}
	
	public static void initMenu() {
		new MainMenu(gameWindow.getGamePanel());
	}

}
