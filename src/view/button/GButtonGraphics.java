/*
 * Classe g�rant l'affichage de l'�tat du bouton en fonction de l'�tat de la souris
 */

package view.button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controls.GButtonGraphicsMouse;

@SuppressWarnings("serial")
public class GButtonGraphics extends JPanel {
	
	private BufferedImage mouseOutImage;
	private BufferedImage mouseOverImage;
	private BufferedImage mouseDownImage;
	private BufferedImage currentImage;
	private GButton button;
	
	public GButtonGraphics(GButton button) {
		
		//Initialisation des graphismes du bouton et de son �couteur d'�v�nement
		
		try {
			mouseOutImage = ImageIO.read(new File("src/view/button/mouseOut.png"));
			mouseOverImage = ImageIO.read(new File("src/view/button/mouseOver.png"));
			mouseDownImage = ImageIO.read(new File("src/view/button/mouseDown.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.button = button;
		this.setLayout(null);
		this.setVisible(true);
		currentImage = mouseOutImage;
		this.addMouseListener(new GButtonGraphicsMouse(this));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(currentImage, 0, 0,(int)button.getDimensions().getWidth(),(int)button.getDimensions().getHeight(), null);
	}
	
	public void updateImageSize() {
		
		//Mise � jour de l'affichage
		
		this.repaint();
		button.repaint();
	}

	public void setGraphics(String type) {
		
		//D�fini l'image � utiliser selon l'�tat re�u par le MouseListener
		
		if(type=="over") {
			this.currentImage = mouseOverImage;
		} else if (type=="down") {
			this.currentImage = mouseDownImage;
		} else if (type=="out") {
			this.currentImage = mouseOutImage;
		}
		this.updateImageSize();
	}
	
}
