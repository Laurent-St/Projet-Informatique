package controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;

public class LoadMouseListener implements MouseListener {
	private Game game;
	public LoadMouseListener(Game game){
		this.game=game;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseclicked");
		game.play(true);
		
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
