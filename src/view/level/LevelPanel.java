/*
 * Panneau d'affichage du niveau. Celui-ci dessine tous les �l�ments faisant partie du plateau de jeu (Player + Map)
 * Doit �tre li� � une Map (r�cup�ration du terrain, monstres, objets)
 * Doit �tre li� � un Game (r�cup�ration du joueur)
 * Il est possible de changer la map � afficher
 * N'utilise pas de patron observateur, le rafraichissement de l'affichage se fait selon une horloge
 */

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
import model.graphicElements.SpecialFX;
import model.graphicElements.TileLibrary;
import model.map.Map;
import view.GamePanel;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel {
	
	private Game game;
	private GamePanel gamePanel;
	private static Rectangle levelPanelBounds = new Rectangle(20,20,960,680);
	private Map map;
	
	@SuppressWarnings("unused")
	private GraphicsClock clock;
	
	public LevelPanel(Game game, Map map) {
		
		//D�finition de la mise en page du LevelPanel
		
		this.game = game;
		setMap(map);	//la Map est mise dans le LevelPanel
		gamePanel=game.getGameWindow().getGamePanel();
		this.setLayout(null);
		this.setBounds(getLevelPanelBounds());
		gamePanel.add(this);
		this.setVisible(true);
		
		clock = new GraphicsClock(15,this);	//Thread qui va rafra�chir tous les �l�ments pr�sents dans le LevelPanel
											//en appelant toutes les 15 millisecondes la m�thode paintComponent() d�finie ci-dessous.
	}
	
	public Rectangle getLevelPanelBounds() {
		//Donne les limites d'affichage du terrain
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
		
		//Affichage successif des diff�rents �l�ments de la map, est appel� par la GraphicsClock
		//Toutes ces fonctions prennent part du polymorphisme des GameObjects (tous les objets ramassables sont trait�s comme des CollectableObject etc.)
		paintBackground(g);
		paintObjects(g);
		paintAllActors(g);
		paintWeapon(g);
		paintSpecialFX(g);
	}
	
	public void paintBackground(Graphics g) {
		//DESSIN DE L'ARRIERE-PLAN
		
		Image tilesImage = TileLibrary.getTilesImage(); //R�cup�ration de l'image contenant tous les types de tuiles
		int size = TileLibrary.getTileSize();
		Map map = getMap();
		
		//Balayage de toute la matrice du terrain
		for(int i=0;i<map.getLevelWidth();i++) {
			for(int j=0;j<map.getLevelHeight();j++) {
				
				Rectangle r = TileLibrary.getBoundsOf(map.getTileAt(i, j)); //R�cup�ration de l'image pr�cise de la tuile � afficher, correspondant au type de tuile (portion d'image de tilesImage)
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
		//Affiche les Monster et le Player
		ArrayList<Monster> monsters = getMap().getMonsters();
		Player player = getGame().getPlayer();
		for(Monster m : monsters) {
			paintActor(g,m);
		}
		paintActor(g,player);
	}
	
	public void paintActor(Graphics g, Actor a) {
		//Effectue le dessin d'un Actor (player, monster)
		//Cette fonction prend en compte l'�tat d'animation de l'actor
		
		int xcount = 0; //Horizontalement dans l'image de l'actor = animation du d�placement
		if(a.getMovingState()!="null") {
			xcount = a.getAnimationCount();
		}
		
		int ycount = 0; //Verticalement dans l'image de l'actor = direction du d�placement
		if(a.getOrientation()=="up") {
			ycount = 2;
		} else if (a.getOrientation()=="left") {
			ycount = 1;
		} else if (a.getOrientation()=="right") {
			ycount = 3;
		}
		
		int size=a.getActorSize(); //D�pend de la taille de l'actor (exemple: le boss est plus grand que le joueur)
		g.drawImage(a.getImage(), a.getX(), a.getY(), a.getX()+size, a.getY()+size,
				size*xcount, ycount*size, size*xcount+size, ycount*size+size,
				null);
	}
	
	public void paintWeapon(Graphics g) {
		
		//Affiche l'arme du Player lorsque celui-ci attaque. L'arme est affich�e suivant l'orientation du Player.
		Player p = getGame().getPlayer();
		HandWeapon hw = p.getHandWeapon();
		if (hw!=null) {
			if(hw.inAttackMode()) {
				int c = hw.getAnimationCount();  //Prend en compte l'�tat d'animation de l'arme de main
				if(p.getOrientation()=="up") {
					g.drawImage(hw.getImage(), hw.getX(), hw.getY(), hw.getX()+32,hw.getY()+32,
						32*c,64,32*c+32,96,null);
				} else if (p.getOrientation()=="down") {
					g.drawImage(hw.getImage(), hw.getX(), hw.getY()+32, hw.getX()+32,hw.getY(),
							32*c,64,32*c+32,94,null);
				} else if (p.getOrientation()=="left") {
					g.drawImage(hw.getImage(), hw.getX()+32, hw.getY(), hw.getX(),hw.getY()+32,
							32*c,32,32*c+32,64,null);
				} else {
					g.drawImage(hw.getImage(), hw.getX(), hw.getY(), hw.getX()+32,hw.getY()+32,
							32*c,32,32*c+32,64,null);
				}
			}
		}
		
		//Affichage des projectiles en mouvement. Prend �videmment en compte la direction dans laquelles ils se d�placent.
		ArrayList<Projectile> projectiles = getMap().getProjectiles();
		for (Projectile pro : projectiles) {
			if(!pro.isDead()) {
				String d = pro.getDirection();
				if(d=="up") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,0,32,32,64,null);
				} else if (d=="right") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,32,32,64,64,null);
				} else if (d=="down") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,64,32,96,64,null);
				} else if (d=="left") {
					g.drawImage(pro.getImage(), pro.getX(), pro.getY(), pro.getX()+32,pro.getY()+32,96,32,128,64,null);
				}
			}
		}
	}
	
	public void paintObjects(Graphics g){
		//Affiche tous les objets pr�sents sur la Map (GameObjects et CollectableObjects ramassables)
		//Ceux-ci ne requi�rent pas d'animation
		
		for (GameObject object: getMap().getGameObjects()){
			Image image=object.getImage();
			g.drawImage(image, object.getX(), object.getY(),object.getX()+(int)object.getHitbox().getMaxX(), object.getY()+(int)object.getHitbox().getMaxY(),
					0,0,32,32,null);
		}
		for (CollectableObject object: getMap().getCollectableObjects()){
			Image image=object.getImage();
			g.drawImage(image, object.getX(), object.getY(),object.getX()+(int)object.getHitbox().getMaxX(), object.getY()+(int)object.getHitbox().getMaxY(),
					0,0,32,32,null);
		}
	}
	
	public void paintSpecialFX(Graphics g) {
		//Affiche les effets sp�ciaux de la map (prise de d�gat, levelup)
		//Prend compte de leur �tat d'animation
		
		for(GameObject sfx: getMap().getSpecialFX()) {
			if(sfx!=null) {
				Image image = sfx.getImage();
				int size = ((SpecialFX) sfx).getSize();
				int c = sfx.getAnimationCount();
				g.drawImage(image, sfx.getX(), sfx.getY(), sfx.getX()+(int)sfx.getHitbox().getMaxX(), sfx.getY()+(int)sfx.getHitbox().getMaxY(),
						size*c,0,size*c+size,size,null);
			}
		}
	}
}
