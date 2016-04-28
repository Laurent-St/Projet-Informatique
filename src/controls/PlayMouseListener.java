package controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;

public class PlayMouseListener implements MouseListener {
	private Game game;
	
	public PlayMouseListener(Game game){
		this.game=game;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		game.play(false);
		
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
