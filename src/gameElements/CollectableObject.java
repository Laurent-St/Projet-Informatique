package gameElements;

public class CollectableObject extends GameObject {
	private String name;

public CollectableObject(String name, int position){
	super(position);
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