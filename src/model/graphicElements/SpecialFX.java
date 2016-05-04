package model.graphicElements;

import java.awt.Rectangle;

import animation.CountTimer;
import animation.CountTimerListener;
import model.Game;
import model.gameElements.GameObject;
import model.map.Map;

public class SpecialFX extends GameObject implements CountTimerListener {

	private static final long serialVersionUID = 1L;
	private Map linkedMap;
	private CountTimer ct;

	
	public SpecialFX(double x, double y, Map level, int animationDuration, int animationDelay, String url,
			Rectangle hitbox, Game game) {
		super(x, y, url, hitbox, game);
		this.linkedMap = level;
		this.ct = new CountTimer(animationDuration,animationDelay,this);
		linkedMap.addSpecialFX(this);
	}

	public void atCount(CountTimer ck) {
		if (ck.getCount()==4) {
			linkedMap.removeSpecialFX(this);
			ck.stop();
		}
		
	}
	
	public int getAnimationCount() {
		if(ct!=null) {
			return ct.getCount();
		}
		return 0;
	}

	public int getSize() {
		return getHitbox().width;
	}

}
