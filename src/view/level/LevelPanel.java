package view.level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import animation.GraphicsClock;
import model.Game;
import model.gameElements.Actor;
import model.gameElements.CollectableObject;
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
		paintObjects(g);
		paintAllActors(g);
		paintWeapon(g);
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
		ArrayList<Monster> monsters = getMap().getMonsters();
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
					g.drawImage(hw.getImage(), hw.getX(), hw.getY(), hw.getX()+31,hw.getY()+31,
						32*c,64,32*c+31,95,null);
				} else if (p.getOrientation()=="down") {
					g.drawImage(hw.getImage(), hw.getX(), hw.getY()+31, hw.getX()+31,hw.getY(),
							32*c,64,32*c+31,93,null);
				} else if (p.getOrientation()=="left") {
					g.drawImage(hw.getImage(), hw.getX()+31, hw.getY(), hw.getX(),hw.getY()+31,
							32*c,32,32*c+31,63,null);
				} else {
					g.drawImage(hw.getImage(), hw.getX(), hw.getY(), hw.getX()+31,hw.getY()+31,
							32*c,32,32*c+31,63,null);
				}
			}
		}
		
		ArrayList<Projectile> projectiles = getMap().getProjectiles();
		for (Projectile pro : projectiles) {
			if(!pro.isDead()) {
				String d = pro.getDirection();
				if(d=="up") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,0,32,31,63,null);
				} else if (d=="right") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,32,32,63,63,null);
				} else if (d=="down") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,64,32,95,63,null);
				} else if (d=="left") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,96,32,127,63,null);
				}
			}
		}
	}
	
	public void paintObjects(Graphics g){
		for (GameObject object: getMap().getGameObjects()){
			Image image=object.getImage();
			g.drawImage(image, object.getX(), object.getY(),object.getX()+(int)object.getHitbox().getWidth(), object.getY()+(int)object.getHitbox().getHeight(),
					0,0,31,31,null);
		}
		for (CollectableObject object: getMap().getCollectableObjects()){
			Image image=object.getImage();
			g.drawImage(image, object.getX(), object.getY(),object.getX()+(int)object.getHitbox().getWidth(), object.getY()+(int)object.getHitbox().getHeight(),
					0,0,31,31,null);
		}
	}
}
