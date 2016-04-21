package model;


import model.graphicElements.*;
import view.FontLoader;
import view.GameWindow;
import view.level.Level;
import view.menu.MainMenu;

import java.util.ArrayList;

import controls.PlayerControls;
import model.gameElements.*;

public class Game {
	
	private GameWindow gameWindow;
	private Player player;
	private ArrayList<Monster> monsters;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		initGraphics();
		//initMenu();
		initLevel1(); //FONCTION DE TEST
	}
	
	public void initGraphics() {
		FontLoader.loadGameFont();	//Permet d'utiliser la police du jeu
		gameWindow = new GameWindow();
	}
	
	public void initMenu() {
		new MainMenu(gameWindow.getGamePanel());
	}
	
	public void initLevel1() {
		TileLibrary.initImage();
		Level level1 = new Level(gameWindow.getGamePanel());
		level1.activate();
		
		player = new Player("Couillon", 1, 1, 1, level1);
		gameWindow.getFocusOwner().addKeyListener(new PlayerControls(player));
		
		//GameObject go = new Axe("axe",25, 45,10,10, "src/gameElements/axe.png", level);
		//level.setComponentZOrder(go, 0);
		
		//Monster monster=new Monster("src/gameElements/zombie.png", 80, 100, 50, 200, 0.5, level1);
		
		ArrayList<Monster> monsters=level1.getMonsters();
		for (int i=0; i<monsters.size(); i++){
			level1.setComponentZOrder(monsters.get(i), 0);
		}
		level1.setComponentZOrder(player, 0);
		
	}

}
