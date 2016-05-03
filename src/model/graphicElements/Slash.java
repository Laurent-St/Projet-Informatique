package model.graphicElements;

import java.awt.Rectangle;

import model.Game;
import model.map.Map;

public class Slash extends SpecialFX {
	

	private static final long serialVersionUID = 1L;
	private static Rectangle slashHitbox = new Rectangle(0,0,32,32);
	private static String url = "src/model/graphicElements/slash.png";
	private static int animationDuration = 5;
	private static int animationDelay = 100;
	

	public Slash(double x, double y, Game game, Map level) {
		super(x, y, level, animationDuration, animationDelay, url, slashHitbox, game);
	}
}
