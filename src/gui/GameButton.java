package gui;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class GameButton extends JButton {
	
	public GameButton(String label) {
		super();
		
		this.setFont(FontLoader.getFont());
		
		try {
			Image buttonImg = ImageIO.read(getClass().getResource("button.png"));
			this.setIcon(new ImageIcon(buttonImg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setText(label);
	}
	
	
}
