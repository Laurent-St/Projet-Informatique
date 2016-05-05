/*
 * CLASSE PRINCIPALE CONTENANT LE JEU
 * Contient
 * -la m�thode main qui initialise les diff�rentes parties du jeu
 * -les m�thodes qui permettent de passer entre les niveaux
 * -les m�thodes de s�rialisation
 */


package model;

import model.graphicElements.*;
import model.map.BossMap;
import model.map.Map;
import model.map.RandomMap;
import view.FontLoader;
import view.GamePanel;
import view.GameWindow;
import view.level.LevelPanel;
import view.menu.InstructionsMenu;
import view.menu.MainMenu;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import controls.PlayerControls;
import model.gameElements.*;

public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private transient GameWindow gameWindow;
	private transient GamePanel gamePanel;
	private transient LevelPanel levelPanel;
	private transient MainMenu mainMenu;
	private transient InstructionsMenu instructionsMenu;
	private transient Thread monsterAI;
	
	private Player player;
	private transient PlayerControls playerControls;
	
	//Permet de stocker les 10 terrains du jeu
	private ArrayList<Map> levels = new ArrayList<Map>(); 
	private Map currentMap;
	
	public static void main(String[] args) {
		
		//Initialisation de la police de jeu et des graphismes du terrain
		FontLoader.loadGameFont();
		TileLibrary.initImage();
		new Game();
	}
	
	public Game() {
		initGUI();
		initMenu();
	}
	
	public void play(boolean loadGame) {
		
		//M�thode appel�e pour d�marrer la partie
		//Si loadGame est true, il y a une restauration de la partie pr�c�dente
		
		if(!loadGame) {
			initLevel();
			initPlayer();
			initAI();
			getCurrentMap().initActorsAndObjects();
		} else {
			restoreGame();
			initAI();
		}
		
		//Les menus sont retir�s et les graphismes et les contr�les du jeu sont initialis�s
		gamePanel.removeMenus();
		initGraphics();
		initControls();
		
		//Initialisation des panneaux d'inventaires et de statistiques du joueur
		gamePanel.initInventoryWindow(player.getInventory());
		gamePanel.initStatsPanel(player);
	}
	
	public void initGUI() {
		//M�thode qui g�re l'initialisation de la fen�tre de jeu
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel); //Affiche la fen�tre principale
	}
	
	public void initMenu(){
		//M�thode qui g�re l'initialisation des menus de jeu
		instructionsMenu = new InstructionsMenu(this);
		mainMenu = new MainMenu(this);
		gamePanel.setMenu(mainMenu);
	}
	
	public void initLevel() {
		//M�thode qui initialise le premier niveau � afficher
		RandomMap level1 = new RandomMap(1,this);
		levels.add(level1);
		setCurrentMap(level1);
	}
	
	public void initGraphics() {
		//M�thode qui initialise la vue du terrain et des GameObjects
		levelPanel = new LevelPanel(this, getCurrentMap());
	}

	public void initControls() {
		//M�thode initialisant les contr�les
		playerControls = new PlayerControls(player);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		gamePanel.addKeyListener(playerControls);
	}
	
	
	public void initPlayer(){
		//Cr�e le joueur et lui donne 2 potions.
		player = new Player(0, 100, 100, this);
		player.getInventory().setInInventory(new HealthPotion(0,0,50,this));
		player.getInventory().setInInventory(new ManaPotion(0,0,50,this));
		
		//EPEE PAR DEFAUT
		Sword sword = new Sword("epee",0,0,this, player);
		player.equipHandWeapon(sword);
		
		//FIREBALL PAR DEFAUT
		FireballWeapon fw = new FireballWeapon(this, player);
		player.equipThrowableWeapon(fw);
	}
	
	public void initAI() {
		//Initialisation du thread des monstres
		monsterAI = new Thread(new MonsterAIRunnable(this));
		monsterAI.start();
	}

	public GameWindow getGameWindow() {	
		return gameWindow;
	}
	
	public Map getCurrentMap() {
		return this.currentMap;
	}

	public void setCurrentMap(Map map) {
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
		
		//Permet de passer au niveau indiqu� par levelNum. Celui-ci correspond � une map stock�e dans la liste levels

		//Mise en pause des gameObjects ayant un thread
		getCurrentMap().stopAllThreads();
		
		int currentLevelNum = getCurrentMap().getLevelNum();
		
		//Cr�ation d'une nouvelle map si la map appel�e est inexistante. Sinon, une carte existante est charg�e
		if(levelNum>levels.size()) {
			Map newLevel;
			if(levelNum%5==0) {
				newLevel = new BossMap(levelNum,this);
			} else {
				newLevel = new RandomMap(levelNum,this);
			}
			levels.add(newLevel);
			newLevel.initActorsAndObjects();
			setCurrentMap(newLevel);
		} else {
			setCurrentMap(levels.get(levelNum-1));
			getCurrentMap().resumeAllThreads();
		}
		
		levelPanel.setMap(getCurrentMap());
		
		//T�l�portation du joueur apr�s avoir pass� la porte
		if(getCurrentMap().getLevelNum()>currentLevelNum) {
			getCurrentMap().tryToTeleport(player, new Point(40,30));
		} else {
			getCurrentMap().tryToTeleport(player, new Point(900,620));
		}
	}
	
	public void saveGame(){
		//Utilise le m�canisme de s�rialisation. Sauvegarde l'ensemble des �l�ments du jeu.
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
		//Restaure la sauvegarde. Il faut relancer tous les Thread manuellement car ceux-ci ne sont pas
		//s�rialisables et sont donc sp�cifi�s "transient".
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
		
	




