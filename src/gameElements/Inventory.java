package gameElements;
import java.util.ArrayList;

public class Inventory {
	private ArrayList<CollectableObject> content;
	private int maxSize;
	
public Inventory( int maxSize){
	ArrayList<CollectableObject> content=new ArrayList<CollectableObject>();
	this.maxSize=maxSize;
}

public void setInInventory(CollectableObject object){
	if (content.size()<maxSize){
		content.add(object);
	}else{
		System.out.println("Your inventory is full");
	}
}
}
