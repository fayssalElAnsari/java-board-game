package game.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WarUnitTest {
	
	Unit warUnit;
	
	@Before
	public void init() {
		warUnit = new WarUnit();
	}

	@Test
	public void testWarUnit() {
		assertTrue(this.warUnit != null);
	}

	@Test
	public void testGetGolds() {
		assertTrue(this.warUnit.getGolds() == 0);
	}

	@Test
	public void testSetGolds() {
		this.warUnit.setGolds(9000);
		assertTrue(this.warUnit.getGolds() == 9000);
	}

}
