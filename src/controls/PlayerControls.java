/*
 * Classe gérant les contrôles du joueur.
 */

package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.gameElements.Player;

public class PlayerControls implements KeyListener {
	
	private static int z = KeyEvent.VK_Z;
	private static int q = KeyEvent.VK_Q;
	private static int s = KeyEvent.VK_S;
	private static int d = KeyEvent.VK_D;
	private static int space = KeyEvent.VK_SPACE;
	private static int lshift = KeyEvent.VK_SHIFT;
	private static int k1 = KeyEvent.VK_1;
	private static int k2 = KeyEvent.VK_2;
	private static int k3 = KeyEvent.VK_3;
	private static int k4 = KeyEvent.VK_4;
	private static int k5 = KeyEvent.VK_5;
	private static int f = KeyEvent.VK_F;
	private static int ctrl= KeyEvent.VK_CONTROL;
	private static int dup = KeyEvent.VK_UP;
	private static int ddown = KeyEvent.VK_DOWN;
	private static int dleft = KeyEvent.VK_LEFT;
	private static int dright = KeyEvent.VK_RIGHT;
	//private static int e =KeyEvent.VK_E; 
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private boolean readyToRemove;
	
	private Player player;
	
	public PlayerControls(Player player) {
		this.player = player;
		up = false;
		down = false;
		left = false;
		right = false;
		readyToRemove=false;
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//Les 4 premiers tests mettent le joueur dans un état de déplacement. Si le joueur est dans l'état up,
		//il est orienté vers le haut et se déplacera vers le haut. Meme principe pour les 3 autres orientations.
		if (e.getKeyCode()==z || e.getKeyCode()==dup) {
			up = true;
			player.setMoving("up");
		} else if (e.getKeyCode()==q || e.getKeyCode()==dleft) {
			left = true;
			player.setMoving("left");
		} else if (e.getKeyCode()==s || e.getKeyCode()==ddown) {
			down = true;
			player.setMoving("down");
		} else if (e.getKeyCode()==d || e.getKeyCode()==dright) {
			right = true;
			player.setMoving("right");
		} else if (e.getKeyCode()==space) {
			player.attackHand();
		} else if (e.getKeyCode()== lshift) {
			player.attackSpecial();
		} else if(e.getKeyCode()==k1) {
			if (readyToRemove){
				player.getInventory().dropObject(1);
			} else{
				player.getInventory().select(1);
			}
		} else if(e.getKeyCode()==k2) {
			if (readyToRemove){
				player.getInventory().dropObject(2);
			} else{
				player.getInventory().select(2);
			}
		} else if(e.getKeyCode()==k3) {
			if (readyToRemove){
				player.getInventory().dropObject(3);
			} else{
				player.getInventory().select(3);
			}
		} else if(e.getKeyCode()==k4) {
			if (readyToRemove){
				player.getInventory().dropObject(4);
			} else{
				player.getInventory().select(4);
			}
		} else if(e.getKeyCode()==k5) {
			if (readyToRemove){
				player.getInventory().dropObject(5);
			} else{
				player.getInventory().select(5);
			}
		} else if(e.getKeyCode()==f){
			player.tryInteraction();
		} else if (e.getKeyCode()==ctrl){
			readyToRemove=true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==z || e.getKeyCode()==dup) {
			up = false;
		} else if (e.getKeyCode()==q || e.getKeyCode()==dleft) {
			left = false;
		} else if (e.getKeyCode()==s || e.getKeyCode()==ddown) {
			down = false;
		} else if (e.getKeyCode()==d || e.getKeyCode()==dright) {
			right = false;
		}
		
		//Vérifie l'état de déplacement lorsque la touche est relachée.
		if (up) {
			player.setMoving("up");
		} else if (down) {
			player.setMoving("down");
		} else if (right) {
			player.setMoving("right");
		} else if (left) {
			player.setMoving("left");
		} else {
			player.setMoving("null");
		}
		
		if (e.getKeyCode()==ctrl){
			readyToRemove=false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
