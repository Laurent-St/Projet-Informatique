package model.graphicElements;

import java.awt.Rectangle;

import animation.CountTimer;
import animation.CountTimerListener;
import model.Game;
import model.gameElements.GameObject;
import model.map.Map;

public class Slash extends GameObject implements CountTimerListener {
	

	private static final long serialVersionUID = 1L;
	private static Rectangle slashHitbox = new Rectangle(0,0,32,32);
	private static String url = "src/model/graphicElements/slash.png";
	private Map linkedMap;
	private CountTimer ct;

	public Slash(double x, double y, Game game, Map level) {
		super(x, y, url, slashHitbox, game);
		this.linkedMap = level;
		level.addSpecialFX(this);
		ct = new CountTimer(5,100,this);
		
	}
	@Override
	public void atCount(CountTimer ck) {
		if (ck.getCount()==4) {
			linkedMap.removeSpecialFX(this);
			ck.stop();
		}
		
	}
	
	public int getAnimationCount() {
		return ct.getCount();
	}
	
	

}
