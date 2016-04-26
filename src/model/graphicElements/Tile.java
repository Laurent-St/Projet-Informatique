package model.graphicElements;

import model.Game;
import model.PoisonDamage;

public class Tile implements PoisonDamage, Runnable {

	private int[] reference = { 0, 0 };
	private boolean isWalkable = true; // pour savoir si on peut marcher dessus
	private boolean isPoisonous;
	private String type;
	private Game game;
	private Thread tileThread;
	private boolean tileThreadRunning = false;

	public Tile(int[] ref, String type, Game game) {
		// System.out.println("constru Tile");
		this.game=game;
		reference[0] = ref[0];
		reference[1] = ref[1];
		this.type = type;
		if (reference[0] == 18) {
			isPoisonous = true;
		}
	}

	public void run() {
		int i = 0;
		while (i < 4) {
			game.getPlayer().setHealth(game.getPlayer().getHealth() - 10);
			i++;
			try {
				Thread.sleep(1000);

			} catch (Exception e) {
			}
		}
		//Thread.currentThread().interrupt();
		tileThreadRunning = false;
	}

	public void envenom() {
		if(tileThread==null){
			tileThreadRunning = true;
			tileThread = new Thread(this);
			tileThread.start();
		}
		if (!tileThreadRunning) {
			tileThreadRunning = true;
			tileThread = new Thread(this);
			tileThread.start();
		}
	}

	public boolean getIsPoisonous() {
		return isPoisonous;
	}

	public boolean getIsWalkable() {
		return isWalkable;
	}

	public void setIsWalkable(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}

	public int[] getReference() {
		return reference;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
}
