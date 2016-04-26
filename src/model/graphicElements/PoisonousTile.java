//package model.graphicElements;
//
//import model.Game;
//import model.PoisonThread;
//
//public class PoisonousTile extends Tile{
//	private PoisonThread poisonThread;
//	private boolean poisonThreadRunning;
//	private Game game;
//
//	public PoisonousTile(int[] reference, String type, Game game) {
//		super(reference, type, game);
//		setIsPoisonous(true);
//		poisonThread=null;
//		poisonThreadRunning=false;
//	}
//	public void startPoisonThread(){
//		poisonThread= new PoisonThread(game);
//		poisonThread.envenom();
//	}
//	
////	public void envenom() {
////		if(poisonThread==null){
////			poisonThread = new PoisonThread(game);
////			poisonThread.setIsPoisonThreadRunning(true);
////			poisonThread.start();
//////			setPoisonThreadRunning(false);	
////		}
////		if (!poisonThreadRunning) {
////			poisonThread = new PoisonThread(game);
////			poisonThread.setIsPoisonThreadRunning(true);
////			poisonThread.start();
////		}
////	}
//	
//	public void setPoisonThreadRunning(boolean isPoisonThreadRunning){
//		poisonThreadRunning=isPoisonThreadRunning;
//	}
//	
//	
//	
//}
