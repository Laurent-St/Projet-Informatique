package gameElements;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.level.LevelPanel;

@SuppressWarnings("serial")
public class GameObject extends JComponent {
	//private int position;
	private int x;
	private int y;
	private BufferedImage object_image;
	private String image_url;
	private int image_size;
	private LevelPanel level;
	

public GameObject(int x,int y, String image_url, LevelPanel level){
	//x et y sont les positions relatives dans le niveau, pas celles en nombre de pixels.
	//Pour afficher l'objet avec ses vraies dimensions, il faudra donc multiplier par la dimension.
	setX(x);
	setY(y);
	this.image_url=image_url;
	setImage();
	this.level=level;
	level.add(this);
	this.setVisible(true);
	this.setBounds(new Rectangle(0,0,1000,1000));
	this.repaint();
}

public void setImage(){
	try{
		object_image=ImageIO.read(new File(image_url));
	}catch(IOException e){
		e.printStackTrace();
	}
}


public BufferedImage getImage(){
	return object_image;
}

public LevelPanel getLevel() {
	return this.level;
}

public int getX(){
	return x;
}

public void setX(int x){
	this.x=x;  //temporaire, � lier avec l'interface graphique!
}
public int getY(){
	return y;
}
public void setY(int y){
	this.y=y;
}

public void paintComponent(Graphics g) {
	System.out.println("ok");
	super.paintComponent(g);
<<<<<<< HEAD
	g.drawImage(object_image, 0, 0, x*object_image.getWidth(), y*object_image.getHeight(), null);
=======
	g.drawImage(object_image, getX(), getY(), null);
>>>>>>> origin/master
}





}