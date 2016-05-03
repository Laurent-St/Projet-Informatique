package model.graphicElements;

import java.awt.Rectangle;

import model.Game;
import model.map.Map;

public class DeadMessage extends SpecialFX {
	

	private static final long serialVersionUID = 1L;
	private static Rectangle deadHitbox = new Rectangle(0,0,64,64);
	private static String url = "src/model/graphicElements/dead.png";
	private static int animationDuration = 5;
	private static int animationDelay = 250;

	public DeadMessage(double x, double y, Game game, Map level) {
		super(x, y, level, animationDuration, animationDelay, url, deadHitbox, game);
	}		

}
