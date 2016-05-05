/*
 * Contenu principal de la fenêtre de jeu
 * Affiche successivement un menu (MenuPanel) de jeu ou une partie (LevelPanel + InventoryWindow + StatsPanel)
 */

package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.Game;
import model.gameElements.Inventory;
import model.gameElements.Player;
import view.level.InventoryWindow;
import view.level.StatsPanel;
import view.menu.MenuPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Dimension panelSize = new Dimension(1000,800);
	private InventoryWindow inventoryWindow;
	private StatsPanel statsPanel;
	private Game game;
	private MenuPanel currentMenu;
	
	public GamePanel(Game game) {
		
		//Définition de l'affichage du GamePanel
		
		this.game=game;
		this.setLocation(0, 0);
		this.setPreferredSize(panelSize);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		System.out.println("new GamePanel");
	}
	
	public Dimension getDimensions() {
		return panelSize;
	}
	
	public void setMenu(MenuPanel menu) {
		
		//Permet d'afficher un menu de jeu
		
		removeMenus();
		this.currentMenu = menu;
		this.add(menu);
		menu.setVisible(true);
		repaint();
	}
	
	public void initInventoryWindow(Inventory inventory){
		
		//Permet d'afficher un panneau d'inventaire
		
		inventoryWindow=new InventoryWindow(inventory);
		this.add(inventoryWindow);
		this.setComponentZOrder(inventoryWindow, 0);
		inventoryWindow.setVisible(true);
		this.repaint();
		inventoryWindow.repaint();
	}
	
	public void initStatsPanel(Player player) {
		
		//Permet d'afficher un panneau de statistiques du joueur
		
		statsPanel = new StatsPanel(player);
		this.add(statsPanel);
		statsPanel.setVisible(true);
		player.setStatsPanel(statsPanel);
	}
	
	public InventoryWindow getInventoryWindow(){
		return inventoryWindow;
	}
	public Game getGame(){
		return game;
	}

	public void removeMenus() {
		if(currentMenu!=null) {this.remove(currentMenu);}
	}
}
