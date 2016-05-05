/*
 * Classe qui gère l'IA des Monster avec un Thread. Le déplacement des Monster est aléatoire,
 * ils ne font pas attention au Player. Le Boss dispose de plus d'attaques que les Monster
 */

package model;

import java.util.ArrayList;
import java.util.Random;

import model.gameElements.Boss;
import model.gameElements.Monster;
import model.gameElements.Zombie;

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
					if (m.getCount()%80 == 0) {
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
					
					if(m.isDead()) {
						game.getCurrentMap().removeMonster(m);
					}
					m.setCount(m.getCount()+1);
					if (m instanceof Zombie){
						if (m.getCount() == 81) {
							m.setCount(1);
						}
						if(m.getCount()%40 == 0) {
							m.tryAttack();	
						}
					}
					else {	//(m instanceof Boss)
						Boss b=(Boss)m;
						if (b.getCount()==800){
							b.setCount(0);
						}
						if (b.getCount()%400==0){
							b.poisonAttack();
						}
						if (b.getCount()%200==0){
							b.tryAttack();
							b.fireBallAttack();
						}
						if (b.getCount()%100==0){
							b.tryAttack();
						}
					}
					m.move();
					
					
				}
			}
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
