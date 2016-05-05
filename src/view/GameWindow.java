/*
 * Fen�tre principale du jeu
 */


package view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GamePanel gamePanel;
	
	public GameWindow(GamePanel gamePanel) {
		
		//Initialisation de l'apparence de la fen�tre et de son comportement lors de la fermeture
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		//l'op�ration de fermeture va �tre red�finie dans WindowListener
		this.setTitle("Hack And Slash");
		this.setResizable(false);
		
		//initialisation du contenu de la fen�tre (contentPane)
		
		this.gamePanel = gamePanel;
		initPanel();
		
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    System.out.println("new GameWindow");
	    
	    this.addWindowListener(new WindowListener(gamePanel.getGame()));
	}
	
	public void windowClosed() {
		
	}

	
	public void initPanel() {
		gamePanel.setVisible(true);
		this.add(gamePanel);
	}

	
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}
}