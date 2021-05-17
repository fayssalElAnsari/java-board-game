/**
 * 
 */
package game.character.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import src.game.character.unit.Player;

/**
 * @author aya ziko
 *
 */
public class UnitTest {
	
	private Player P1;
	private Player P2;
	private Map testMap;
	private Unit unite1;
	private Unit unite2;
	private Unit unite3;
	
	@Before
	public void setUp() {
		this.P1 = new Player("Ziko");
		this.P2 = new Player("Aya");
		this.testMap = new Map("Maptest", 10, 10, 1);
		this.unite1 = new Unit(P1, tiles[0][0]);
		this.unite2 = new Unit(P2, tiles[5][7]);
	}

	@Test
	public void eatWW() {
		assertEquals(true, unite1.eat())
		assertEquals(false, unite3.eat());
	}
	
	@Test
	public void getAttackPointWW() {
		assertEquals(1, unite1.getAttackPoints());
	}
	
	@Test
	public void setAttackPointWW() {
		assertEquals(1, unite1.getAttackPoints());
		unite1.setAttackPoints(5);
		assertEquals(5, unite1.getAttackPoints());
	}
	
	@Test
	public void getOwnerWW() {
		assertEquals(P1, unite1.getOwner());
		assertEquals(P2, unite2.getOwner());
	}
	
	@Test
	public void setOwnerWW() {
		assertEquals(P1, unite1.getOwner());
		unite1.setOwner(P2);
		assertEquals(P2, unite1.getOwner());
	}
	
	@Test
	public void getSizeWW() {
		assertEquals(1, unite1.getSize());
	}
	
	@Test
	public void setSizeWW() {
		assertEquals(1, unite1.getSize());
		unite1.setSize(5);
		assertEquals(5, unite1.getSize());
	}
	
	@Test
	public void putOnTileWW() {
		assertEquals(P2, tiles[5][7]);
		assertEquals(true, unite2.putOnTile(tiles[4][2]));
		assertEquals(false, unite2.putOnTile(tiles[0][0]));
		assertEquals(false, unite2.putOnTile(tiles[4][2]));
		
	}
	
	/*
	@Test
	public void eatOrDieWW() {
		compliqué a testé :/ car aucun getter ou setter pour vérifier le bon fonctionnement
		seul moyen est de voir si ça marche en jeu 
	}*/
	
	
	/*
	@Test
	public void harvestWW() {
		pour le coup même problème que au dessus, des messages sont affiché selon si une Tile peut etre
		harvest ou non mais c'est sur la sortie System et je ne sais pas comment récuperer le message
		et savoir si il s'affiche comme il faut ou non
		
	}*/
	
	@Test
	public void getTurnsalaryWW() {
		assertEquals(true, unite2.getTurnSalary());
	}
	
	@Test
	public void getResourceWW() {
		assertEquals(0, unite1.getResources());
	}

	@Test
	public void getTileWW() {
		assertEquals(tiles[0][0], unite1.getTile());
		assertEquals(tiles[5][7], unite2.getTile());
	}
	
	@Test
	public void getPayedWW() {
		assertEquals(1, unite1.getPayed());
	}
	
	@Test
	public void getPayedV2WW() {
		assertEquals(5, unite1.getPayed(5));
	}
	
	/*@Test
	public void sendRessourceWW() {
		même problème que precedement
	}
	
	@Test
	public void incrementSizeWW() {
		idem
	}*/
	
	
	// ---Pour permettre l'exécution des test----------------------
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(UnitTest.class);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
