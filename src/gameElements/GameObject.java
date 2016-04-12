package gameElements;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GameObject extends JComponent {
	protected int position;
	protected int x;
	protected int y;


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