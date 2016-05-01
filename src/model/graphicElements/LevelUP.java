package model.graphicElements;

import java.awt.Rectangle;

import animation.CountTimer;
import animation.CountTimerListener;
import model.Game;
import model.gameElements.GameObject;
import model.map.Map;

public class LevelUP extends GameObject implements CountTimerListener, SpecialFX {
	

	private static final long serialVersionUID = 1L;
	private static Rectangle lupHitbox = new Rectangle(0,0,32,32);
	private static String url = "src/model/graphicElements/levelup.png";
	private Map linkedMap;
	private CountTimer ct;

	public LevelUP(double x, double y, Game game, Map level) {
		super(x, y, url, lupHitbox, game);
		this.linkedMap = level;
		level.addSpecialFX(this);
		ct = new CountTimer(5,150,this);
		
	}
	@Override
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
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 32;
	}
	
	

}
