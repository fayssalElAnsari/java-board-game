package game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import game.player.FarmPlayer;
import game.player.Player;
import game.player.WarPlayer;

public class WarGameTest {
	
	Player fayssal;
	Player aya;
	List<Player> players;
	int x;
	int y;
	Game game;
	
	@Before
	public void init() {
		fayssal = new WarPlayer("fayssal");
		fayssal = new WarPlayer("aya");
		players.add(this.fayssal);
		players.add(this.aya);
		this.x = 10;
		this.y = 10;
		game = new FarmGame(this.x, this.y, this.players);
	}

	@Test
	public void testResultat() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEndGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testWarGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testAffectArmee() {
		fail("Not yet implemented");
	}

	@Test
	public void testAttaqueVoisins() {
		fail("Not yet implemented");
	}

	@Test
	public void testAttaque() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeploiementAdjacent() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeploiement() {
		fail("Not yet implemented");
	}

	@Test
	public void testAskNbUnitToDeploy() {
		fail("Not yet implemented");
	}

	@Test
	public void testGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisplayGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewOwner() {
		fail("Not yet implemented");
	}

}
