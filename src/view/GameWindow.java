package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	//private GamePanel mainPanel;
	private GamePanel gamePanel;
	
	
	public GameWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Hack And Slash");
		this.setResizable(false);
		//mainPanel=new GamePanel();
		//mainPanel.setPreferredSize(new Dimension(1000,1000));
		
		initPanel();
		
		//mainPanel.setVisible(true); déjà dans le constructeur de GamePanel
		
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    System.out.println("new GameWindow");
	}

	
	public void initPanel() {
		gamePanel = new GamePanel();
		gamePanel.setVisible(true);
		this.add(gamePanel);
		//this.setContentPane(gamePanel);
	}

	
	public GamePanel getGamePanel() {
		return this.gamePanel;
		//return this.mainPanel;
	}
}