/*
 * Thread agissant comme horloge: toutes les 15 millisecondes, il appelle la méthode paintComponent
 *  du JComponent auquel il est associé.
 */

package animation;

import java.io.Serializable;

import javax.swing.JComponent;

public class GraphicsClock implements Serializable, Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
