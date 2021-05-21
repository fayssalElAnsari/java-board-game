package game.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FarmUnitTest {
	
	Unit farmUnit;
	
	@Before
	public void init() {
		farmUnit = new FarmUnit();
	}

	@Test
	public void testFarmUnit() {
		assertTrue(this.farmUnit != null);
	}

	@Test
	public void testGetGolds() {
		assertTrue(this.farmUnit.getGolds() == 0);
	}

	@Test
	public void testSetGolds() {
		this.farmUnit.setGolds(9000);
		assertTrue(this.farmUnit.getGolds() == 9000);
	}

}
