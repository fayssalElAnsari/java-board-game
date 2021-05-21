package game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import game.player.FarmPlayer;
import game.player.Player;

/**
 * in this class we will try to simulate user input and have 
 * a predefined game where we know the outcome before hand 
 * @author elansari
 *
 */
public class FarmGameTest {
	
	
	Player fayssal;
	Player aya;
	List<Player> players;
	int x;
	int y;
	Game game;
	
	@Before
	public void init() {
		fayssal = new FarmPlayer("fayssal");
		fayssal = new FarmPlayer("aya");
		players.add(this.fayssal);
		players.add(this.aya);
		this.x = 10;
		this.y = 10;
		game = new FarmGame(this.x, this.y, this.players);
	}

//	@Test
//	public void testDisplayGame() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testResultat() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEndGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testFarmGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testAffectOuvrier() {
		fail("Not yet implemented");
	}

	@Test
	public void testGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewOwner() {
		fail("Not yet implemented");
	}

}
