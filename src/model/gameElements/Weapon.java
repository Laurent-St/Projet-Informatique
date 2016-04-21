package model.gameElements;


import java.awt.Rectangle;

import model.Game;

public class Weapon extends CollectableObject {
	private int damage;

	private Player attachedPlayer;
	private boolean attackMode = false;
	// range=1 correspond à la case d'à côté(arme au cac), range>1 correspond à
	// une arme à distance.

	public Weapon(String name, double x, double y, int damage, String image_url, Rectangle hitbox,
			Game game, Player attachedPlayer) {
		super(name, x, y, image_url, hitbox, game);
		setDamage(damage);
		setAttachedPlayer(attachedPlayer);
	}

	private void setAttachedPlayer(Player attachedPlayer) {
		this.attachedPlayer = attachedPlayer;
		this.attackMode = true;
		this.setX(0);
		this.setY(0);
		
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		if (damage >= 0) {
			this.damage = damage;
		} else {
			this.damage = 0;
			throw new RuntimeException("Dégâts négatifs: valeur mise à 0");
		}
	}
	
	public void attack() {
		//A REDEFINIR SELON LE COMPORTEMENT DES ARMES
	}
	
	public Player getAttachedPlayer() {
		return this.attachedPlayer;
	}
	
	public boolean inAttackMode() {
		return this.attackMode;
	}
}