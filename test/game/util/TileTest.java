package game.util;

import static org.junit.Assert.*;

import org.junit.Before;
import game.util.*
import org.junit.Test;

import game.character.*;
import game.character.unit.Unit;
import game.util.tile.*;

public class TileTest {
	
	private Position positionTest;
	private TileProd tileProdTest;
	private Map testMap;
	private Player P1;
	private Player P2;
	private Unit unite1;
	private Unit unite2;
	private Unit unite3;
	
	@Before
	public void setUp() {
		this.P1 = new Player("Ziko");
		this.P2 = new Player("Aya");
		this.testMap = new Map("Maptest", 10, 10, 1);
		Tile tilesTest1 = tiles[0][0];
		Tile tilesTest2 = tiles[5][7];
		Tile tilesTest3 = tiles[4][7];
		Tile tilesTest4 = tiles[5][8];
		Tile tilesTest5 = tiles[3][4];
		Position PosTest = new Position(2, 3);
		Tile tileTest0 = new Tile(PosTest, TileProd.SAND);
		this.unite1 = new Army(P1, tilesTest1, 5);
		this.unite2 = new Army(P2, tilesTest2, 5);
		this.unite3 = new Unit(P1, tiles[1][9]);
	}

	@Test
	public void getTurnSalaryWW() {
		assertEquals(0, tileTest0.getTurnSalary());
	}

	@Test
	public void setTurnSalaryWW() {
		assertEquals(0, tileTest0.getTurnSalary());
		tileTest0.setTurnSalary(5);
		assertEquals(5, tileTest0.getTurnSalary());
	}

	@Test
	public void getBonusFoodConsumptionWW() {
		assertEquals(1, tileTest0.getBonusFoodConsumption());
	}

	@Test
	public void getBonusAttackPointsWW() {
		assertEquals(0, tileTest0.getBonusAttackPoints);
	}

	@Test
	public void isOceanWW() {
		assertEquals(false, tileTest0.isOcean());
	}

	@Test
	public void getTypeSymbolWW() {
		assertEquals('T', tileTest0.getTypeSymbo());
	}

	@Test
	public void getMaxNbSoldiersWW() {
		assertEquals(5, tileTest0.getMaxNbSoldiers());
	}
	
	@Test
	public void setMaxNbSoldiersWW() {
		assertEquals(5, tileTest0.getMaxNbSoldiers());
		tileTest0.setMaxNbSoldiers(10);
		assertEquals(10, tileTest0.getMaxNbSoldiers());
	}

	@Test
	public void getOwnerSymbolWW() {
		assertEquals('Z', tilesTest1.getOwnerSymbol());
	}

	@Test
	public void getPositionWW() {
		assertEquals(PosTest, tileTest0.getPosition());
	}

	@Test
	public void getOwnerWW() {
		assertEquals(P2, tilesTest2.getOwner());
	}

	@Test
	public void setOwnerWW() {
		assertEquals(null, tilesTest3.getOwner());
		tilesTest3.setOwner(P1);
		assertEquals(P1, tilesTest3.getOwner());
	}

	@Test
	public void loseResourcesWW() {
		assertEquals(true, tilesTest4.loseResources(500));
		assertEquals(false, tilesTest5.loseResources(6500));
	}

	@Test
	public void getTileProdWW() {
		assertEquals(TileProd.SAND, tileTest0.getTileProd());
	}

	@Test
	public void checkIfEmptyWW() {
		assertEquals(true, tilesTest4.checkIfEmpty());
		assertEquals(false, tilesTest2.checkIfEmpty());
	}
	
	
	@Test
	public void setUnitWW() {
		assertEquals(null, tilesTest3.getUnit());
		tilesTest3.setUnit(unite3);
		assertEquals(unite3, tilesTest3.getUnit());
	}
	
	@Test
	public void getBonusPointsWW() {
		assertEquals(0, tileTest0.getBonusPoints());
	}

	@Test
	public void setBonusPointsWW() {
		assertEquals(0, tileTest0.getBonusPoints());
		tileTest0.setBonusPoints(5);
		assertEquals(5, tileTest0.getBonusPoints());
	}

}
