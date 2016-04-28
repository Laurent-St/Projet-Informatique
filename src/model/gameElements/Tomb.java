package model.gameElements;

public class Tomb extends CollectableObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Inventory inventory;
	private int experience;

public Tomb(String name, int position,Inventory inventory, int experience){
	super(name, position);
	setInventory(inventory);
	setExperience(experience);
}
public Inventory getInventory(){
	return inventory;
}
public void setInventory(Inventory inventory){
	this.inventory=inventory;
}
public int getExperience(){
	return experience;
}
public void setExperience(int experience){
	this.experience=experience;
}

}
