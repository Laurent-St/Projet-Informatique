package hackAndSlash;

import gameElements.Monster;
import gameElements.Player;

import controls.PlayerControls;
import gui.FontLoader;
import gui.GameWindow;
import gui.TileLibrary;
import gui.level.LevelPanel;
import gui.menu.MainMenu;
import levels.Level;

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
		LevelPanel level = new LevelPanel(gameWindow.getGamePanel());
		level.activate();
		
		Player player = new Player("Couillon", 1, 1, 1, level);
		gameWindow.getFocusOwner().addKeyListener(new PlayerControls(player));
		
		//GameObject go = new Axe("axe",25, 45,10,10, "src/gameElements/axe.png", level);
		//level.setComponentZOrder(go, 0);
		
		Monster monster=new Monster("src/gameElements/zombie.png", 20, 20, 50, 200, 0.5, level);
		
		level.setComponentZOrder(monster, 0);
		level.setComponentZOrder(player, 0);
	}

}
