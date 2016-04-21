package gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
//import com.sun.prism.Graphics;
//import com.sun.prism.paint.Color;

import gameElements.Inventory;
import gameElements.Player;

public class InventoryWindow extends JPanel {		//modèle vue-contrôleur pour l'inventaire
	private static Dimension inventoryWindowSize;
	//private GameWindow gameWindow;
	//private Inventory inventory;
	
	public InventoryWindow(){
		inventoryWindowSize= new Dimension(1000, 200);
		//this.gameWindow=gameWindow;
		this.setPreferredSize(inventoryWindowSize);
		//this.setLayout(null);
		this.setVisible(true);
		this.repaint();
	}
	


	public void paint(Graphics g){
		for (int i=0; i<inventoryWindowSize.width; i++){
			g.setColor(Color.ORANGE);
			g.fillRect(i*200, 0, 200, 200);
			g.setColor(Color.BLACK);
			g.drawRect(i*200, 0, 200, 200);
		}
		
		
	}	
	
}

