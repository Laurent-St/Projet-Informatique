/*
 * InstructionsMenu : Menu affichant les règles du jeu et les contrôles
 */

package view.menu;

import java.awt.Dimension;

import controls.BackMouseListener;
import model.Game;
import view.button.GButton;

public class InstructionsMenu extends MenuPanel {

	private static final long serialVersionUID = 1L;
	
	private GButton back;

	public InstructionsMenu(Game game) {
		super(game.getGamePanel());
		
		//Définition du contenu du menu et initialisation des écouteurs d'évemenents pour les boutons
		
		this.setBackgroundImage("src/view/menu/instructionsMenu.png");
		back = new GButton("Back",24,new Dimension(250,75));
		back.setLocation(700, 675);
		back.addTo(this);
		back.addMouseListener(new BackMouseListener(game));
		back.setVisible(true);
	}

}
