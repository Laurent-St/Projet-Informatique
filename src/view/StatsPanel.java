package view;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Game;
import model.gameElements.Player;

public class StatsPanel extends JPanel{
	
	private static Rectangle panelBounds = new Rectangle(0,750,1000,50);
	private Game game;
	private Player player;
	
	private JLabel healthLabel;
	private JLabel levelLabel;
	private JLabel manaLabel;
	private JProgressBar health;
	private JProgressBar level;
	private JProgressBar mana;
	
	public StatsPanel(Game game){
		this.game = game;
		this.player = game.getPlayer();
		this.setBounds(panelBounds);
		this.setLayout(null);
		this.setBackground(Color.BLACK);
	}
	
	public void update() {
		
	}

}
