/*
 * Gère l'inventaire du joueur, contient une liste de CollectableObject
 * Doit être initialisé avec un Player (inventaire lié à un joueur)
 */

package model.gameElements;

import java.io.Serializable;
import java.util.ArrayList;

import view.level.InventoryObserver;

public class Inventory implements InventorySubject, Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<CollectableObject> content;
	private int maxSize = 5;
	private InventoryObserver observer;
	private Player attachedPlayer;

	public Inventory(int maxSize, Player attachedPlayer) {
		
		this.attachedPlayer = attachedPlayer;
		content = new ArrayList<CollectableObject>();
		setMaxSize(maxSize);
		
	}

	public void setInInventory(CollectableObject object) {
		
		//Ajoute un objet à l'inventaire (si non plein)
		
		if (content.size() < maxSize) {
			content.add(object);
			notifyObserver();
		} else {
			System.out.println("Your inventory is full"); 
		}
	}

	public void dropObject(int i){
		
		//Jete un objet sur la map et le retire de l'inventaire
		
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
		
		//Renvoie un objet de l'inventaire à la position i (selon l'input clavier du joueur)
		
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