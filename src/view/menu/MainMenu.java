package view.menu;

import java.awt.Dimension;

import javax.swing.JButton;

import controls.InstructionsMouseListener;
import controls.LoadMouseListener;
import controls.PlayMouseListener;
import model.Game;
import view.GamePanel;
import view.button.GButton;

@SuppressWarnings("serial")
public class MainMenu extends MenuPanel {
	
	private GButton playButton;
	private GButton instructionsButton;
	private GButton loadButton;
	
	public MainMenu(Game game) {
		super(game.getGamePanel());
		this.setBackgroundImage("src/view/menu/gameMenu.png");
		
		playButton = new GButton("PLAY",24,new Dimension(250,100));
		playButton.setLocation(700, 650);
		playButton.addTo(this);
		playButton.addMouseListener(new PlayMouseListener(game));
		playButton.setVisible(true);
		
		instructionsButton = new GButton("Rules", 18, new Dimension(250,66));
		instructionsButton.setLocation(700, 550);
		instructionsButton.addTo(this);
		instructionsButton.setVisible(true);
		instructionsButton.addMouseListener(new InstructionsMouseListener(game));
		
		loadButton=new GButton("LOAD", 18, new Dimension(250,66));
		loadButton.setLocation(700, 450);
		loadButton.addTo(this);
		loadButton.addMouseListener(new LoadMouseListener(game));
	}

}
