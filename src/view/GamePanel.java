package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.Game;
import model.gameElements.Inventory;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private static Dimension panelSize = new Dimension(1000,800);
	private InventoryWindow inventoryWindow;
	private Game game;
	
	public GamePanel(Game game) {
		this.game=game;
		this.setLocation(0, 0);
		this.setPreferredSize(panelSize);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		initInventoryWindow();
		System.out.println("new GamePanel");
	}
	
	public Dimension getDimensions() {
		return panelSize;
	}

	public void removePanels() {
		this.removeAll();
	}
	
	public void initInventoryWindow(){
		inventoryWindow=new InventoryWindow(getGame().getPlayer().getInventory());
		//this.setContentPane(inventoryWindow);
		inventoryWindow.setBounds(0, 700, 1000, 200);
		this.add(inventoryWindow);
		this.setComponentZOrder(inventoryWindow, 0);
		inventoryWindow.setVisible(true);
		inventoryWindow.repaint();
		//inventoryWindow.setLocation(0, 800);
	}
	public InventoryWindow getInventoryWindow(){
		return inventoryWindow;
	}
	public Game getGame(){
		return game;
	}
}
