package gameElements;

import java.awt.Image;
import java.awt.Rectangle;
import gui.level.LevelPanel;

import java.util.Random;

import animation.Count;

public class Monster extends Actor implements Runnable {
	private Image monsterImage;
	private int count;

	public Monster(String name,double x, double y, int damage, int health, double speed, LevelPanel level){
		super(name, x, y, damage, health, speed, level, new Rectangle(5,0,18,31));
		Thread monsterThread=new Thread(this);
		monsterThread.start();
		count = 0;
		
	}


@Override
public void run(){
	try{
		while(true){
			this.repaint();
			
			if(count==0) {
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
			}
			count+=1;
			if(count==80) {
				count = 0;
			}
			move();
			Thread.sleep(15);
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


