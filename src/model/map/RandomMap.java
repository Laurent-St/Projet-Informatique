/*
 * Type de map permettant une génération aléatoire de terrain avec apparition de monstres, pièges et objets.
 * Cette classe est instanciable !
 * Difficulté de jeu proportionnelle au numéro du niveau.
 */

package model.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import model.Game;
import model.gameElements.Axe;
import model.gameElements.Bow;
import model.gameElements.FireballWeapon;
import model.gameElements.Sword;
import model.graphicElements.Floor;
import model.graphicElements.PoisonousTile;
import model.graphicElements.Wall;

public class RandomMap extends Map {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Rectangle> rooms; //Salles générées aléatoirement
	private ArrayList<Point> holes; //Passages entre les salles générés aléatoirement
	private Game game;
	
	private int numberOfMonsters=10;
	
	public RandomMap(int levelNum, Game game) {
		super(levelNum, game);
		
		//Création du sol
		this.fill(new Rectangle(0,0,getLevelWidth(),getLevelHeight()), new Floor(game));
		
		rooms = new ArrayList<Rectangle>();
		rooms.add(this.getLevelBounds()); //Première salle = terrain entier
		holes = new ArrayList<Point>();
		
		//Génération du terrain
		generateMaze(0, rooms, holes);
		renderMaze(rooms, holes);
		initDoors(); //Portes positionnées à 2 endroits fixes, fonction définie dans Map
		initTraps();
	}

	public void initActorsAndObjects() {
		
		//Cette fonction doit être appelée par le Game afin de générer les monstres et objets de la map.
		//L'appel ne se fait pas dans le constructeur car certains d'objets du game seraient encore nulls.
		
		generateZombies(numberOfMonsters);
		initGameObjects();
	}
	
	public void initGameObjects(){
		
		//Apparition aléatoire d'objets sur le terrain
		
		Random rnd = new Random();
		int randomParameter = 3; // (randomParameter)^-1 de probabilité d'apparition d'un objet particulier
		if(rnd.nextInt(randomParameter)==1) {
			Axe axe= new Axe("hache", 0, 0, getGame(), getGame().getPlayer());
			addCollectableObject(axe);
			tryToTeleport(axe, randomPoint());
		}
		if(rnd.nextInt(randomParameter)==1) {
			Bow bow = new Bow(getGame(),getGame().getPlayer());
			addCollectableObject(bow);
			tryToTeleport(bow, randomPoint());
		}
		if(rnd.nextInt(randomParameter)==1) {
			Sword sword = new Sword("epee",0,0,getGame(), getGame().getPlayer());
			addCollectableObject(sword);
			tryToTeleport(sword,randomPoint());
		}
		if(rnd.nextInt(randomParameter)==1) {
			FireballWeapon fbw = new FireballWeapon(getGame(),getGame().getPlayer());
			addCollectableObject(fbw);
			tryToTeleport(fbw,randomPoint());
		}
	}
	
	public Point randomPoint() {
		
		//Renvoie un point aléatoire sur la map
		
		Random rnd = new Random();
		Point p = new Point();
		p.setLocation(rnd.nextInt(getLevelWidth()*20-40)+20, rnd.nextInt(getLevelHeight()*20-40)+20);
		return p;
	}
	


