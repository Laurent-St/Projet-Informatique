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
<<<<<<< HEAD
	private JLabel attackBonus;
=======
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
		healthLabel.setBounds(50,17,50,15);
=======
		healthLabel.setBounds(50,15,50,15);
>>>>>>> refs/remotes/origin/master
		healthLabel.setForeground(Color.WHITE);
		
		levelLabel = new JLabel();
		levelLabel.setFont(FontLoader.getFont(8));
<<<<<<< HEAD
		levelLabel.setBounds(500,17,100,15);
=======
		levelLabel.setBounds(500,15,50,15);
>>>>>>> refs/remotes/origin/master
		levelLabel.setForeground(Color.WHITE);
		
		manaLabel = new JLabel();
		manaLabel.setText("Mana");
		manaLabel.setFont(FontLoader.getFont(8));
<<<<<<< HEAD
		manaLabel.setBounds(250,17,50,15);
		manaLabel.setForeground(Color.WHITE);
		
		attackBonus = new JLabel();
		attackBonus.setText("Attack Bonus");
		attackBonus.setFont(FontLoader.getFont(8));
		attackBonus.setBounds(800,17,150,15);
		attackBonus.setForeground(Color.WHITE);
		
		xpLabel = new JLabel();
		xpLabel.setText("XP");
		xpLabel.setFont(FontLoader.getFont(8));
		xpLabel.setBounds(600,17,50,15);
=======
		manaLabel.setBounds(250,15,50,15);
		manaLabel.setForeground(Color.WHITE);
		
		xpLabel = new JLabel();
		xpLabel.setText("Experience");
		xpLabel.setFont(FontLoader.getFont(8));
		xpLabel.setBounds(550,15,50,15);
>>>>>>> refs/remotes/origin/master
		xpLabel.setForeground(Color.WHITE);
		
		health = new JProgressBar();
		health.setBounds(100, 15, barSize.width, barSize.height);
<<<<<<< HEAD
		health.setForeground(Color.RED);
		health.setBackground(Color.GRAY);
		
		xp = new JProgressBar();
		xp.setBounds(650, 15, barSize.width, barSize.height);
		xp.setForeground(Color.GREEN);
		xp.setBackground(Color.GRAY);
		
		mana = new JProgressBar();
		mana.setBounds(300, 15, barSize.width, barSize.height);
		mana.setForeground(Color.BLUE);
		mana.setBackground(Color.GRAY);
=======
		
		xp = new JProgressBar();
		xp.setBounds(600, 15, barSize.width, barSize.height);
		
		mana = new JProgressBar();
		mana.setBounds(300, 15, barSize.width, barSize.height);
>>>>>>> refs/remotes/origin/master
		
		this.add(healthLabel);
		this.add(levelLabel);
		this.add(manaLabel);
		this.add(xpLabel);
		this.add(health);
		this.add(xp);
		this.add(mana);
<<<<<<< HEAD
		this.add(attackBonus);
=======
>>>>>>> refs/remotes/origin/master
		
		healthLabel.setVisible(true);
		levelLabel.setVisible(true);
		manaLabel.setVisible(true);
		xpLabel.setVisible(true);
		health.setVisible(true);
		xp.setVisible(true);
		mana.setVisible(true);
<<<<<<< HEAD
		attackBonus.setVisible(true);
=======
>>>>>>> refs/remotes/origin/master
		
		update();
	}
	
	public void update() {
<<<<<<< HEAD
		mana.setValue((int) Math.floor(100/player.getMaxMana()*player.getMana()));
		xp.setValue((int) Math.floor(100/player.getMaxExperienceForLevel()*player.getExperience()));
		health.setValue((int) Math.floor(100/player.getMaxHealth()*player.getHealth()));
		levelLabel.setText("Level "+String.valueOf(player.getLevel()));
=======
		//mana.setValue((int) Math.floor(100/player.getMaxMana()*player.getMana()));
		//xp.setValue((int)Math.floor(100/player.getMaxExperienceForLevel()*player.getExperience()));
>>>>>>> refs/remotes/origin/master
	}

}
