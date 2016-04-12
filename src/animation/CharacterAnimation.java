package animation;

import gameElements.Actor;

public class CharacterAnimation extends Animation {

	public CharacterAnimation(Actor actor) {
		super(actor);
		// TODO Auto-generated constructor stub
	}
	
	public void mainFunction() {
		getSource().move();
		getSource().repaint();
	}
	

}
