package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Skull extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String url = "src/model/gameElements/crane.png";
	private static Rectangle craneHitbox = new Rectangle(0,0,32,32);
	
	public Skull(double x, double y, Game game) {
		super(x,y,url,craneHitbox,game);
	}

}
