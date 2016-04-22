package view.level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JPanel;

import model.graphicElements.TileLibrary;
import model.map.Map;

@SuppressWarnings("serial")
public class LevelBackground extends JPanel {
	
	private LevelPanel levelPanel;
	
	public LevelBackground(LevelPanel levelPanel) {
		this.levelPanel = levelPanel;
		Rectangle bounds = levelPanel.getLevelPanelBounds();
		
		this.setBounds(0, 0, (int)bounds.getWidth(),(int)bounds.getHeight());
		levelPanel.add(this);
		this.setVisible(true);
		this.setLayout(null);
		System.out.println("new Beckground");
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image tilesImage = TileLibrary.getTilesImage();
		int size = TileLibrary.getTileSize();
		Map map = levelPanel.getMap();
		
		for(int i=0;i<map.getLevelWidth();i++) {
			for(int j=0;j<map.getLevelHeight();j++) {
				
				Rectangle r = TileLibrary.getBoundsOf(map.getTileAt(i, j));
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
