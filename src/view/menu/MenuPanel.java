/* CLASSE ABSTRAITE
 * Cette classe sert de base pour les différentes pages de menu
 */

package view.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import view.GamePanel;

public abstract class MenuPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image background;
	
	public MenuPanel(GamePanel gp) {
		
		//Définition du layout et de la taille de la page de menu
		
		this.setLayout(null);
		this.setBounds(0,0,(int)gp.getDimensions().getWidth(),(int)gp.getDimensions().getHeight());
	}
	
	public void setBackgroundImage(String ImageURL) {
		
		//Défini une image de fond pour la page de menu
		
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
