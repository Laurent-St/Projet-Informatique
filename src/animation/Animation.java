package animation;

import gameElements.GameObject;

public class Animation implements Runnable{
	
	private GameObject source;
	private int sleepTime = 15;
	
	public Animation(GameObject gameObject) {
		this.source = gameObject;
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
			System.out.println("sleep");
		}
	}

}
