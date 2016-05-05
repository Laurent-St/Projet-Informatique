/*
 * Compteur utilis� par certaines classes afin de pouvoir acc�der � un compte qui s'incr�mente apr�s un intervalle de temps donn�.
 * (Utile pour les animations de d�placements)
 */

package animation;

import java.io.Serializable;

public class Count implements Serializable, Runnable {
	
	private static final long serialVersionUID = 1L;
	private int count = 0;
	private int maxCount;
	protected int sleepTime;
	private transient Thread clock;
	
	public Count(int max, int sleepTime) {
		count = 0;
		maxCount = max;
		this.sleepTime = sleepTime;
		clock = new Thread(this);
		clock.start();
	}
	public void activeCountThread(){	//utilis� pour la restauration de sauvegarde
		clock=new Thread(this);
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
	
	@SuppressWarnings("deprecation")
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
