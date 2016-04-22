package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GamePanel gamePanel;
	
	public GameWindow(GamePanel gamePanel) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Hack And Slash");
		this.setResizable(false);
		
		this.gamePanel = gamePanel;
		initPanel();
		
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    System.out.println("new GameWindow");
	}

	
	public void initPanel() {
		gamePanel.setVisible(true);
		this.add(gamePanel);
	}

	
	public GamePanel getGamePanel() {
		return this.gamePanel;
		//return this.mainPanel;
	}
}