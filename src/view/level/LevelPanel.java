package view.level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import animation.GraphicsClock;
import model.Game;
import model.gameElements.Actor;
import model.gameElements.GameObject;
import model.gameElements.HandWeapon;
import model.gameElements.Monster;
import model.gameElements.Player;
import model.gameElements.Projectile;
import model.gameElements.Weapon;
import model.graphicElements.TileLibrary;
import model.map.Map;
import view.GamePanel;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel {
	
	private Game game;
	private GamePanel gamePanel;
	private static Rectangle levelPanelBounds = new Rectangle(20,20,960,680);
	private Map map;
	
	private GraphicsClock clock;
	
	public LevelPanel(Game game, Map map) {
		this.game = game;
		setMap(map);
		gamePanel=game.getGameWindow().getGamePanel();
		this.setLayout(null);
		this.setBounds(getLevelPanelBounds());
		gamePanel.add(this);
		this.setVisible(true);
		System.out.println("activate");
		
		clock = new GraphicsClock(15,this);
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
	
	protected void paintComponent(Graphics g) {
		paintBackground(g);
		//paintGameObjects(g); //JE LAI DEJA RAJOUTE A TA PLACE ;)
		paintAllActors(g);
		paintWeapon(g);
		paintGameObjects(g);
	}
	
	public void paintBackground(Graphics g) {
		Image tilesImage = TileLibrary.getTilesImage();
		int size = TileLibrary.getTileSize();
		Map map = getMap();
		
		for(int i=0;i<map.getLevelWidth();i++) {
			for(int j=0;j<map.getLevelHeight();j++) {
				
				Rectangle r = TileLibrary.getBoundsOf(map.getTileAt(i, j));
				g.drawImage(tilesImage,
						i*size, j*size, (i+1)*size, (j+1)*size,
						(int)r.getX(),
						(int)r.getY(),
						(int)(r.getX()+r.getWidth()),
						(int)(r.getY()+r.getHeight()),
						null);
			}
		}
	}
	
	public void paintAllActors(Graphics g) {
		ArrayList<Monster> monsters = getGame().getMonsters();
		Player player = getGame().getPlayer();
		for(Monster m : monsters) {
			paintActor(g,m);
		}
		paintActor(g,player);
	}
	
	public void paintActor(Graphics g, Actor a) {
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
	}
	
	public void paintWeapon(Graphics g) {
		Player p = getGame().getPlayer();
		HandWeapon hw = p.getHandWeapon();
		if (hw!=null) {
			if(hw.inAttackMode()) {
				int c = hw.getAnimationCount();
				if(p.getOrientation()=="up") {
					g.drawImage(hw.getImage(), p.getX(), p.getY()-20, p.getX()+31,p.getY()+11,
						32*c,64,32*c+31,95,null);
				} else if (p.getOrientation()=="down") {
					g.drawImage(hw.getImage(), p.getX(), p.getY()+31+25, p.getX()+31,p.getY()+25,
							32*c,64,32*c+31,93,null);
				} else if (p.getOrientation()=="left") {
					g.drawImage(hw.getImage(), p.getX()+21, p.getY(), p.getX()-10,p.getY()+31,
							32*c,32,32*c+31,63,null);
				} else {
					g.drawImage(hw.getImage(), p.getX()+10, p.getY(), p.getX()+41,p.getY()+31,
							32*c,32,32*c+31,63,null);
				}
			}
		}
		
		ArrayList<Projectile> projectiles = getGame().getProjectiles();
		for (Projectile pro : projectiles) {
			if(pro.isTravelling()) {
				String d = pro.getDirection();
				if(d=="up") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,0,0,31,31,null);
				} else if (d=="right") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,32,0,63,31,null);
				} else if (d=="down") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,64,0,95,31,null);
				} else if (d=="left") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,96,0,127,31,null);
				}
			}
		}
	}
	
	public void paintGameObjects(Graphics g){
		if (game.getGameObjects()!=null){
			for (GameObject object: game.getGameObjects()){
				Image image=object.getImage();
				g.drawImage(image, (int)object.getXdouble(), (int)object.getYdouble(),(int)object.getHitbox().getWidth(), (int)object.getHitbox().getHeight(), null);
			}
		}
	}
	
	
	
}
