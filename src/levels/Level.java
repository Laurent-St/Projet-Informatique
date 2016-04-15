package levels;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import gui.Floor;

public class Level extends LevelData {
	
	private ArrayList<Rectangle> rooms;
	private ArrayList<Point> holes;
	
	public Level() {
		this.fill(new Rectangle(0,0,this.getLevelWidth(),this.getLevelHeight()), new Floor());
		this.makeBorder();
		
		rooms = new ArrayList<Rectangle>();
		rooms.add(new Rectangle(0,0,this.getLevelWidth(),this.getLevelHeight()));
		holes = new ArrayList<Point>();
		
		generateMaze(0, rooms, holes);
		
	}
	
	public void generateMaze(int step, ArrayList<Rectangle> subdivisions, ArrayList<Point> holes) {
		int stepMax = 5;
		Random rnd = new Random();
		ArrayList<Rectangle> newObjects = new ArrayList<Rectangle>();
		
		for(int i=0; i<subdivisions.size(); i++) {
			Rectangle room = subdivisions.get(i);
			
			int width = (int) room.getWidth();
			int height = (int) room.getHeight();
			int orientation = rnd.nextInt(1);
			
			Rectangle r1 = new Rectangle();
			Rectangle r2 = new Rectangle();
			boolean newRooms = false;
			
			if(orientation==0 && height>=7) { //HORIZONTAL
				int subHeight = rnd.nextInt(height-6)+4;
				r1.setBounds((int)room.getX(), (int)room.getY(), width, subHeight);
				r2.setBounds((int)room.getX(), (int)room.getY()+subHeight, width, height-subHeight);
				
				int holeX = rnd.nextInt(width-1)+(int)room.getX()+1;
				holes.add(new Point(holeX,(int) (room.getY()+subHeight)));
				
				newRooms = true;
				
			} else if (orientation==1 && width>=7) { //VERTICAL
				int  subWidth = rnd.nextInt(width-6)+4;
				r1.setBounds((int)room.getX(), (int)room.getY(), subWidth, height);
				r2.setBounds((int)room.getX()+subWidth, (int)room.getHeight(), width-subWidth, height);
				
				int holeY = rnd.nextInt(height-1)+(int)room.getY()+1;
				holes.add(new Point((int) (room.getX()+subWidth), holeY));
				
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

}
