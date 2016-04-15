package animation;

import gameElements.Player;

public class PlayerThread extends Animation {
	
	public PlayerThread(Player player) {
		super(player);
	}
	
	public void mainFunction() {
		getSource().move();
		getSource().repaint();
	}
	

}
