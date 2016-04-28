package model.gameElements;

import java.awt.Rectangle;
import java.util.Random;

import model.Game;

public class Monster extends Actor implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count;
	private transient Thread monsterThread;

	public Monster(String name, double x, double y, int damage, int health, double speed, Game game, Rectangle hitbox) {
		super(name, x, y, damage, health, speed, game, hitbox);
		monsterThread = new Thread(this);
		monsterThread.start();
		count = 0;
		// System.out.println("new Monster");

	}
	
	public void die() {
		monsterThread.interrupt();
		randomDrop();
		getGame().getCurrentMap().removeMonster(this);
	}
	
	public void activeMonsterThread(){		//va être utilisé pour la restauration de la sauvegarde.
		monsterThread=new Thread(this);
		monsterThread.start();
	}
	
	public void randomDrop() {
		Random random = new Random();
		int rnd = random.nextInt(100);
		if(rnd<=10) {
			getGame().getCurrentMap().addCollectableObject(new HealthPotion(getX(),getY(),50,getGame()));
		} else if(rnd>10 && rnd <=20) {
			getGame().getCurrentMap().addCollectableObject(new ManaPotion(getX(),getY(),50,getGame()));

		} else {
			getGame().getCurrentMap().addGameObject(new Skull(getX(),getY(),getGame()));
		}
		
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (count == 0) {
					Random randomGenerator = new Random();
					int randomNum = randomGenerator.nextInt(4);
					if (randomNum == 1) {
						setMoving("up");
					} else if (randomNum == 2) {
						setMoving("down");
					} else if (randomNum == 3) {
						setMoving("right");
					} else {
						setMoving("left");
					}
				}
				count += 1;
				if (count == 80) {
					count = 0;
				}
				if(count%40 == 0) {
					tryAttack();
				}
				move();
				Thread.sleep(15);
			}
		} catch (Exception e) {
		}
	}
	
	public void tryAttack() {
		Player player = getGame().getPlayer();
		int range = 15;
		
		Rectangle testHitbox1 = new Rectangle(getX()+(int)getHitbox().getMinX()-range,
				getY()+(int)getHitbox().getMinY()-range,
				(int)getHitbox().getWidth()+range,
				(int)getHitbox().getHeight()+range);
		Rectangle testHitbox2 = new Rectangle(player.getX()+(int)player.getHitbox().getMinX(),
				player.getY()+(int)player.getHitbox().getMinY(),
				(int)player.getHitbox().getWidth(),
				(int)player.getHitbox().getHeight());
		if(testHitbox1.intersects(testHitbox2)) {
			System.out.print("Player tabassé");
			player.setHealth(player.getHealth()-getDamage());
		}
				
	}

	public void interruptThread() {
		monsterThread.suspend();
	}
	
	public void resumeThread() {
		monsterThread.resume();
	}

}
