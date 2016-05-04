package model.gameElements;

import java.io.Serializable;
import java.util.ArrayList;

import view.InventoryObserver;

public class Inventory implements InventorySubject, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CollectableObject> content;
	private int maxSize = 5;
	private InventoryObserver observer;
	private Player attachedPlayer;
	//private Game game;

	public Inventory(int maxSize, Player attachedPlayer) {
		
		this.attachedPlayer = attachedPlayer;
		content = new ArrayList<CollectableObject>();
		setMaxSize(maxSize);
//		for (int i=0; i<maxSize; i++){
//			content.add(i, null);
//		}
		//this.inventoryWindow=game.getGamePanel().getInventoryWindow();
		
	}

	public void setInInventory(CollectableObject object) {
		if (content.size() < maxSize) {
			content.add(object);
//			inventoryWindow.repaint();
			notifyObserver();
		} else {
			System.out.println("Your inventory is full"); // à printer dans
															// l'interface et
															// pas dans la
															// console.
		}
	}

	public void dropObject(int i){
		CollectableObject co = getFromInventory(i-1);
		if(co!=null) {
			attachedPlayer.getGame().getCurrentMap().addCollectableObject(co);
			co.setX(attachedPlayer.getXdouble());
			co.setY(attachedPlayer.getYdouble());
			removeFromInventory(co);
		}
	}
	
	public void removeFromInventory(CollectableObject object) {
		content.remove(object);
		notifyObserver();
	}

	public ArrayList<CollectableObject> getContent(){
		return content;
	}
	
	public void clearInventory() {
		content.clear();
	}

	public int getMaxSize() {
		return maxSize;
	}
	
	public Player getAttachedPlayer() {
		return this.attachedPlayer;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public ArrayList<CollectableObject> getExistingContent(){		//retourne une liste avec juste les éléments existants
		return content;
	}
	
	public CollectableObject getFromInventory(int i) {
		if(getExistingContent().size()>i) {
			if(getExistingContent().get(i)!=null) {
				return getExistingContent().get(i);
			}
		}
		return null;
	}
	
	public void setInventoryObserver(InventoryObserver o) {
		this.observer = o;
	}
	
	public void notifyObserver(){
		if (observer!=null){
			observer.updateContent();
		}
	}

	public void select(int i) {
		CollectableObject object = getFromInventory(i-1);
		if(object!=null) {
			object.inventorySelectAction();
		}
	}

	public boolean isFull() {
		return content.size()>=getMaxSize();
	}
	
}