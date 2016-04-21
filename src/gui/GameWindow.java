package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GamePanel mainPanel;
	private GamePanel gamePanel;
	private InventoryWindow inventoryWindow;
	
	public GameWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Hack And Slash");
		this.setResizable(false);
		mainPanel=new GamePanel();
		mainPanel.setPreferredSize(new Dimension(1000,1000));
		
		initPanel();
		initInventoryWindow();
		//mainPanel.setVisible(true); déjà dans le constructeur de GamePanel
		this.add(mainPanel);
		
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	
	public void initInventoryWindow(){
		inventoryWindow=new InventoryWindow();
		//this.setContentPane(inventoryWindow);
		mainPanel.add(inventoryWindow, BorderLayout.SOUTH);
		//inventoryWindow.setLocation(0, 800);
	}
	
	public void initPanel() {
		gamePanel = new GamePanel();
		mainPanel.add(gamePanel, BorderLayout.NORTH);
		//this.setContentPane(gamePanel);
	}

	
	public GamePanel getGamePanel() {
		//return this.gamePanel;
		return this.mainPanel;
	}
}