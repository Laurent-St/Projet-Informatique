package levels;

import java.awt.Rectangle;

import gui.Floor;
import gui.TileLibrary;
import gui.Wall;

public class Level1 extends LevelData {
	
	public Level1() {
		this.fill(new Rectangle(0,0,this.getLevelWidth(),this.getLevelHeight()), new Floor());
		this.makeBorder();
		this.setTileAt(2, 2, new Wall(TileLibrary.WALL_PLOT));
	}

}
