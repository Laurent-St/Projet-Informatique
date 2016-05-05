/*
 * Classe qui gère les clicks sur le bouton Rules du Menu.
 */


package controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;

public class InstructionsMouseListener implements MouseListener {
	
	private Game game;
	
	public InstructionsMouseListener(Game game) {
		this.game = game;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		game.switchToInstructionsMenu();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