	public void generateMaze(int step, ArrayList<Rectangle> subdivisions, ArrayList<Point> holes) {
		
		//ALGORITHME DE GENERATION DE TERRAIN PAR DIVISIONS RECURSIVES
		//Les rectangles correspondant aux salles sont récusivement divisées en nouvelles salles jusqu'à ce que step==stepMax
		
		int stepMax = 3; //Définit la ramification des subdivisions
		Random rnd = new Random();
		ArrayList<Rectangle> newObjects = new ArrayList<Rectangle>();
		
		//Cette boucle divise chaque rectangle en deux
		for(int i=0; i<subdivisions.size(); i++) {
			Rectangle room = subdivisions.get(i);
			
			int width = (int) room.getWidth();
			int height = (int) room.getHeight();
			int x = (int) room.getX();
			int y = (int) room.getY();
			int orientation = step%2; //Défini si la division est horizontale ou verticale
			
			Rectangle r1 = new Rectangle();
			Rectangle r2 = new Rectangle();
			boolean newRooms = false;
			
			if(orientation==0 && height>=9) { 	//Division horizontale avec condition sur la taille des nouvelles salles
												//Si condition non-remplie, il n'y a pas de création de nouvelles salles
				int subHeight = rnd.nextInt(height-6)+4;
				r1.setBounds(x, y, width, subHeight);
				r2.setBounds(x, y+subHeight, width, height-subHeight);
				
				int holeX = rnd.nextInt(width-1)+x+1;
				holes.add(new Point(holeX,y+subHeight)); // Ajout d'un passage entre les deux salles
				
				newRooms = true;
				
			} else if (orientation==1 && width>=9) { 	//Division verticale avec condition sur la taille des nouvelles salles
														//Si condition non-remplie, il n'y a pas de création de nouvelles salles
				int  subWidth = rnd.nextInt(width-6)+4;
				r1.setBounds(x, y, subWidth, height);
				r2.setBounds(x+subWidth, y, width-subWidth, height);
				
				int holeY = rnd.nextInt(height-1)+y+1;
				holes.add(new Point(x+subWidth, holeY)); // Ajout d'un passage entre les deux salles
				
				newRooms = true;
			}
			if(newRooms) {
				newObjects.add(r1);
				newObjects.add(r2);
			} else {
				newObjects.add(room);
			}	
		}
		
		//Appel récursif
		if(step==stepMax) {
			this.rooms = newObjects;
			this.holes = holes;
		} else {
			generateMaze(step+1, newObjects, holes);
		}
	}
	
	
	public void renderMaze(ArrayList<Rectangle> rooms, ArrayList<Point> holes) {
		
		//PERMET D'AFFICHER LE LABYRINTHE DE FACON COHERENTE
		//Traduction Salles et Passages -> tuiles
		//Permet aussi d'orienter correctement les murs selon leur situation sur le terrain
		
		ArrayList<Point> corners = new ArrayList<Point>(); //Tous les murs à réorienter seront placés dans cette liste afin de réduire le temps d'execution 
		
		//MURS PRINCIPAUX
		for(int i=0;i<rooms.size();i++) {
			Rectangle room = rooms.get(i);
			this.makeBorder(room);
			corners.add(new Point((int)room.getX(), (int)room.getY()));
			corners.add(new Point((int)room.getMaxX(), (int)room.getY()));
			corners.add(new Point((int)room.getX(), (int)room.getMaxY()));
			corners.add(new Point((int)room.getMaxX(),(int)room.getMaxY()));
		}
		
		//INTERSECTIONS DES MURS
		for(int j=0;j<corners.size();j++) {
			Point corner = corners.get(j);
			Wall w = new Wall(determineWallOrientation(corner), game);
			setTileAt(corner.x,corner.y,w);
		}
		
		//TROUS ENTRE MURS
		for(int k=0;k<holes.size();k++) {
			Point hole = holes.get(k);
			int x = hole.x;
			int y = hole.y;
			setTileAt(x,y,new Floor(game)); //Trou placé à la position
			
			//Défini une liste des 4 positions adjacentes du trou
			Point[] positions = {new Point(x+1,y), new Point(x-1,y), new Point(x,y+1), new Point(x,y-1)};
			
			//Ces 4 positions adjacentes sont également trouées afin de garantir le passage par tous les trous du terrain
			//(Condition suffisante pour que toutes les salles soient visitables)
			for(int l=0;l<4;l++) {
				
				//Condition permettant de ne pas trouer la bordure extérieure du terrain
				if(onBorder(positions[l])==false) {
					setTileAt(positions[l].x,positions[l].y,new Floor(game));
				} else {
					setTileAt(positions[l].x,positions[l].y, new Wall(determineWallOrientation(positions[l]), game));
				}
				
				//Définition d'une liste de cases adjacentes à la case trouée afin de vérifier, dans le cas où c'est un mur, si son orientation est toujours correcte (du à l'apparition d'un nouveau trou)
				Point[] subPositions = {new Point(1,0), new Point(0,1), new Point(-1,0), new Point(0,-1)};
				for(int n=0;n<4;n++) {
					Point subPoint = positions[l].getLocation();
					subPoint.translate(subPositions[n].x, subPositions[n].y);
					if(getTileAt(subPoint.x,subPoint.y).getType()=="wall") {
						setTileAt(subPoint.x,subPoint.y,new Wall(determineWallOrientation(subPoint), game));
					}
				}
			}
		}
	}
	
	private void initTraps() {
		
		//Apparition aléatoire de pièges à poison
		//6 paquets de maximum 6 cases empoisonnées
		
		Random rnd = new Random();
		for(int i=0;i<6;i++) {
			int x = rnd.nextInt(getLevelWidth()-2)+1;
			int y = rnd.nextInt(getLevelHeight()-2)+1;
			for(int j=0;j<6;j++) {
				int nx = x+rnd.nextInt(5)-2;
				int ny = y+rnd.nextInt(5)-2;			
				if(getTileAt(nx,ny).getType()=="floor" && (nx>=5 || ny >=5)) {;
					setTileAt(nx,ny,new PoisonousTile(getGame()));
				}
			}
		}
	}

}
