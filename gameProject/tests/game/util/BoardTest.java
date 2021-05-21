package game.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	
	Board board1;
	
	@Before
	public void init() {
		board1 = new Board("testBoard", 5, 5);
	}

	@Test
	public void testBoard() {
		assertTrue(board1 != null);
	}

//	@Test
//	public void testDisplayBoard() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetTiles() {
		assertTrue(this.board1.getTiles() != null);
	}

	@Test
	public void testGetName() {
		assertTrue(this.board1.getName().equals("testBoard"));
	}

	@Test
	public void testGetWidth() {
		assertEquals(this.board1.getWidth(), 5);
	}

	@Test
	public void testGetHeight() {
		assertEquals(this.board1.getHeight(), 5);
	}

}
