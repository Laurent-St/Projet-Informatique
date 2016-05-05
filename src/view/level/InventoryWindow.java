/*
 * Panneau d'affichage de l'inventaire et des armes du joueur.
 * Implémente l'interface InventoryObserver (patron observateur) pour permettre la mise à jour de l'affichage
 * Doit être lié à un inventaire pour récupérer ses objets
 */


package view.level;

import javax.swing.JPanel;

import model.gameElements.CollectableObject;
import model.gameElements.Inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

@SuppressWarnings("serial")
public class InventoryWindow extends JPanel implements InventoryObserver {		//modèle vue-contrôleur pour l'inventaire

	private Inventory inventory;
	
	public InventoryWindow(Inventory inventory){
		
		//Définition de la mise en page
		
		setInventory(inventory);
		this.setBounds(500-(inventory.getMaxSize()+4)*25, 700, 1000, 50);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		this.repaint();
	}
	
	public void setInventory(Inventory inventory) {
		
		//Défini l'inventaire lié ainsi que l'InventoryObserver dans cet inventaire
		
		this.inventory = inventory;
		inventory.setInventoryObserver(this);
		updateContent();
	}
	


	protected void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 50);
		
		//Dessin des cases (vides) de l'inventaire
		for (int j=0; j<5; j++){
			paintOneCase(g, j);
			char[] key = {String.valueOf(j+1).charAt(0)};
			g.drawChars(key, 0, 1, j*50+2, 50);
		}
		
		//Dessin des objets de l'inventaire à travers une boucle parcourant les objets de l'inventaire
		if (inventory!=null){
			System.out.println("inventory pas null");
			if (inventory.getExistingContent()!=null){
				int i=0;
				for (CollectableObject object: inventory.getExistingContent()){ //Polymorphisme: tous les objets de l'inventaire héritent de CollectableObject
					Image image=object.getImage(); //Récupération de l'image de l'objet
					g.drawImage(image, i*50+5, 5, i*50+44, 44,
									0,0,31,31,null);
					i++;
				}
			}
			
			//Dessin des emplacements des armes actives
			paintOneCase(g, inventory.getMaxSize()+2);
			paintOneCase(g, inventory.getMaxSize()+3);
			char[] space = {'s','p','a','c','e'};
			char[] shift = {'s','h','i','f','t'};
			g.drawChars(space, 0, 5, (inventory.getMaxSize()+2)*50, 50);
			g.drawChars(shift, 0, 5, (inventory.getMaxSize()+3)*50, 50);
			
			//Dessin de l'arme de main et de l'arme à distance actives
			if(inventory.getAttachedPlayer().getHandWeapon()!=null) {
				g.drawImage(inventory.getAttachedPlayer().getHandWeapon().getImage(), inventory.getMaxSize()*50+105, 0, inventory.getMaxSize()*50+144, 39,
						0,0,31,31,null);
			}
			if(inventory.getAttachedPlayer().getThrowableWeapon()!=null) {
				g.drawImage(inventory.getAttachedPlayer().getThrowableWeapon().getImage(), inventory.getMaxSize()*50+155, 0, inventory.getMaxSize()*50+194, 39,
						0,0,31,31,null);
			}
		}
	}
	
	public void paintOneCase(Graphics g, int x){
		
		//Permet de dessiner une case vide
		
		g.setColor(Color.ORANGE);
		g.fillRect(x*50, 0, 50, 50);
		g.setColor(Color.BLACK);
		g.drawRect(x*50, 0, 50, 50);
		
	}
	
	//InventoryObserver
	public void updateContent(){
		this.repaint();
		
	}
	
}

