/*
 * Classe de monstre zombie, le monstre g�n�rique du jeu.
 */

package model.gameElements;

import java.awt.Rectangle;
import java.util.Random;

import model.Game;

public class Zombie extends Monster {

	private static final long serialVersionUID = 1L;

	public Zombie(double x, double y, int damage, int health, double speed, int xp, Game game,
			Rectangle hitbox) {
		super("src/model/gameElements/zombie.png", x, y, damage, health, 0, speed, xp, game, hitbox);
	}
	
	public void randomDrop() {
		
		//Jette un objet al�atoire sur la map (arme, potion ou crane)
		
		Random random = new Random();
		int rnd = random.nextInt(100);
		if(rnd<=10) {
			getGame().getCurrentMap().addCollectableObject(new HealthPotion(getX(),getY(),45+getDamage(),getGame()));
		} else if(rnd>10 && rnd <=20) {
			getGame().getCurrentMap().addCollectableObject(new ManaPotion(getX(),getY(),45+getDamage(),getGame()));

		} else if(rnd==21) {
			getGame().getCurrentMap().addCollectableObject(new Lightsaber("Lightsaber",getX(),getY(),getGame(),getGame().getPlayer()));
		} else {
			getGame().getCurrentMap().addGameObject(new Skull(getX(),getY(),getGame()));
		}
	}
	
	public void die() {
		randomDrop();
		getGame().getPlayer().gainXP(this.getXPReward()); //Gain d'XP pour le joueur
		super.die();
	}

}
