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


	private PlayerWar P1;
	private PlayerWar P2;
	private Map testMap;
	private Army unite1;
	private Army unite2;
	private Army unite3;
	
	@Before
	public void setUp() {
		this.P1 = new PlayerWar("Ziko");
		this.P2 = new PlayerWar("Aya");
		this.testMap = new Map("Maptest", 10, 10, 1);
		Tile tilesTest1 = tiles[0][0];
		Tile tilesTest2 = tiles[5][7];
		Tile tilesTest3 = tiles[4][7];
		Tile tilesTest4 = tiles[5][8];
		Tile tilesTest5 = tiles[3][4];
		this.unite1 = new Army(P1, tilesTest1, 5);
		this.unite2 = new Army(P2, tilesTest2, 5);
	}

	/*@Test
	public void putOnTileWorkWell() {
		déjà realiser sur UnitTest.java 
	}*/
	
	/*@Test
	public void attackTileWorkWell() {
		testé automatiquement par conquerEnemiesWorkWell etant donné que la méthode conquerEnemies() 
		utilise la méthode attackTile()
	}*/
	
	@Test
	public void setSizeWorkWell() {
		assertEquals(5, unite1.getSize());
		unite1.setSize(10);
		assertEquals(10, unite1.getSize());
	}
	
	@Test
	public void conquerEnemiesWorkWell() {
		assertEquals(P2, tilesTest2.getOwner());
		unite2.conquerEnemies();
		assertEquals(P2, tilesTest3.getOwner());
		assertEquals(P2, tilesTest4.getOwner());
	}
	
	@Test
	public void getTileWW() {
		assertEquals(tilesTest2, unite2.getTile());
		unite2.putOnTile(tilesTest5);
		assertEquals(tilesTest5, unite2.getTile());
	}
	
	
	@Test
	public void toString() {
		String TestString = unite1.toString();
		assertEquals("Owner:Ziko; AP:1", TestString);
	}
	
	
}
