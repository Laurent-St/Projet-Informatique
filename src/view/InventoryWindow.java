package view;

import java.awt.Dimension;

import javax.swing.JPanel;

import model.gameElements.Inventory;

import java.awt.Color;
import java.awt.Graphics;
//import com.sun.prism.Graphics;
//import com.sun.prism.paint.Color;

@SuppressWarnings("serial")
public class InventoryWindow extends JPanel {		//modèle vue-contrôleur pour l'inventaire
	private static Dimension inventoryWindowSize;
	//private GameWindow gameWindow;
	//private Inventory inventory;
	
	public InventoryWindow(){
		//this.inventory = inventory;
		inventoryWindowSize= new Dimension(1000, 200);
		//this.gameWindow=gameWindow;
		this.setBackground(Color.BLACK);
		this.setPreferredSize(inventoryWindowSize);
		//this.setLayout(null);
		this.setVisible(true);
		this.repaint();
	}
	


	protected void paintComponent(Graphics g){
		for (int i=0; i<5; i++){
			g.setColor(Color.ORANGE);
			g.fillRect(i*50, 0, 50, 50);
			g.setColor(Color.BLACK);
			g.drawRect(i*50, 0, 50, 50);
		}
		
		
	}	
	
}

