package gameElements;

public class HealthPotion extends Potion{
	
	public HealthPotion(String name, int position, int value){
	super(name,position,value);
	setValue(value);
}
}
