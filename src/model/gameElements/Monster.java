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
	private Thread monsterThread;

	public Monster(String name, double x, double y, int damage, int health, double speed, Game game, Rectangle hitbox) {
		super(name, x, y, damage, health, speed, game, hitbox);
		monsterThread = new Thread(this);
		monsterThread.start();
		count = 0;
		// System.out.println("new Monster");

	}
	
	public void die() {
		monsterThread.stop();
		randomDrop();
		getGame().getCurrentMap().removeMonster(this);
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
				move();
				Thread.sleep(15);
			}
		} catch (Exception e) {
		}
	}

	public void interruptThread() {
		monsterThread.suspend();
	}
	
	public void resumeThread() {
		monsterThread.resume();
	}

}
