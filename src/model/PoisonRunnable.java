package model;

import java.io.Serializable;

import model.gameElements.Player;

public class PoisonRunnable implements Serializable, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private Player player;
	private PoisonningObject poisonner;
	
	public PoisonRunnable(Game game, PoisonningObject poisonner){
		this.game=game;
		this.poisonner = poisonner;
	}
	@Override
	public void run() {
		player=game.getPlayer();
		int i = 0;
		while (i < 4) {
			player.setHealth(player.getHealth() - 10);
			i++;
			try {
				Thread.sleep(1000);

			} catch (Exception e) {
			}
		}
		poisonner.setPoisonThreadRunning(false);
	}
}