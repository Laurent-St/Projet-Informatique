package gameElements;

import java.awt.Rectangle;

import animation.CountTimer;
import animation.CountTimerListener;
import gui.level.LevelPanel;
import java.awt.Graphics;

public class Weapon extends CollectableObject implements CountTimerListener {
	private int damage;
	private int range;
	private CountTimer attackAnimationCount;
	private Player attachedPlayer;
	private boolean attackMode = false;
	// range=1 correspond à la case d'à côté(arme au cac), range>1 correspond à
	// une arme à distance.

	public Weapon(String name, int x, int y, int damage, int range, String image_url, Rectangle hitbox,
			LevelPanel level, Player attachedPlayer) {
		super(name, x, y, image_url, hitbox, level);
		setDamage(damage);
		setRange(range);
		setAttachedPlayer(attachedPlayer);
		
		attackAnimationCount = new CountTimer(5,1000,this);
		attackAnimationCount.stop();
		attackAnimationCount.setCount(4);
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

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		if (range < 1) {
			throw new RuntimeException("Portée négative");
		} else {
			this.range = range;
		}
	}
	
	public void attack() {
		attackAnimationCount = null;
		attackAnimationCount = new CountTimer(5,50,this);
		
	}

	@Override
	public void atCount(CountTimer ck) {
		this.repaint();
		if (ck.getCount()==4) {
			ck.stop();
			ck.setCount(4);
		}
	}
	
	protected void paintComponent(Graphics g) {
		if(attackMode) {
			Player p = attachedPlayer;
			int c = attackAnimationCount.getCount();
			
			if(p.getOrientation()=="up") {
				g.drawImage(this.getImage(), p.getX()+31, p.getY()-20, p.getX(),p.getY()+11,
					32*c,32,32*c+31,63,null);
			} else if (p.getOrientation()=="down") {
				g.drawImage(this.getImage(), p.getX(), p.getY()+15, p.getX()+31,p.getY()+46,
						32*c,32,32*c+31,63,null);
			} else if (p.getOrientation()=="left") {
				g.drawImage(this.getImage(), p.getX()+21, p.getY(), p.getX()-10,p.getY()+31,
						32*c,32,32*c+31,63,null);
			} else {
				g.drawImage(this.getImage(), p.getX()+10, p.getY(), p.getX()+41,p.getY()+31,
						32*c,32,32*c+31,63,null);
			}
		} else {
			super.paintComponent(g);
		}
	}
}