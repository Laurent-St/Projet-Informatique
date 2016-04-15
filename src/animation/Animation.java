package animation;

import gameElements.Actor;
import gameElements.Player;

public class Animation implements Runnable{
	
	private Actor source;
	private int sleepTime = 15;
	
	public Animation(Player actor) {
		this.source = actor;
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mainFunction();
		}
	}
	
	public void mainFunction() {
		//A redefinir dans PlayerAnimation
	}
	
	public Actor getSource() {
		return source;
	}

}
