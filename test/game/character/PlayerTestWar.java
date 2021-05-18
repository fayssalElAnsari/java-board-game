package game.character;
import game.util.Tile;
import game.util.tile.DesertsTile;
import game.util.tile.TileProd;
import game.util.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTestWar {
	
	PlayerWar P1 = new PlayerWar("Fayssal");
	PlayerWar P2 = new PlayerWar("Aya");
	PlayerWar P3 = new PlayerWar("Mehdi");
	PlayerWar P4 = new PlayerWar("Ziko");
	
	Tile desertTile = new DesertsTile(new Position(0,0));
	

	  @Test
	  public void initAndGettersTest(){
	    assertSame(P1.getName(),"Fayssal");
	    assertEquals(P1.getSoldiers(),35);
	  }
	  
	  @Test
	  public void testCreateArmy(){
		  assertEquals(true, P1.createArmy(10, desertTile));
		  assertEquals(false, P1.createArmy(3, desertTile));
		  
	  }
	  @Test
	  public void testSellResources() {
		  P1.setResource(TileProd.CORN, 5);
		  assertEquals(5,P1.getResource(TileProd.CORN));
	  }
	  
	  
	  /*@Test
	  public void testCalculateTotalPoints() {
		  assertEquals();
	  }*/

	  

}
