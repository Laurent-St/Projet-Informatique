/*
 * Gestion d'évènement lors de la fermeture de la fenêtre.
 * Doit être lié à un Game afin de déclencher une sauvegarde automatique de la partie lors de la fermeture de celle-ci.
 */


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
