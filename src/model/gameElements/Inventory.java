package model.gameElements;

import java.util.ArrayList;

import model.Game;
import view.GamePanel;
import view.InventoryWindow;

public class Inventory {
	private ArrayList<CollectableObject> content;
	private int maxSize = 5;
	private InventoryWindow inventoryWindow;
	//private Game game;

	public Inventory(int maxSize) {
		ArrayList<CollectableObject> content = new ArrayList<CollectableObject>();
		setMaxSize(maxSize);
//		for (int i=0; i<maxSize; i++){
//			content.add(i, null);
//		}
		//this.inventoryWindow=game.getGamePanel().getInventoryWindow();
		
	}

	public void setInInventory(CollectableObject object) {
		if (content.size() < maxSize) {
			content.add(object);
			inventoryWindow.repaint();
		} else {
			System.out.println("Your inventory is full"); // � printer dans
															// l'interface et
															// pas dans la
															// console.
		}
	}

	public void removeFromInventory(CollectableObject object) {
		content.remove(object);
		// ATTENTION IL FAUDRA GERER LE FAIT QU'IL PEUT Y AVOIR PLUSIEURS OBJETS
		// DU MEME TYPE!
	}

	public void clearInventory() {
		content.clear();
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public ArrayList<CollectableObject> getExistingContent(){		//retourne une liste avec juste les �l�ments existants
//		ArrayList<CollectableObject> res=new ArrayList<CollectableObject>();
//		for (int i=0;i<maxSize;i++){
//			if (content.get(i)!=null){
//				res.add(content.get(i));
//			}
//		}
//		return res;
		return content;
	}
}