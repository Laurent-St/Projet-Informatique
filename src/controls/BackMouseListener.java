/*
 * Classe qui gère les clicks sur le bouton Back dans la parte Rules.
 */


package controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;

public class BackMouseListener implements MouseListener {
		
	private Game game;
	
	public BackMouseListener(Game game) {
		this.game = game;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		game.backToMainMenu();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
