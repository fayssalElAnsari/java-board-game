package game.character;

import java.util.Map.Entry;

import game.character.unit.Army;
import game.character.unit.Unit;
import game.util.ActionPlayer;
import game.util.Tile;
import game.util.tile.TileProd;

public class PlayerWar extends Player {

	/**
	 * public constructor for the Player class each player starts off the game with
	 * only 15 gold pieces the last action performed by this player is nothing (or
	 * skip) each player will have 6 empty worker slots that will be filled later in
	 * the game each player will have an inventory having 0 of each resource kind
	 * 
	 * @param name the name to be given to this player
	 */
	public PlayerWar(String name) {
		// call the constructor of Player
		super(name);
		// create the units as some armies
		soldiers = 35;
		foodUnits = 10;
	}

	/**
	 * getter for soldiers left for this player
	 * 
	 * @return int the number of soldiers this player has left
	 */
	public int getSoldiers() {
		return soldiers;
	}

	/**
	 * to create an army check if player has soldiers left check if the number of
	 * requested soldiers is greater than the number of the soldiers available, get
	 * the first empty slot in the units array, create a new army with that number
	 * of soldier subtract the number of soldiers for the players available soldiers
	 * put the newly created army in the units array of the soldier
	 * 
	 * @param n the number of soldiers to create the new army with
	 * @return true if army created succesfuly, false if not
	 */
	public boolean createArmy(int n, Tile tile) {
		if (soldiers > 0) {
			if (n < soldiers) {
				int slot = getFirstEmptySlot();
				if (slot != -1) {// if there's a slot available and it's not full
					soldiers = soldiers - n;
					Unit newArmy = new Army(this, tile, n);
					units[slot] = newArmy;
				}
				return true;
			} else {
				System.out.println("/!\\ The number of soldiers left is not enough :(");
				createArmy(n, tile);
				return false;
			}
		} else {
			System.out.println("/!\\ You have no soldiers left :(");
			return false;
		}
	}

	/**
	 * exchanges the resources in the inventory of this player for food
	 */
	public void sellResources() {
		this.lastAction = ActionPlayer.EXCHANGE;
		for (Entry<TileProd, Integer> entry : this.inventory.entrySet()) {
			TileProd product = entry.getKey();
			Integer quantity = entry.getValue();
			int profit = 0;

			if (product == TileProd.ROCK) {
				profit = 0;
			} else if (product == TileProd.CORN) {
				profit = quantity * 5;
			} else if (product == TileProd.SAND) {
				profit = 0;
			} else if (product == TileProd.WOOD) {
				profit = quantity;// *1
			}
			this.setFoodUnits(this.getFoodUnits() + profit);
			entry.setValue(0);
		}
	}

	/**
	 * calculate and return the total number of points acquired by this war player
	 * the amount is calculated by adding the number of gold coins this war player
	 * has to the bonus points points gotten for each tile type and if he has at
	 * least 10 tiles in total he will get an additional 5 points
	 */
	public int calculateTotalPoints() {
		this.points = 0;
		int nbTerritories = 0;
		this.points += this.gold;
		for (int i = 0; i < units.length; i++) {
			this.points += units[i].getTile().getBonusPoints();
			nbTerritories++;
		}
		if (nbTerritories >= 10) {
			this.points += 5;
		}
		return points;
	}

}
