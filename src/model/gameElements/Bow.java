/*
 * Arme à distance qui crée des Arrow et les envoie (en redéfinissant la fonction attack() de ThrowableWeapon).
 */

package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Bow extends ThrowableWeapon {
	
	private static final long serialVersionUID = 1L;
	private static Rectangle hitbox = new Rectangle(0,0,32,32);
	private static int damage = 30;
	private static int bowMana = 5;

	
	public Bow(Game game, Player attachedPlayer) {
		super("Bow",0, 0, damage, bowMana, "src/model/gameElements/bow.png", hitbox,game,attachedPlayer);
	}
	
	public void attack() {
		if(enoughMana()) {
			Actor a = getAttachedActor();
			Arrow arrow = new Arrow(0, 0, a.getOrientation(), getGame(), a);
			super.attack(arrow);
		}
	}
	
}
