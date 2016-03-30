package gameElements;

public class GameObject {
	protected int position;


public GameObject(int position){
	setPosition(position);
	}

public int getPosition(){
	return position;
}

public void setPosition(int position){
	this.position=position;  //temporaire, à lier avec l'interface graphique!
}
}