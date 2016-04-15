package gui.level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JComponent;

import gui.Tile;
import gui.TileLibrary;
import levels.LevelData;

@SuppressWarnings("serial")
public class LevelBackground extends JComponent {
	
	private LevelPanel levelPanel;
	private LevelData levelData;
	
	public LevelBackground(LevelPanel levelPanel) {
		this.levelPanel = levelPanel;
		this.levelData = levelPanel.getLevelData();
		Rectangle bounds = levelPanel.getLevelBounds();
		
		this.setBounds(0, 0, (int)bounds.getWidth(),(int)bounds.getHeight());
		levelPanel.add(this);
		this.setVisible(true);
		this.setLayout(null);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image tilesImage = TileLibrary.getTilesImage();
		int size = TileLibrary.getTileSize();
		
		for(int i=0;i<levelData.getLevelWidth();i++) {
			for(int j=0;j<levelData.getLevelHeight();j++) {
				
				Rectangle r = TileLibrary.getBoundsOf(levelData.getTileAt(i, j));
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
