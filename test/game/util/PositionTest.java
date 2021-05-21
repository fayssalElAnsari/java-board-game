/**
 * 
 */
package game.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author aya
 *
 */
public class PositionTest {

	@Before
	public void setUp() {
		Position PosTest = new Position(4, 7);
		Position PosTest2 = new Position(2, 5);
	}
	
	public void toStringWW() {
		assertEquals("(4,7)", PosTest.toString());
	}

	public void getXCoordinateWW() {
		assertEquals(4, PosTest.getXCoordinate());
	}

	public int getYCoordinateWW() {
		assertEquals(7, PosTest.getYCoordinate());
	}

	public void setPositionWW() {
		assertEquals(4, PosTest.getXCoordinate());
		assertEquals(7, PosTest.getYCoordinate());
		PosTest.setPosition(6, 8);
		assertEquals(6, PosTest.getXCoordinate());
		assertEquals(8, PosTest.getYCoordinate());
	}

	public void setXCoordinateWW() {
		assertEquals(4, PosTest.getXCoordinate());
		PosTest.setXCoordinate(5);
		assertEquals(5, PosTest.getXCoordinate());
	}

	public void setYCoordianteWW() {
		assertEquals(7, PosTest.getYCoordinate());
		PosTest.setYCoordinate(3);
		assertEquals(3, PosTest.getYCoordinate());
	}
	
	public void calculateDistanceWW() {
		assertEquals(Math.sqrt(8), PosTest.calculateDistance(PosTest2));
	}

}
