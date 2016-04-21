package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private static Dimension panelSize = new Dimension(1000,800);
	
	public GamePanel() {
		this.setLocation(0, 0);
		this.setPreferredSize(panelSize);
		//this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
	}
	
	public Dimension getDimensions() {
		return panelSize;
	}

	public void removePanels() {
		this.removeAll();
	}

	public void addPanel(JPanel panel) {
		this.add(panel);
	}
}
