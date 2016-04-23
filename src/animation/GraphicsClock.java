package animation;

import javax.swing.JComponent;

public class GraphicsClock implements Runnable {
	
	private int delay;
	private JComponent source;
	
	public GraphicsClock(int delay, JComponent source) {
		this.delay = delay;
		this.source = source;
		Thread GCT = new Thread(this);
		GCT.start();
		
	}

	@Override
	public void run() {
		while(true) {
			source.repaint();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
