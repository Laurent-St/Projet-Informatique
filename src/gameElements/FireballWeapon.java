package gameElements;

import java.awt.Graphics;
import java.awt.Rectangle;

import gui.level.LevelPanel;

public class FireballWeapon extends ThrowableWeapon {
	
	private static Rectangle hitbox = new Rectangle(-16,-16,32,32);
	private static int damage = 20;

	
	public FireballWeapon(LevelPanel levelPanel, Player attachedPlayer) {
		super("Fireballs",0, 0, damage, "src/gameElements/fireball.png", hitbox,levelPanel,attachedPlayer);
	}
	
	public void attack() {
		Player p = getAttachedPlayer();
		Rectangle ph = p.getHitbox();
		Fireball f = new Fireball(0, 0, p.getOrientation(), getLevelPanel(), p);
		super.attack(f);
	}
	
	protected void paintComponent(Graphics g) {
		
	}

}
