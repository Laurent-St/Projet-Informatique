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
	// private int position;
	private double x;
	private double y;
	private Image object_image;
	private String image_url;
	private LevelPanel level;
	private Rectangle hitbox;

	public GameObject(double x, double y, String image_url, Rectangle hitbox, LevelPanel level) {
		// x et y sont les positions relatives dans le niveau, pas celles en
		// nombre de pixels.
		// Pour afficher l'objet avec ses vraies dimensions, il faudra donc
		// multiplier par la dimension.
		setX(x);
		setY(y);
		setHitbox(hitbox);
		this.image_url = image_url;
		setImage();
		this.level = level;
		level.add(this);
		this.setVisible(true);
		this.setBounds(new Rectangle(0, 0, 1000, 1000));
		this.repaint();
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

	public LevelPanel getLevel() {
		return this.level;
	}

	public int getX() {
		return (int)Math.round(x);
	}

	public void setX(double x) {
		this.x = x; // temporaire, à lier avec l'interface graphique!
	}

	public int getY() {
		return (int)Math.round(y);
	}
	
	public double getYdouble() {
		return y;
	}
	public double getXdouble() {
		return x;
	}

	public void setY(double d) {
		this.y = d;
	}
	
	public void setHitbox(Rectangle d) {
		this.hitbox = d;
	}
	
	public Rectangle getHitbox() {
		return this.hitbox;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(object_image, getX(), getY(), null);
	}

}