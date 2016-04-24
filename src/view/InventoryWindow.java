package view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Game;
import model.gameElements.CollectableObject;
import model.gameElements.Inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
//import com.sun.prism.Graphics;
//import com.sun.prism.paint.Color;

@SuppressWarnings("serial")
public class InventoryWindow extends JPanel implements InventoryObserver {		//modèle vue-contrôleur pour l'inventaire
	private static Dimension inventoryWindowSize;
	private static int[][] cases= new int[5][2];  //première partie: numéro case, 2e: 1 ou 0, occupation case
	//private GameWindow gameWindow;
	private Inventory inventory;
	private Game game;
	
	public InventoryWindow(Inventory inventory){
		this.inventory=inventory;
		inventoryWindowSize= new Dimension(1000, 50);
		//this.gameWindow=gameWindow;
		this.setBackground(Color.BLACK);
		this.setPreferredSize(inventoryWindowSize);
		//this.setLayout(null);
		this.setVisible(true);
		this.repaint();
	}
	


	protected void paintComponent(Graphics g){
		for (int j=0; j<5; j++){
			paintOneCase(g, j);
		}
		if (inventory!=null){
			System.out.println("inventory pas null");
			if (inventory.getExistingContent()!=null){
				for (CollectableObject object: inventory.getExistingContent()){
					Image image=object.getImage();
					for (int i=0; i<5; i++){
						if (!isCaseOccupied(i)){
							g.drawImage(image, i*50, i*50, 40, 40, null);
							cases[i][1]=1;
							break;
						}
					}
				}
			}
		}
	}
	
	public void paintOneCase(Graphics g, int x){
		g.setColor(Color.ORANGE);
		g.fillRect(x*50, 0, 50, 50);
		g.setColor(Color.BLACK);
		g.drawRect(x*50, 0, 50, 50);
		
	}
	
	public void updateContent(){
		this.repaint();
		
	}
	
	public boolean isCaseOccupied(int i){		//argument=numéro de la case
		boolean res=false;
		if (cases[i][1]==1){
			res=true;
		}
		return res;
	}
	

		
	
	
}

