/*
 * SpecialFX s'affichant lorsque le joueur passe de niveau
 */

package model.graphicElements;

import java.awt.Rectangle;

import model.Game;
import model.map.Map;

public class LevelUP extends SpecialFX {
	

	private static final long serialVersionUID = 1L;
	private static Rectangle lupHitbox = new Rectangle(0,0,32,32);
	private static String url = "src/model/graphicElements/levelup.png";
	private static int animationDuration = 5;
	private static int animationDelay = 200;

	public LevelUP(double x, double y, Game game, Map level) {
		super(x, y, level, animationDuration, animationDelay, url, lupHitbox, game);
	}

}
