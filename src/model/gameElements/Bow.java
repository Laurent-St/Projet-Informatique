package model.gameElements;

import java.awt.Rectangle;

import model.Game;

public class Bow extends ThrowableWeapon {
	
	private static Rectangle hitbox = new Rectangle(0,0,32,32);
	private static int damage = 30;
	private static int bowMana = 5;

	
	public Bow(Game game, Player attachedPlayer) {
		super("Fireballs",0, 0, damage, bowMana, "src/model/gameElements/bow.png", hitbox,game,attachedPlayer);
	}
	
	public void attack() {
		if(enoughMana()) {
			Player p = getAttachedPlayer();
			Arrow a = new Arrow(0, 0, p.getOrientation(), getGame(), p);
			super.attack(a);
		}
	}
	
}
