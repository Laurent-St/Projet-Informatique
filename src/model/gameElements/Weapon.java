package model.gameElements;


import java.awt.Rectangle;
import java.util.ArrayList;

import model.Game;

public class Weapon extends CollectableObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int damage;
	private int manaConsumption;

	private Actor attachedActor;
	private boolean attackMode = false;
	// range=1 correspond � la case d'� c�t�(arme au cac), range>1 correspond �
	// une arme � distance.

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
		if(enoughMana()) {
			getAttachedActor().setMana(getAttachedActor().getMana()-getManaConsumption());
		}
	}
	
	public void inflictDirectDamage(ArrayList<Actor> hitActors) {
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
		return getAttachedActor().getMana()>=getManaConsumption();
	}
	
}