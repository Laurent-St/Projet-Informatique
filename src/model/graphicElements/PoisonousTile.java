package model.graphicElements;

import model.Game;
import model.PoisonRunnable;
import model.PoisonningObject;

public class PoisonousTile extends Tile implements PoisonningObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PoisonRunnable poisonRunnable;
	private transient Thread poisonThread;
	private boolean poisonThreadRunning = false;
	
	public PoisonousTile(Game game) {
		super(TileLibrary.POISON_TRAP, "poisonTile", game);
		setIsPoisonous(true);
		poisonThread=null;
		poisonRunnable = new PoisonRunnable(game,this);
	}
	
	public void envenom() {
		if(poisonThread==null){
			setPoisonThreadRunning(true);
			poisonThread = new Thread(poisonRunnable);
			poisonThread.start();	
		}
		if (!poisonThreadRunning) {
			setPoisonThreadRunning(true);
			poisonThread = new Thread(poisonRunnable);
			poisonThread.start();
		}
	}
	
	public void setPoisonThreadRunning(boolean isPoisonThreadRunning){
		poisonThreadRunning=isPoisonThreadRunning;
	}	
}
