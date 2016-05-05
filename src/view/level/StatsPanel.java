/*
 * Classe graphique permettant d'afficher les statistiques du joueur.
 * Celle-ci doit être initialisée avec un joueur en paramètre.
 * Mise à jour de l'affichage par la fonction update
 */

package view.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.gameElements.Player;
import view.FontLoader;

public class StatsPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static Rectangle panelBounds = new Rectangle(0,750,1000,50);
	private Player player;
	
	private JLabel healthLabel;
	private JLabel levelLabel;
	private JLabel manaLabel;
	private JLabel xpLabel;

	private JLabel attackBonus;

	private JProgressBar health;
	private JProgressBar xp;
	private JProgressBar mana;
	
	private Dimension barSize = new Dimension(100,20);
	
	public StatsPanel(Player player){
		
		//Définition de la mise en page du StatsPanel
		
		this.player = player;
		this.setBounds(panelBounds);
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);
		
		healthLabel = new JLabel();
		healthLabel.setText("Health");
		healthLabel.setFont(FontLoader.getFont(8));
		healthLabel.setBounds(50,17,50,15);
		healthLabel.setForeground(Color.WHITE);
		
		levelLabel = new JLabel();
		levelLabel.setFont(FontLoader.getFont(8));
		levelLabel.setBounds(500,17,100,15);
		levelLabel.setForeground(Color.WHITE);
		
		manaLabel = new JLabel();
		manaLabel.setText("Mana");
		manaLabel.setFont(FontLoader.getFont(8));
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
		xpLabel.setForeground(Color.WHITE);
		
		health = new JProgressBar();
		health.setBounds(100, 15, barSize.width, barSize.height);
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
		
		this.add(healthLabel);
		this.add(levelLabel);
		this.add(manaLabel);
		this.add(xpLabel);
		this.add(health);
		this.add(xp);
		this.add(mana);
		this.add(attackBonus);

		
		healthLabel.setVisible(true);
		levelLabel.setVisible(true);
		manaLabel.setVisible(true);
		xpLabel.setVisible(true);
		health.setVisible(true);
		xp.setVisible(true);
		mana.setVisible(true);
		attackBonus.setVisible(true);
		
		update();
	}
	
	public void update() {
		
		//Mise à jour de l'affichage, requiert un accès aux différentes propriétés du joueur
		
		mana.setValue((int) Math.floor(100.0/(double)player.getMaxMana()*(double)player.getMana()));
		xp.setValue((int) Math.floor(100.0/(double)player.getMaxExperienceForLevel()*(double)player.getExperience()));
		health.setValue((int) Math.floor(100.0/(double)player.getMaxHealth()*(double)player.getHealth()));
		levelLabel.setText("Level "+String.valueOf(player.getLevel()));
		attackBonus.setText("Damage Bonus: "+String.valueOf(player.getDamage()));
		
		//Texte en rouge en-dessous de valeurs critiques
		
		if(player.getMana()<=10) {
			manaLabel.setForeground(Color.RED);
		} else {
			manaLabel.setForeground(Color.WHITE);
		}
		
		if(player.getHealth()<=10) {
			healthLabel.setForeground(Color.RED);
		} else {
			healthLabel.setForeground(Color.WHITE);
		}
		xp.repaint();
		health.repaint();
	}

}
