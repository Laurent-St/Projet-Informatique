package view;

import java.awt.Dimension;
import javax.swing.JPanel;

import model.gameElements.CollectableObject;
import model.gameElements.Inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

@SuppressWarnings("serial")
public class InventoryWindow extends JPanel implements InventoryObserver {		//modèle vue-contrôleur pour l'inventaire

	private static int[][] cases= new int[5][2];  //première partie: numéro case, 2e: 1 ou 0, occupation case
	private Inventory inventory;
	
	public InventoryWindow(Inventory inventory){
		this.inventory=inventory;
		inventory.setInventoryObserver(this);
		this.setBounds(500-(inventory.getMaxSize()+4)*25, 700, 1000, 50);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		this.repaint();
	}
	


	protected void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 50);
		for (int j=0; j<5; j++){
			paintOneCase(g, j);
			char[] key = {String.valueOf(j+1).charAt(0)};
			g.drawChars(key, 0, 1, j*50+2, 50);
		}
		if (inventory!=null){
			cases = new int[5][2];
			System.out.println("inventory pas null");
			if (inventory.getExistingContent()!=null){
				for (CollectableObject object: inventory.getExistingContent()){
					Image image=object.getImage();
					for (int i=0; i<inventory.getMaxSize(); i++){
						if (!isCaseOccupied(i)){
							g.drawImage(image, i*50+5, 5, i*50+45, 45,
									0,0,31,31,null);
							cases[i][1]=1;
							break;
						}
					}
				}
			}
			paintOneCase(g, inventory.getMaxSize()+2);
			paintOneCase(g, inventory.getMaxSize()+3);
			char[] space = {'s','p','a','c','e'};
			char[] shift = {'s','h','i','f','t'};
			g.drawChars(space, 0, 5, (inventory.getMaxSize()+2)*50, 50);
			g.drawChars(shift, 0, 5, (inventory.getMaxSize()+3)*50, 50);
			if(inventory.getAttachedPlayer().getHandWeapon()!=null) {
				g.drawImage(inventory.getAttachedPlayer().getHandWeapon().getImage(), inventory.getMaxSize()*50+105, 0, inventory.getMaxSize()*50+145, 40,
						0,0,32,32,null);
			}
			if(inventory.getAttachedPlayer().getThrowableWeapon()!=null) {
				g.drawImage(inventory.getAttachedPlayer().getThrowableWeapon().getImage(), inventory.getMaxSize()*50+155, 0, inventory.getMaxSize()*50+195, 40,
						0,0,32,32,null);
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

