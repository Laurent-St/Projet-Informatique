package gameElements;

import gui.level.LevelPanel;

public class CollectableObject extends GameObject {
	private String name;

public CollectableObject(String name,int x, int y, String image_url, LevelPanel level){
	super(x,y,image_url, level);
	setName(name);
}

public String getName(){
	return name;
}
public void setName(String name){
	if (name instanceof String){
		this.name=name;
	}else{
		throw new RuntimeException("Le nom doit être une chaîne de caractères");
	}
}
}