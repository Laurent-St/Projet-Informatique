package gameElements;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import gui.level.LevelPanel;

import java.util.Random;

import javax.imageio.ImageIO;

public class Monster extends Actor implements Runnable {
	private Image monsterImage;

	public Monster(String name,double x, double y, int damage, int health, int speed, LevelPanel level){
		super(name, x, y, damage, health, speed, level, new Rectangle(0,0,32,32));
		Thread monsterThread=new Thread(this);
		monsterThread.start();
		
	}


@Override
public void run(){
	try{
		while(true){
			this.repaint();
			Random randomGenerator=new Random();
			int randomNum=randomGenerator.nextInt(4);
			if (randomNum==1){
				setMoving("up");
			}else if(randomNum==2){
				setMoving("down");
			}else if(randomNum==3){
				setMoving("right");
			}else{
				setMoving("left");
			}
			move();
			System.out.println("Le monstre bouge");
			Thread.sleep(1000);
		}
	}catch(Exception e){}
}

//public void setImage(){
//	try {
//		monsterImage = ImageIO.read(new File("src/gameElements/zombie.png"));
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}
//public paintComponent()

}


