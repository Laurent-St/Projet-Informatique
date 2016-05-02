package model;

import java.util.ArrayList;
import java.util.Random;

import model.gameElements.Monster;

public class MonsterAIRunnable implements Runnable {
	
	private Game game;
	
	public MonsterAIRunnable(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		while(true) {
			if(game.getCurrentMap()!=null) {
				@SuppressWarnings("unchecked")
				ArrayList<Monster> monsters = (ArrayList<Monster>) game.getCurrentMap().getMonsters().clone();
				for(Monster m: monsters) {
					if (m.getCount() == 0) {
						Random randomGenerator = new Random();
						int randomNum = randomGenerator.nextInt(4);
						if (randomNum == 1) {
							m.setMoving("up");
						} else if (randomNum == 2) {
							m.setMoving("down");
						} else if (randomNum == 3) {
							m.setMoving("right");
						} else {
							m.setMoving("left");
						}
					}
					m.setCount(m.getCount()+1);
					if (m.getCount() == 80) {
						m.setCount(0);
					}
					if(m.getCount()%40 == 0) {
						m.tryAttack();
					}
					
					m.move();
					
					if(m.isDead()) {
						game.getCurrentMap().removeMonster(m);
					}
				}
			}
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
