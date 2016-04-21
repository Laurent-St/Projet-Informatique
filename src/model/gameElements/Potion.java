package model.gameElements;

public class Potion extends CollectableObject{
	private int value;

public Potion(String name, int position, int value){
	super(name,position);
	setValue(value);
}
public int getValue(){
	return value;
}
public void setValue(int value){
	if (value<=0){
		throw new RuntimeException("Potion inutile!");
	}else{
		this.value=value;
	}
}
/*
public void potionDrinked(){
	//fonction abstraite qui sera rédéfinie en fonction du type de potion
} 
*/
}
