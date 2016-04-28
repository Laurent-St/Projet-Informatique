package animation;

import java.io.Serializable;

public class Count implements Serializable, Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count = 0;
	private int maxCount;
	protected int sleepTime;
	private Thread clock;
	
	public Count(int max, int time) {
		count = 0;
		maxCount = max;
		sleepTime = time;
		clock = new Thread(this);
		clock.start();
	}
	
	public void increment() {
		count+=1;
		if (count>=maxCount) {
			count=0;
		}
		
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int val) {
		count = val;
	}
	
	public void stop() {
		clock.stop();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				
			}
			this.increment();
		}		
	}

}
