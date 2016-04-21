package view.level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JComponent;

import model.graphicElements.Tile;
import model.graphicElements.TileLibrary;

@SuppressWarnings("serial")
public class LevelBackground extends JComponent {
	
	private LevelPanel levelPanel;
	
	public LevelBackground(LevelPanel levelPanel) {
		this.levelPanel = levelPanel;
		Rectangle bounds = levelPanel.getLevelPanelBounds();
		
		this.setBounds(0, 0, (int)bounds.getWidth(),(int)bounds.getHeight());
		levelPanel.add(this);
		this.setVisible(true);
		this.setLayout(null);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image tilesImage = TileLibrary.getTilesImage();
		int size = TileLibrary.getTileSize();
		
		for(int i=0;i<levelPanel.getLevelWidth();i++) {
			for(int j=0;j<levelPanel.getLevelHeight();j++) {
				
				Rectangle r = TileLibrary.getBoundsOf(levelPanel.getTileAt(i, j));
				g.drawImage(tilesImage,
						i*size, j*size, (i+1)*size, (j+1)*size,
						(int)r.getX(),
						(int)r.getY(),
						(int)(r.getX()+r.getWidth()),
						(int)(r.getY()+r.getHeight()),
						null);
			}
		}
	}

}
