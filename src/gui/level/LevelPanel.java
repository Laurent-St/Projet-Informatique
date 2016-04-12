package gui.level;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import gui.GamePanel;
import levels.LevelData;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel {
	
	private LevelData levelData;
	private GamePanel gamePanel;
	private LevelBackground levelBackground;
	private static Rectangle levelBounds = new Rectangle(20,20,960,680);
	
	public LevelPanel(LevelData levelData, GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.levelData = levelData;
		this.setLayout(null);
		this.setBounds((int)getLevelBounds().getX(),(int)getLevelBounds().getY(),(int)getLevelBounds().getWidth(),(int)getLevelBounds().getHeight());
		levelBackground = new LevelBackground(this);
	}
	
	public void activate() {
		levelBackground.repaint();
		gamePanel.removeAll();
		gamePanel.addPanel(this);
		this.setVisible(true);
		levelBackground.repaint();
		this.repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public LevelData getLevelData() {
		return this.levelData;
	}
	
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}
	
	public Rectangle getLevelBounds() {
		return levelBounds;
	}

}
