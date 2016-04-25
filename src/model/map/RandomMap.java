package model.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import model.Game;
import model.graphicElements.Floor;
import model.graphicElements.Wall;

public class RandomMap extends Map {
	
	private ArrayList<Rectangle> rooms;
	private ArrayList<Point> holes;
	
	public RandomMap() {
		this.fill(new Rectangle(0,0,this.getLevelWidth(),this.getLevelHeight()), new Floor());
		
		rooms = new ArrayList<Rectangle>();
		rooms.add(this.getLevelBounds());
		holes = new ArrayList<Point>();
		
		generateMaze(0, rooms, holes);
		renderMaze(rooms, holes);
		System.out.println("new Level");
	}
	


	//ALGORITHME DE GENERATION DE TERRAIN PAR DIVISIONS RECURSIVES
	public void generateMaze(int step, ArrayList<Rectangle> subdivisions, ArrayList<Point> holes) {
		int stepMax = 3;
		Random rnd = new Random();
		ArrayList<Rectangle> newObjects = new ArrayList<Rectangle>();
		
		for(int i=0; i<subdivisions.size(); i++) {
			Rectangle room = subdivisions.get(i);
			
			int width = (int) room.getWidth();
			int height = (int) room.getHeight();
			int x = (int) room.getX();
			int y = (int) room.getY();
			//int orientation = rnd.nextInt(2);
			int orientation = step%2;
			
			Rectangle r1 = new Rectangle();
			Rectangle r2 = new Rectangle();
			boolean newRooms = false;
			
			if(orientation==0 && height>=9) { //HORIZONTAL
				int subHeight = rnd.nextInt(height-6)+4;
				r1.setBounds(x, y, width, subHeight);
				r2.setBounds(x, y+subHeight, width, height-subHeight);
				
				int holeX = rnd.nextInt(width-1)+x+1;
				holes.add(new Point(holeX,y+subHeight));
				
				newRooms = true;
				
			} else if (orientation==1 && width>=9) { //VERTICAL
				int  subWidth = rnd.nextInt(width-6)+4;
				r1.setBounds(x, y, subWidth, height);
				r2.setBounds(x+subWidth, y, width-subWidth, height);
				
				int holeY = rnd.nextInt(height-1)+y+1;
				holes.add(new Point(x+subWidth, holeY));
				
				newRooms = true;
			}
			if(newRooms) {
				newObjects.add(r1);
				newObjects.add(r2);
			} else {
				newObjects.add(room);
			}	
		}
		if(step==stepMax) {
			this.rooms = newObjects;
			this.holes = holes;
		} else {
			generateMaze(step+1, newObjects, holes);
		}
	}
	
	
	//PERMET D'AFFICHER LE LABYRINTHE DE FACON COHERENTE
	public void renderMaze(ArrayList<Rectangle> rooms, ArrayList<Point> holes) {
		
		ArrayList<Point> corners = new ArrayList<Point>();
		
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
			Wall w = new Wall(determineWallOrientation(corner));
			setTileAt(corner.x,corner.y,w);
		}
		
		//TOURS ENTRE MURS
		for(int k=0;k<holes.size();k++) {
			Point hole = holes.get(k);
			int x = hole.x;
			int y = hole.y;
			setTileAt(x,y,new Floor());
			Point[] positions = {new Point(x+1,y), new Point(x-1,y), new Point(x,y+1), new Point(x,y-1)};
			
			for(int l=0;l<4;l++) {
				Point[] subPositions = {new Point(1,0), new Point(0,1), new Point(-1,0), new Point(0,-1)};
				if(onBorder(positions[l])==false) {
					setTileAt(positions[l].x,positions[l].y,new Floor());
				} else {
					setTileAt(positions[l].x,positions[l].y, new Wall(determineWallOrientation(positions[l])));
				}
				for(int n=0;n<4;n++) {
					Point subPoint = positions[l].getLocation();
					subPoint.translate(subPositions[n].x, subPositions[n].y);
					if(getTileAt(subPoint.x,subPoint.y).getType()=="wall") {
						setTileAt(subPoint.x,subPoint.y,new Wall(determineWallOrientation(subPoint)));
					}
				}
			}
		}
	}	

}
