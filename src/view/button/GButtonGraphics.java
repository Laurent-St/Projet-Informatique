package view.button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GButtonGraphics extends JPanel {
	
	private BufferedImage mouseOutImage;
	private BufferedImage mouseOverImage;
	private BufferedImage mouseDownImage;
	private BufferedImage currentImage;
	private GButton button;
	
	public GButtonGraphics(GButton button) {
		try {
			mouseOutImage = ImageIO.read(new File("src/gui/button/mouseOut.png"));
			mouseOverImage = ImageIO.read(new File("src/gui/button/mouseOver.png"));
			mouseDownImage = ImageIO.read(new File("src/gui/button/mouseDown.png"));
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
		this.repaint();
		button.repaint();
	}

	public void setGraphics(String type) {
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
