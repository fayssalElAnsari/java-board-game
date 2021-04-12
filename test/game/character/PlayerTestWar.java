/**
 * 
 */
package game.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class PlayerTestWar {
	private PlayerWar P1;
	private PlayerWar P2;
	private PlayerWar P3;
	private PlayerWar P4;
	
	@Before
	public void before(){
		this.P1 = new PlayerWar("Fayssal");
	    this.P2 = new PlayerWar("Aya");
	    this.P3 = new PlayerWar("Mehdi");
	    this.P4 = new PlayerWar("Zicko");

	  }
	
	  @Test
	  public void testGetName(){
		  assertEquals("Fayssal",this.P1.getName());
		  assertEquals("Aya",this.P2.getName());
		  assertEquals("Mehdi",this.P3.getName());
		  assertEquals("Zicko",this.P4.getName());

	  }
	  
	  
	  @Test
	  public void testgetSoldiers() {
		  assertEquals("35",this.P1.getSoldiers());
		  assertEquals("35",this.P2.getSoldiers());
		  assertEquals("35",this.P3.getSoldiers());
		  assertEquals("35",this.P4.getSoldiers());
	  }
	  
}
