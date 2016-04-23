package view.level;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JPanel;

import animation.GraphicsClock;
import model.Game;
import model.gameElements.Actor;
import model.gameElements.Monster;
import model.gameElements.Player;
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
	
	private GraphicsClock graphicsClock;

	
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
		graphicsClock = new GraphicsClock(15,this);
	}
	
	protected void paintComponent(Graphics g) {
		paintActors(g);
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
	
	public Game getGame() {
		return this.game;
	}
	
	public void paintActors(Graphics g) {
		ArrayList<Monster> monsters = getGame().getMonsters();
		Player player = getGame().getPlayer();
		for(Monster m : monsters) {
			paintActor(g,m);
		}
		paintActor(g,player);
	}
	public void paintActor(Graphics g, Actor a) {
		this.repaint(new Rectangle(a.getX()-10, a.getY()-10,50,50));
			int xcount = 0;
			if(a.getMovingState()!="null") {
				xcount = a.getAnimationCount();
			}
			int ycount = 0;
			if(a.getOrientation()=="up") {
				ycount = 2;
			} else if (a.getOrientation()=="left") {
				ycount = 1;
			} else if (a.getOrientation()=="right") {
				ycount = 3;
			}
			g.drawImage(a.getImage(), a.getX(), a.getY(), a.getX()+32, a.getY()+32,
					32*xcount, ycount*32, 32*xcount+32, ycount*32+32,
					null);
			System.out.println("paint monster");
			//g.drawImage(this.getImage(), 0, 0, 31, 31,
					//32*xcount, ycount*32, 32*xcount+32, ycount*32+32,
					//null);
	}
}
