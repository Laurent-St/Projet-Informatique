package model.gameElements;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import model.Game;

public class Monster extends Actor implements Runnable {

	private int count;

	public Monster(String name, double x, double y, int damage, int health, double speed, Game game, Rectangle hitbox) {
		super(name, x, y, damage, health, speed, game, hitbox);
		Thread monsterThread = new Thread(this);
		monsterThread.start();
		count = 0;
		// System.out.println("new Monster");

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

}
