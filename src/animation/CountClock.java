package animation;

public class CountClock implements Runnable {
	
	private int sleepTime = 0;
	private Count count;
	
	public CountClock(int st, Count c) {
		sleepTime = st;
		count = c;
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				
			}
			count.increment();
		}
	}

}
