package game.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTestFarm {
	private PlayerFarm P1;
	private PlayerFarm P2;
	private PlayerFarm P3;
	private PlayerFarm P4;
	
	@Before
	public void before(){
		this.P1 = new PlayerFarm("Fayssal");
	    this.P2 = new PlayerFarm("Aya");
	    this.P3 = new PlayerFarm("Mehdi");
	    this.P4 = new PlayerFarm("Zicko");

	  }
	
	  @Test
	  public void testGetName(){
		  assertEquals("Fayssal",this.P1.getName());
		  assertEquals("Aya",this.P2.getName());
		  assertEquals("Mehdi",this.P3.getName());
		  assertEquals("Zicko",this.P4.getName());

	  }

	

	
	
}
