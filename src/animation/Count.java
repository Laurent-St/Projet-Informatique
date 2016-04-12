package animation;

public class Count {
	
	private int count = 0;
	private int maxCount;
	private Thread clock;
	
	public Count(int max, int time) {
		count = 0;
		maxCount = max;
		clock = new Thread(new CountClock(time,this));
		clock.start();
	}
	
	public void increment() {
		System.out.println(count);
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

}
