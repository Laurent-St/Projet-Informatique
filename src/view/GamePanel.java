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
	private StatsPanel statsPanel;
	private Game game;
	
	public GamePanel(Game game) {
		this.game=game;
		this.setLocation(0, 0);
		this.setPreferredSize(panelSize);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		initInventoryWindow();
		initStatsPanel();
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
		inventoryWindow.setBounds(0, 700, 1000, 50);
		this.add(inventoryWindow);
		this.setComponentZOrder(inventoryWindow, 0);
		inventoryWindow.setVisible(true);
		inventoryWindow.repaint();
	}
	
	public void initStatsPanel() {
		statsPanel = new StatsPanel(getGame());
		this.add(statsPanel);
		statsPanel.setVisible(true);
		getGame().getPlayer().setStatsPanel(statsPanel);
	}
	
	public InventoryWindow getInventoryWindow(){
		return inventoryWindow;
	}
	public Game getGame(){
		return game;
	}
}
