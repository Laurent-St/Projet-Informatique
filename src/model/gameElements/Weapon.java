/*
 * Classe abstraite, mère de tous les types d'armes (HandWeapon, ThrowableWeapon, Projectile)
 * Permet de définir des caractéristiques d'armes (dégat)
 * Doit être lié à un Actor (propriétaire de l'arme)
 */

package model.gameElements;


import java.awt.Rectangle;
import java.util.ArrayList;

import model.Game;

public abstract class Weapon extends CollectableObject {

	private static final long serialVersionUID = 1L;
	private int damage;
	private int manaConsumption;

	private Actor attachedActor;
	private boolean attackMode = false;


	public Weapon(String name, double x, double y, int damage, int manaConsumption, String image_url, Rectangle hitbox,
			Game game, Actor attachedActor) {
		super(name, x, y, image_url, hitbox, game);
		setDamage(damage);
		setAttachedActor(attachedActor);
		this.manaConsumption = manaConsumption;
	}

	private void setAttachedActor(Actor attachedActor) {
		this.attachedActor = attachedActor;
		this.attackMode = true;		
	}
	
	
	public Actor getAttachedActor() {
		return this.attachedActor;
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
		//Fonction s'exécutant lors de l'utilisation de l'arme
		//Fonction permettant de réduire la quantité de mana de l'actor lié à l'arme
		//attack() doit être redéfinie dans les types d'armes (selon leurs comportements)
		if(enoughMana()) {
			getAttachedActor().setMana(getAttachedActor().getMana()-getManaConsumption());
		}
	}
	
	public void inflictDirectDamage(ArrayList<Actor> hitActors) {
		//Inflige des dommages à tous les acteurs spécifiés en paramètre
		for (Actor a : hitActors) {
			a.setHealth(a.getHealth()-getDamage()-getAttachedActor().getDamage());
		}
	}
	
	public boolean inAttackMode() {
		return this.attackMode;
	}
	
	public int getManaConsumption() {
		return this.manaConsumption;
	}
	
	public boolean enoughMana() {
		//Vérifie si l'actor lié a assez de mana
		return getAttachedActor().getMana()>=getManaConsumption();
	}
	
}