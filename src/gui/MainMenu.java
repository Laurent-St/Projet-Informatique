package gui;

import java.awt.Dimension;
import gui.MenuPanel;
import gui.button.GButton;

@SuppressWarnings("serial")
public class MainMenu extends MenuPanel {
	
	private GButton playButton;
	private GButton instructionsButton;
	
	public MainMenu(GamePanel game) {
		super(game);
		this.setBackgroundImage("src/gui/gameMenu.png");
		
		playButton = new GButton("PLAY",24,new Dimension(250,100));
		playButton.setLocation(700, 650);
		playButton.addTo(this);
		
		instructionsButton = new GButton("Rules", 18, new Dimension(250,66));
		instructionsButton.setLocation(700, 550);
		instructionsButton.addTo(this);
	}

}
