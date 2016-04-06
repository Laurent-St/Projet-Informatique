package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JPanel;

import levels.LevelData;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel {
	
	private LevelData levelData;
	private GamePanel gamePanel;
	
	public LevelPanel(LevelData levelData, GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.levelData = levelData;
		this.setLayout(null);
		this.setBounds(0,0,(int)gamePanel.getDimensions().getWidth(),(int)gamePanel.getDimensions().getHeight());
	}
	
	public void activate() {
		gamePanel.removeAll();
		gamePanel.addPanel(this);
		this.setVisible(true);
		this.repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image tiles = Tile.getTilesImage();
		int size = Tile.getTileSize();
		
		for(int i=0;i<levelData.getLevelWidth();i++) {
			for(int j=0;j<levelData.getLevelHeight();j++) {
				
				Rectangle r = Tile.getBoundsOf(levelData.getTileAt(i, j));
				g.drawImage(tiles,
						i*size, j*size, (i+1)*size, (j+1)*size,
						(int)r.getX(),(int)r.getY(),(int)(r.getX()+r.getWidth()),(int)(r.getY()+r.getHeight()),
						null);
			}
		}
	}

}
