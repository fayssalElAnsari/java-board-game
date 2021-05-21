package game.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PositionTest {
	
	Position pos1;
	Position pos1_duplicate;
	
	@Before
	public void init() {
		this.pos1 = new Position(1,1);
		this.pos1_duplicate = new Position(1,1);
	}

	@Test
	public void testPosition() {
		assertTrue(this.pos1 != null);
	}

	@Test
	public void testGetX() {
		assertEquals(this.pos1.getX(), 1);
	}

	@Test
	public void testGetY() {
		assertEquals(this.pos1.getY(), 1);
	}

	@Test
	public void testEqualsPosition() {
		assertTrue(this.pos1.equals(this.pos1_duplicate));
	}

}
