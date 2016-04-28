package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Game;

public class WindowListener extends WindowAdapter {
	
	private Game game;
	
	public WindowListener(Game game) {
		this.game = game;
	}
	
	public void windowClosing(WindowEvent e){
		game.saveGame();
		System.exit(0);
	}
}
