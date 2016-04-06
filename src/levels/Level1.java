package levels;

import java.awt.Rectangle;

import gui.Tile;

public class Level1 extends LevelData {
	
	public Level1() {
		this.fill(new Rectangle(0,0,50,40), Tile.FLOOR);
		this.makeBorder();
	}

}
