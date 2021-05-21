package game.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.player.WarPlayer;
import game.character.Unit;
import game.character.WarUnit;
import game.player.Player;
import game.util.Tile;
import game.util.Tiletype;

public class WarPlayerTest {
	
	Player player1;
	Player player2;
	Tile tileDesert;
	Tile tileMountain;
	Tile tilePlain;
	Tile tileForest;
	Unit warUnit1;
	
	/**
	 * Initialize the two FarmPlayer objects with their names
	 */
	@Before
	public void init() {
		this.player1 = new WarPlayer("fayssal");
		this.player2 = new WarPlayer("aya");
		this.tileDesert = new Tile(Tiletype.DESERT, 1, 1);
		this.tileMountain = new Tile(Tiletype.MONTAIN, 1, 2);
		this.tilePlain = new Tile(Tiletype.PLAIN, 2, 1);
		this.tileForest = new Tile(Tiletype.WOOD, 2, 2);
		warUnit1 = new WarUnit();
	}

	@Test
	public void testWarPlayer() {
		assertTrue(this.player1.getName().equals("fayssal"));
	}

	@Test
	public void testGetNbWarAvailable() {
		assertEquals(((WarPlayer) this.player1).getNbWarAvailable(), 35);
	}

	@Test
	public void testDeployedWar() {
		assertEquals(((WarPlayer) this.player1).getNbWarAvailable(), 35);
		((WarPlayer) this.player1).deployedWar(5);
		assertEquals(((WarPlayer) this.player1).getNbWarAvailable(), 30);
	}

	@Test
	public void testGetNbFood() {
		assertEquals(((WarPlayer) this.player1).getNbFood(), 10);
	}

	@Test
	public void testSetNbFood() {
		assertEquals(((WarPlayer) this.player1).getNbFood(), 10);
		((WarPlayer) this.player1).setNbFood(9000);
		assertEquals(((WarPlayer) this.player1).getNbFood(), 9000);
		
	}

	@Test
	public void testSetNbWarAvailable() {
		assertEquals(((WarPlayer) this.player1).getNbWarAvailable(), 35);
		((WarPlayer) this.player1).setNbWarAvailable(9000);
		assertEquals(((WarPlayer) this.player1).getNbWarAvailable(), 9000);
	}

	@Test
	public void testRecolteFood() {
		assertEquals(((WarPlayer) this.player1).getNbFood(), 10);
		((WarPlayer) this.player1).recolteFood();
		assertEquals(((WarPlayer) this.player1).getNbFood(), 10);
		this.player1.addOwnedTile(this.tilePlain);
		((WarPlayer) this.player1).recolteFood();
		assertEquals(((WarPlayer) this.player1).getNbFood(), 15);
		this.player1.addOwnedTile(this.tileForest);
		((WarPlayer) this.player1).recolteFood();
		assertEquals(((WarPlayer) this.player1).getNbFood(), 21);
		
	}

	@Test
	public void testDistribFood() {
		assertEquals(((WarPlayer) this.player1).getNbFood(), 10);
		this.player1.addOwnedTile(this.tileDesert);
		this.tileDesert.setUnit(3);
		((WarPlayer) this.player1).distribFood();
		assertEquals(((WarPlayer) this.player1).getNbFood(), 4);
		((WarPlayer) this.player1).distribFood();
		assertEquals(((WarPlayer) this.player1).getNbFood(), 0);
	}

	/////// ABSTRACT ////////

	@Test
	public void testAddGolds() {
		assertEquals(this.player1.getGolds(), 0);
		this.player1.addGolds(9000);
		assertEquals(this.player1.getGolds(), 9000);
	}

	@Test
	public void testAddOwnedTile() {
		this.player1.addOwnedTile(this.tileDesert);
		assertTrue(this.player1.getTileOwned().contains(tileDesert));
		assertFalse(this.player1.getTileOwned().contains(tileForest));
	}

	@Test
	public void testGetGolds() {
		assertEquals(this.player1.getGolds(), 0);
	}

	@Test
	public void testSetGolds() {
		this.player1.setGolds(9000);
		assertEquals(this.player1.getGolds(), 9000);
	}

	@Test
	public void testGetName() {
		assertTrue(this.player1.getName().equals("fayssal"));
	}

	/**
	 * the players starts off with an empty tiles list
	 */
	@Test
	public void testGetTileOwned() {
		assertTrue(this.player1.getTileOwned().isEmpty());
	}

	/**
	 * add tile to players list of tiles and then remove it
	 */
	@Test
	public void testLostTile() {
		this.player1.addOwnedTile(this.tileDesert);
		assertTrue(this.player1.getTileOwned().contains(tileDesert));
		this.player1.lostTile(this.tileDesert);
		assertFalse(this.player1.getTileOwned().contains(tileDesert));
	}
}
