package hackAndSlash;

import gui.FontLoader;
import gui.GameWindow;

public class Game {
	
	private static GameWindow gameWindow;
	
	public static void main(String[] args) {
		initGraphics();
	}
	
	public static void initGraphics() {
		FontLoader.loadGameFont();
		gameWindow = new GameWindow();
	}

}
