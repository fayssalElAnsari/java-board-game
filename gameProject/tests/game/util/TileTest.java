package game.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.player.FarmPlayer;
import game.player.Player;
import game.util.Position;
import game.util.Tile;
import game.util.Tiletype;

public class TileTest {
	
	Tile tile1;
	Tile tile2;
	Player player1;
	Player player2;
	
	@Before
	public void init() {
		tile1 = new Tile(Tiletype.DESERT, 1, 1);
		tile2 = new Tile(Tiletype.MONTAIN, 1, 2);
		player1 = new FarmPlayer("fayssal");
		player2 = new FarmPlayer("aya");
	}

	@Test
	public void testTile() {
		assertTrue(this.tile1 != null);
		assertTrue(this.tile2 != null);
	}

	@Test
	public void testGetPos() {
		assertTrue(this.tile1.getPos().equals(new Position(1,1)));
	}

//	@Test
//	public void testTileTiletypeIntInt() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testToString() {
		// without owner
		assertTrue(this.tile1.toString().equals(this.tile1.getSymbole()+"   "));
		assertTrue(this.tile2.toString().equals(this.tile2.getSymbole()+"   "));
		// with owner
		this.tile1.setOwner(player1);
		assertTrue(this.tile1.toString().equals(this.tile1.getSymbole()+":"+this.player1.getName().charAt(0)+" "));
		this.tile2.setOwner(player2);
		assertTrue(this.tile2.toString().equals(this.tile2.getSymbole()+":"+this.player2.getName().charAt(0)+" "));
	}

	@Test
	public void testGetUnit() {
		assertEquals(this.tile1.getUnit(), 0);
	}

	@Test
	public void testSetUnit() {
		this.tile1.setUnit(5);
		assertEquals(this.tile1.getUnit(), 5);
	}

	@Test
	public void testGetType() {
		assertEquals(Tiletype.DESERT, this.tile1.getType());
		assertEquals(Tiletype.MONTAIN, this.tile2.getType());
	}

	@Test
	public void testGetOwner() {
		assertEquals(this.tile1.getOwner(), null);
	}

	@Test
	public void testSetOwner() {
		this.tile1.setOwner(this.player1);
		assertEquals(this.tile1.getOwner(), this.player1);
	}

//	@Test
//	public void testGetListUnit() {
//		this.tile1.getListUnit();
//	}

	@Test
	public void testGetSymbole() {
		assertTrue(this.tile1.getSymbole().charAt(0) == (this.tile1.getType().name().charAt(0)));
	}

}
