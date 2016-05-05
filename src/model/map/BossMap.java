/*
 * Map instanciable générant un terrain simple ainsi qu'un Boss
 * (En particulier map 5 et 10)
 */

package model.map;

import java.awt.Rectangle;

import model.Game;
import model.gameElements.Boss;
import model.graphicElements.Floor;

public class BossMap extends Map {

	private static final long serialVersionUID = 1L;

	public BossMap(int levelNum, Game game) {
		super(levelNum, game);
		
		//Génération du sol
		this.fill(new Rectangle(0,0,getLevelWidth(),getLevelHeight()), new Floor(game));
		
		//Génération des bordures et des portes
		makeBorder(new Rectangle(0,0,getLevelWidth()-1,getLevelHeight()-1));
		initDoors();
		
		//Génération de pilliers
		makeBorder(new Rectangle(5,5,2,2));
		makeBorder(new Rectangle(5,getLevelHeight()-8,2,2));
		makeBorder(new Rectangle(getLevelWidth()-8,5,2,2));
		makeBorder(new Rectangle(getLevelWidth()-8,getLevelHeight()-8,2,2));
	}
	
	public void initActorsAndObjects() {
		
		//Génération du Boss (difficulté en fonction du niveau)
		
		Boss boss=new Boss(416.0, 316.0, getLevelNum()*5, getLevelNum()*200, getLevelNum()/5, 500, getGame());
		addMonster(boss);
	}

}
