package model.gameElements;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Game;
import view.level.LevelPanel;

public class GameObject {
	// private int position;
	private double posx;
	private double posy;
	private Image object_image;
	private String image_url;
	private Game game;
	private Rectangle hitbox;

	public GameObject(double x, double y, String image_url, Rectangle hitbox, Game game) {
		// x et y sont les positions relatives dans le niveau, pas celles en
		// nombre de pixels.
		// Pour afficher l'objet avec ses vraies dimensions, il faudra donc
		// multiplier par la dimension.
		setX(x);
		setY(y);
		setHitbox(hitbox);
		this.image_url = image_url;
		setImage();
		this.game = game;
	}

	public void setImage() {
		try {
			object_image = ImageIO.read(new File(image_url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage() {
		return object_image;
	}

	public Game getGame() {
		return this.game;
	}	

	public int getX() {
		return (int)Math.round(posx);
	}

	public void setX(double x) {
		this.posx = x; 
	}

	public int getY() {
		return (int)Math.round(posy);
	}
	
	public double getYdouble() {
		return posy;
	}
	public double getXdouble() {
		return posx;
	}

	public void setY(double d) {
		this.posy = d;
	}
	
	public void setHitbox(Rectangle d) {
		this.hitbox = d;
	}
	
	public Rectangle getHitbox() {
		return this.hitbox;
	}
}