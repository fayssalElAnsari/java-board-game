package game.character;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author aya
 *
 */
public class PlayerTest {
	  private Player P1;
	  private Player P2;
	  private Player P3;
	  private Player P4;

	  @Before
	  public void before(){
	    this.P1 = new Player("Fayssal");
	    this.P2 = new Player("Aya");
	    this.P3 = new Player("Mehdi");
	    this.P4 = new Player("Ziko");

	  }
	  @Test
	  public void testGetName(){
		  assertEquals("Fayssal",this.P1.getName());
		  assertEquals("Aya",this.P2.getName());
		  assertEquals("Mehdi",this.P3.getName());
		  assertEquals("Ziko",this.P4.getName());

	  }
	  @Test
	  public void testgetGold(){
	    assertEquals(15,this.P1.getGold());
	    assertEquals(15,this.P2.getGold());
	    assertEquals(15,this.P3.getGold());
	    assertEquals(15,this.P4.getGold());

	  }


}
