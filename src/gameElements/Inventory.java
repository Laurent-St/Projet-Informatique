package gameElements;
import java.util.ArrayList;

public class Inventory {
	private ArrayList<CollectableObject> content;
	private int maxSize=5;
	
public Inventory(int maxSize){
	ArrayList<CollectableObject> content=new ArrayList<CollectableObject>();
	setMaxSize(maxSize);
}

public void setInInventory(CollectableObject object){
	if (content.size()<maxSize){
		content.add(object);
	}else{
		System.out.println("Your inventory is full");	//à printer dans l'interface et pas dans la console.
	}
}
public void removeFromInventory(CollectableObject object){
	content.remove(object);
	//ATTENTION IL FAUDRA GERER LE FAIT QU'IL PEUT Y AVOIR PLUSIEURS OBJETS DU MEME TYPE!
}
public void clearInventory(){
	content.clear();
}

public int getMaxSize(){
	return maxSize;
}

public void setMaxSize(int maxSize){
	this.maxSize=maxSize;
}
}
