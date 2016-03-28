package gui.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GButtonGraphicsMouse implements MouseListener {
	
	private GButtonGraphics button;
	
	public GButtonGraphicsMouse(GButtonGraphics b) {
		button = b;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		button.setGraphics("over");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		button.setGraphics("out");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		button.setGraphics("down");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button.setGraphics("over");
	}

}
