package gui;

import java.awt.Dimension;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private static Dimension panelSize = new Dimension(900,600);
	
	public GamePanel() {
		this.setLocation(0, 0);
		this.setPreferredSize(panelSize);
		this.setVisible(true);	
		
	}
}
