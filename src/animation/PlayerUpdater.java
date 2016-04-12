package animation;

import gameElements.Actor;

public class PlayerUpdater extends Animation {
	
	public PlayerUpdater(Actor actor) {
		super(actor);
	}
	
	public void mainFunction() {
		getSource().move();
		getSource().repaint();
	}
	

}
