package gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GamePanel gamePanel;
	
	public GameWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Hack And Slash");
		this.setResizable(false);
		
		gamePanel = new GamePanel();
		this.add(gamePanel);
		
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
}