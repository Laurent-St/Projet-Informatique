package animation;

public class CountTimer extends Count {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CountTimerListener source;
	
	public CountTimer(int max, int sleepTime, CountTimerListener source) {
		super(max,sleepTime);
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
