package game.character.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.character.PlayerWar;
import game.util.*;
import game.util.tile.*;
import game.*;

public class ArmyTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		/*Map testMap = new Map("Maptest", 10, 10, 1);
		PlayerWar P1 = new PlayerWar("Ziko");
		Position test = new Position(1, 2);
		DesertsTile testTile = new DesertsTile(test);
		Army ZikosArmy = new Army(P1,testTile,25);*/
		Game testGame = new Game();
	}

	@Test
	public void putOnTileWorkWell() {
		Position pos1 = new Position (1, 3);
		Tile test1 = new Tile();
	}
	
	@Test
	public void attackTileWorkWell() {
		
	}
	
	@Test
	public void setSizeWorkWell() {
		
	}
	
	@Test
	public void conquerEnemiesWorkWell() {
		
	}
	
}
