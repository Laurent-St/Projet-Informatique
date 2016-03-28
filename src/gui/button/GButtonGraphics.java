package gui.button;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GButtonGraphics extends JPanel {
	
	private BufferedImage mouseOutImage;
	private GButton button;
	
	public GButtonGraphics(GButton button) {
		try {
			mouseOutImage = ImageIO.read(new File("src/gui/button/mouseOut.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.button = button;
		this.setLayout(null);
		this.setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		mouseOutImage.getScaledInstance(10,10,Image.SCALE_DEFAULT);
		g.drawImage(mouseOutImage, 0, 0,(int)button.getDimensions().getWidth(),(int)button.getDimensions().getHeight(), null);
		
	}
	
	public void updateImageSize() {
		this.repaint();
	}
}
