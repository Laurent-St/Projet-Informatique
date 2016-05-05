/*
 * Classe abstraite, m�re de tous les types d'armes (HandWeapon, ThrowableWeapon, Projectile)
 * Permet de d�finir des caract�ristiques d'armes (d�gat)
 * Doit �tre li� � un Actor (propri�taire de l'arme)
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
			throw new RuntimeException("D�g�ts n�gatifs: valeur mise � 0");
		}
	}
	
	public void attack() {
		//Fonction s'ex�cutant lors de l'utilisation de l'arme
		//Fonction permettant de r�duire la quantit� de mana de l'actor li� � l'arme
		//attack() doit �tre red�finie dans les types d'armes (selon leurs comportements)
		if(enoughMana()) {
			getAttachedActor().setMana(getAttachedActor().getMana()-getManaConsumption());
		}
	}
	
	public void inflictDirectDamage(ArrayList<Actor> hitActors) {
		//Inflige des dommages � tous les acteurs sp�cifi�s en param�tre
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
		//V�rifie si l'actor li� a assez de mana
		return getAttachedActor().getMana()>=getManaConsumption();
	}
	
}