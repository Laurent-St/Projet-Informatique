package gameElements;

import java.awt.Graphics;
import java.awt.Rectangle;

import animation.CountTimer;
import animation.CountTimerListener;
import gui.level.LevelPanel;

public class HandWeapon extends Weapon implements CountTimerListener  {
	
	private int range;
	private CountTimer attackAnimationCount;
	
	public HandWeapon(String name, int x, int y, int damage, int range, String image_url, Rectangle hitbox,
			LevelPanel level, Player attachedPlayer) {
		super(name, x, y, damage, image_url, hitbox, level, attachedPlayer);
		
		setAttackRange(range);
		attackAnimationCount = new CountTimer(5,1000,this);
		attackAnimationCount.stop();
		attackAnimationCount.setCount(4);
	}
	
	public void setAttackRange(int range) {
		if (range < 1) {
			throw new RuntimeException("Portée négative");
		} else {
			this.range = range;
		}
	}
	
	public int getRange() {
		return range;
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
		if(inAttackMode()) {
			Player p = getAttachedPlayer();
			int c = attackAnimationCount.getCount();
			
			if(p.getOrientation()=="up") {
				g.drawImage(this.getImage(), p.getX(), p.getY()-20, p.getX()+31,p.getY()+11,
					32*c,64,32*c+31,95,null);
			} else if (p.getOrientation()=="down") {
				g.drawImage(this.getImage(), p.getX(), p.getY()+31+25, p.getX()+31,p.getY()+25,
						32*c,64,32*c+31,93,null);
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
