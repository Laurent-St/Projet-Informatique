package gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GamePanel gamePanel;
	
	public GameWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Hack And Slash");
		this.setResizable(false);
		
		initPanel();
				
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	
	
	public void initPanel() {
		gamePanel = new GamePanel();
		this.setContentPane(gamePanel);
	}

	
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}
}