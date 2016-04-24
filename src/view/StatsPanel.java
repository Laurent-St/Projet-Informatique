package view;

import java.awt.Color;
import java.awt.Dimension;
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
	private JLabel xpLabel;
	private JProgressBar health;
	private JProgressBar xp;
	private JProgressBar mana;
	
	private Dimension barSize = new Dimension(100,20);
	
	public StatsPanel(Game game){
		this.game = game;
		this.player = game.getPlayer();
		this.setBounds(panelBounds);
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		
		healthLabel = new JLabel();
		healthLabel.setText("Health");
		healthLabel.setFont(FontLoader.getFont(8));
		healthLabel.setBounds(50,15,50,15);
		healthLabel.setForeground(Color.WHITE);
		
		levelLabel = new JLabel();
		levelLabel.setFont(FontLoader.getFont(8));
		levelLabel.setBounds(500,15,50,15);
		levelLabel.setForeground(Color.WHITE);
		
		manaLabel = new JLabel();
		manaLabel.setText("Mana");
		manaLabel.setFont(FontLoader.getFont(8));
		manaLabel.setBounds(250,15,50,15);
		manaLabel.setForeground(Color.WHITE);
		
		xpLabel = new JLabel();
		xpLabel.setText("Experience");
		xpLabel.setFont(FontLoader.getFont(8));
		xpLabel.setBounds(550,15,50,15);
		xpLabel.setForeground(Color.WHITE);
		
		health = new JProgressBar();
		health.setBounds(100, 15, barSize.width, barSize.height);
		
		xp = new JProgressBar();
		xp.setBounds(600, 15, barSize.width, barSize.height);
		
		mana = new JProgressBar();
		mana.setBounds(300, 15, barSize.width, barSize.height);
		
		this.add(healthLabel);
		this.add(levelLabel);
		this.add(manaLabel);
		this.add(xpLabel);
		this.add(health);
		this.add(xp);
		this.add(mana);
		
		healthLabel.setVisible(true);
		levelLabel.setVisible(true);
		manaLabel.setVisible(true);
		xpLabel.setVisible(true);
		health.setVisible(true);
		xp.setVisible(true);
		mana.setVisible(true);
		
		update();
	}
	
	public void update() {
		//mana.setValue((int) Math.floor(100/player.getMaxMana()*player.getMana()));
		//xp.setValue((int)Math.floor(100/player.getMaxExperienceForLevel()*player.getExperience()));
	}

}
