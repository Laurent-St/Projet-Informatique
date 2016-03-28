package gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.button.GButton;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private static Dimension panelSize = new Dimension(1000,800);
	
	public GamePanel() {
		this.setLocation(0, 0);
		this.setPreferredSize(panelSize);
		this.setLayout(null);
		this.setVisible(true);	
		
		//TEST DE LA POLICE
		JLabel test = new JLabel();
		test.setText("Hello World");
		test.setFont(FontLoader.getFont());
		test.setBounds(10, 10,500,50);
		this.add(test);
		
		//TEST BOUTON
		GButton b = new GButton("Bouton", new Dimension(200,50));
		b.setLocation(100, 200);
		this.add(b);
	}
}
