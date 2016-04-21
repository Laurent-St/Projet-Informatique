package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.gameElements.Inventory;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private static Dimension panelSize = new Dimension(1000,800);
	private InventoryWindow inventoryWindow;
	
	public GamePanel() {
		this.setLocation(0, 0);
		this.setPreferredSize(panelSize);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		initInventoryWindow();
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
	
	public void initInventoryWindow(){
		inventoryWindow=new InventoryWindow();
		//this.setContentPane(inventoryWindow);
		inventoryWindow.setBounds(0, 700, 1000, 200);
		addPanel(inventoryWindow);
		this.setComponentZOrder(inventoryWindow, 0);
		inventoryWindow.setVisible(true);
		inventoryWindow.repaint();
		//inventoryWindow.setLocation(0, 800);
	}
}
