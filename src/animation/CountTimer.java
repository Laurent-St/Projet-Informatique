package animation;

public class CountTimer extends Count {
	private CountTimerListener source;
	
	public CountTimer(int max, int time, CountTimerListener source) {
		super(max,time);
		this.source = source;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				
			}
			this.increment();
			source.atCount(this);
		}		
	}

}
