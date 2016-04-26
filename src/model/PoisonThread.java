//package model;
//
//import model.gameElements.Player;
//
////import model.graphicElements.PoisonousTile;
//
//public class PoisonThread extends Thread {
//	private Game game;
//	private boolean isPoisonThreadRunning;
//	private Player player;
//	
//	public PoisonThread(Game game){
//		this.game=game;
//	}
//	@Override
//	public void run() {
//		player=game.getPlayer();
//		int i = 0;
//		while (i < 4) {
//			player.setHealth(player.getHealth() - 10);
//			i++;
//			try {
//				sleep(1000);
//
//			} catch (Exception e) {
//			}
//		}
//		//Thread.currentThread().interrupt();
////		setPoisonThreadRunning(false);	
//		isPoisonThreadRunning=false;
//	}
//	
//	public boolean getIsPoisonThreadRunning(){
//		return isPoisonThreadRunning;
//	}
//	public void setIsPoisonThreadRunning(boolean isPoisonThreadRunning){
//		this.isPoisonThreadRunning=isPoisonThreadRunning;
//	}
//	
//	public void envenom() {
//		if(this==null){
//			this.setIsPoisonThreadRunning(true);
//			this.start();
////			setPoisonThreadRunning(false);	
//		}
//		if (!isPoisonThreadRunning) {
//			this.setIsPoisonThreadRunning(true);
//			this.start();
//		}
//	}
//}
