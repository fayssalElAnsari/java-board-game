package game.character;

import java.util.HashMap;
import java.util.Map.Entry;

import game.character.unit.Army;
import game.character.unit.Unit;
import game.util.ActionPlayer;
import game.util.tile.TileProd;

public class PlayerWar extends Player{
	private Unit[] units;
	private HashMap<TileProd, Integer> inventory = new HashMap<TileProd, Integer>();

	/**
	 * public constructor for the Player class each player starts off the game with
	 * only 15 gold pieces the last action performed by this player is nothing (or
	 * skip) each player will have 6 empty worker slots that will be filled later in
	 * the game each player will have an inventory having 0 of each resource kind
	 * @param name the name to be given to this player
	 */
	public PlayerWar(String name) {
        // call the constructor of Player
        super(name);
        // create the units as some armies
		units = new Army[6];
		for (TileProd resource : TileProd.values()) {
			inventory.put(resource, 0);
		}
	}

	/**
	 * getter for soldiers left for this player
	 * @return int the number of soldiers this player has left
	 */
	public int getSoldiers(){
		return soldiers;
	}

}