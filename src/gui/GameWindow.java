package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GamePanel gamePanel;
	
	public GameWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Hack And Slash");
		this.setResizable(false);
		
		gamePanel = new GamePanel();
		this.add(gamePanel);
		
		//TEST DE LA POLICE
		JLabel test = new JLabel();
		test.setText("Hello World");
		test.setFont(FontLoader.getFont());
		gamePanel.add(test);
		
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
}