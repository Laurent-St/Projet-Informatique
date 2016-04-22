package view.level;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Game;
import model.gameElements.Monster;
import model.graphicElements.Floor;
import model.graphicElements.Tile;
import model.graphicElements.TileLibrary;
import model.graphicElements.Wall;
import model.map.Map;
import view.GamePanel;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel {
	
	private Game game;
	private GamePanel gamePanel;
	private LevelBackground levelBackground;
	private static Rectangle levelPanelBounds = new Rectangle(20,20,960,680);
	private Map map;

	
	public LevelPanel(Game game, Map map) {
		this.game = game;
		setMap(map);
		gamePanel=game.getGameWindow().getGamePanel();
		this.setLayout(null);
		this.setBounds(getLevelPanelBounds());
		levelBackground = new LevelBackground(this);
		activate();
	}

	
	public void activate() {
		System.out.println("activate");
		gamePanel.addPanel(this);
		this.setVisible(true);
		levelBackground.repaint();
		this.repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//DESSINER TOUS LES GAMEOBJECTS
	}
	
	public Rectangle getLevelPanelBounds() {
		return levelPanelBounds;
	}
	
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}
	
	public Map getMap() {
		return this.map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
}
