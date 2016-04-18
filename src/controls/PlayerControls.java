package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gameElements.Player;

public class PlayerControls implements KeyListener {
	
	private static int z = 90;
	private static int q = 81;
	private static int s = 83;
	private static int d = 68;
	private static int space = 32;
	
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
			player.getWeapon().attack();
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
