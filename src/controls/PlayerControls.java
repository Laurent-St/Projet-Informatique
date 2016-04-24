package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

import model.gameElements.Player;

public class PlayerControls implements KeyListener {
	
	private static int z = 90;
	private static int q = 81;
	private static int s = 83;
	private static int d = 68;
	private static int space = 32;
	private static int lshift = 16;
	private static int k1 = 49;
	private static int k2 = 50;
	private static int k3 = 51;
	private static int k4 = 52;
	private static int k5 = 53;
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private Player player;
	
	public PlayerControls(Player player) {
		this.player = player;
		up = false;
		down = false;
		left = false;
		right = false;
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==z) {
			up = true;
			player.setMoving("up");
		} else if (e.getKeyCode()==q) {
			left = true;
			player.setMoving("left");
		} else if (e.getKeyCode()==s) {
			down = true;
			player.setMoving("down");
		} else if (e.getKeyCode()==d) {
			right = true;
			player.setMoving("right");
		} else if (e.getKeyCode()==space) {
			player.attackHand();
		} else if (e.getKeyCode()== lshift) {
			player.attackSpecial();
		} else if(e.getKeyCode()==k1) {
			player.getInventory().select(1);
		} else if(e.getKeyCode()==k2) {
			player.getInventory().select(2);
		} else if(e.getKeyCode()==k3) {
			player.getInventory().select(3);
		} else if(e.getKeyCode()==k4) {
			player.getInventory().select(4);
		} else if(e.getKeyCode()==k5) {
			player.getInventory().select(5);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==z) {
			up = false;
		} else if (e.getKeyCode()==q) {
			left = false;
		} else if (e.getKeyCode()==s) {
			down = false;
		} else if (e.getKeyCode()==d) {
			right = false;
		}
		
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
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
