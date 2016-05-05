/*
 * La superclasse de tous les éléments de la partie Model (à l'exception de Inventory): les Actor(Player et Monster)
 *  et les CollectableObjects(Weapon, Potion, Projectile)
 */

package model.gameElements;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import model.Game;

public abstract class GameObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double posx;
	private double posy;
	private transient Image object_image;
	private String image_url;
	private Game game;
	private Rectangle hitbox;

	public GameObject(double x, double y, String image_url, Rectangle hitbox, Game game) {
		// x et y sont les positions relatives dans le niveau, pas celles en
		// nombre de pixels.
		// Pour afficher l'objet avec ses vraies dimensions, il faudra donc
		// multiplier par la dimension.
		// Les objets ne sont pas liés à une case précise, d'où l'utilisation d'une Hitbox.
		setX(x);
		setY(y);
		setHitbox(hitbox);
		this.image_url = image_url;
		setImage();
		this.game = game;
	}
	
	public void reloadAction(Game game) {	//sert lors de la restauration de la sauvegarde
		this.game = game;
		setImage();
	}

	public void setImage() {
		
		//Défini le fichier image lié au GameObject
		//N'est JAMAIS manipulé par le modèle mais est utilisé par la vue pour dessiner l'objet
		
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

	public int getAnimationCount() {
		//A REDEFINIR
		return 0;
	}
}