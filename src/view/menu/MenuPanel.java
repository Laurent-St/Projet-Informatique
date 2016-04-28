package view.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import view.GamePanel;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
	
	private Image background;
	
	public MenuPanel(GamePanel gp) {
		this.setLayout(null);
		this.setBounds(0,0,(int)gp.getDimensions().getWidth(),(int)gp.getDimensions().getHeight());
		gp.removePanels();
		gp.add(this);
		this.setVisible(true);
	}
	
	public void setBackgroundImage(String ImageURL) {
		try {
			background = ImageIO.read(new File(ImageURL));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.background,0,0,null);
	}

}
