package game.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.util.Tile;
import game.util.Tiletype;
import game.player.Player;
import game.player.FarmPlayer;
import game.player.WarPlayer;

public class FarmPlayerTest {
	
	Player player1;
	Player player2;
	Tile tileDesert;
	Tile tileMountain;
	Tile tilePlain;
	Tile tileForest;
	
	/**
	 * Initialize the two FarmPlayer objects with their names
	 */
	@Before
	public void init() {
		this.player1 = new FarmPlayer("fayssal");
		this.player2 = new FarmPlayer("aya");
		this.tileDesert = new Tile(Tiletype.DESERT, 1, 1);
		this.tileMountain = new Tile(Tiletype.MONTAIN, 1, 2);
		this.tilePlain = new Tile(Tiletype.PLAIN, 2, 1);
		this.tileForest = new Tile(Tiletype.WOOD, 2, 2);
	}
	

	/**
	 * check that the public constructor creates the player objects with the right 
	 * initial values of variables: the name
	 */
	@Test
	public void testInitFarmPlayer() {
		assertTrue(this.player1.getName().equals("fayssal"));
	}

	/**
	 * check that all the resources start off with a 0 value
	 */
	@Test
	public void testGetRessources() {
		for (int resourceQuantity : ((FarmPlayer) this.player1).getRessources().values()) {
		    assertEquals(resourceQuantity, 0);
		}
	}

	/**
	 * the player in the beginning has no tiles
	 * the gold of the player should stay 15
	 */
	@Test
	public void testPayDay() {
		assertEquals(player1.getGolds(), 15);
		((FarmPlayer) this.player1).payDay();
		assertEquals(player1.getGolds(), 15);
	}

	/**
	 * since the player has no resources in the beginning the gold
	 * after trading resources should stay 15
	 */
	@Test
	public void testTradeRessources() {
		assertEquals(this.player1.getGolds(), 15);
		((FarmPlayer) this.player1).tradeRessources();
		assertEquals(this.player1.getGolds(), 15);
	}

	/**
	 * since the player has no tiles the resources should start off with 0 
	 * the user will take in a forest tile and so after recolting resources 
	 * he will have +1 resources of type wood
	 */
	@Test
	public void testRecolteRessources() {
		assertEquals(((FarmPlayer) this.player1).getRessources().get(Tiletype.WOOD.name()).intValue(), 0);
		this.player1.addOwnedTile(this.tileForest);
		((FarmPlayer) this.player1).recolteRessources();
		assertEquals(((FarmPlayer) this.player1).getRessources().get(Tiletype.WOOD.name()).intValue(), 1);
		
	}

	/**
	 * 
	 */
	@Test
	public void testGetGoldDistrib() {
		assertEquals(((FarmPlayer) this.player1).getGoldDistrib(),0);
	}

	@Test
	public void testAddGolds() {
		assertEquals(this.player1.getGolds(), 15);
		this.player1.addGolds(5);
		assertEquals(this.player1.getGolds(), 20);
	}

	@Test
	public void testAddOwnedTile() {
		this.player1.addOwnedTile(this.tileDesert);
		assertTrue(this.player1.getTileOwned().contains(tileDesert));
		assertFalse(this.player1.getTileOwned().contains(tileForest));
	}

	@Test
	public void testGetGolds() {
		assertEquals(this.player1.getGolds(), 15);
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
