package game.character;

import java.util.HashMap;
import java.util.Map.Entry;

import game.character.unit.Unit;
import game.util.ActionPlayer;
import game.util.Tile;
import game.util.tile.TileProd;

public abstract class Player {
	protected String name;
	protected Unit[] units;
	protected int soldiers;
	protected int gold;
	protected int foodUnits;
	protected ActionPlayer lastAction;
	protected HashMap<TileProd, Integer> inventory = new HashMap<TileProd, Integer>();

	/**
	 * public constructor for the Player class each player starts off the game with
	 * 0 gold pieces the last action performed by this player is nothing (or skip)
	 * each player will have 6 empty worker slots that will be filled later in the
	 * game each player will have an inventory having 0 of each resource kind
	 * 
	 * @param name the name to be given to this player
	 */
	public Player(String name) {
		this.name = name;
		this.gold = 0;
		this.units = new Unit[40];// i think it would be simpler to just use a list instead could do it later idk
		this.lastAction = ActionPlayer.NOTHING;
		// if wargame create army else creaate workers
		for (TileProd resource : TileProd.values()) {
			inventory.put(resource, 0);
		}
	}

	/**
	 * sets the last action performed by this player to one of the actions from
	 * AtionPlayer Enum
	 * 
	 * @param action the ActionPlayer of this player
	 */
	public void setLastAction(ActionPlayer action) {
		this.lastAction = action;
	}

	/**
	 * lose n of foodUnits probably by sending them to deployed units to eat
	 * 
	 * @param n the number of foodUnits to be sent to the units
	 */
	public void loseFood(int foodConsumption) {
		// TODO Auto-generated method stub
		this.foodUnits = this.foodUnits - foodConsumption;
	}

	/**
	 * sell a unit to slavery for as cheap as 1 gold coin
	 * 
	 * @param unit the unit that will lose its freedom *angry face*
	 */
	public void sell(Unit unit) {
		try {
			killUnit(unit);
			getPayed(1);
		} catch (Exception e) {
			System.out.println("couldn't sell unit :(");
		}

	}

	/**
	 * get the last action performed by the player
	 * 
	 * @return the lastAction performed by this player
	 */
	public ActionPlayer getLastAction() {
		return this.lastAction;
	}

	/**
	 * get this player's name
	 * 
	 * @return the name of this player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get the unit in index i from the array of units[] units
	 * 
	 * @param i the index of the wanted worker
	 * @return the worker in index i
	 */
	public Unit getUnitByIndex(int i) {
		return this.units[i];
	}

	/**
	 * finds the first empty unit slot of this player
	 * 
	 * @return the index of the first empty slot in this player
	 */
	public int getFirstEmptySlot() {
		int result = -1;
		for (int i = 0; i < units.length; i++) {
			if (units[i] == null) {
				result = i;
				break;
			}
		}
		return result;
	}

	/**
	 * tells the workers to do the actions they need to do at the start of the turn
	 */
	public void startTurn() {
		for (int i = 0; i < units.length; i++) {
			if (units[i] != null) {
				units[i].startTurn();
			}
		}
	}

	/**
	 * a method to delete a certain object of type Unit it will be used to kill
	 * units
	 * 
	 * @param unit the unit to be killed
	 */
	public void killUnit(Unit unit) {
		unit = null;
	}

	/**
	 * ends the turn for this player buy telling the unitss to do the actions they
	 * need to do at the end of each turn
	 */
	public void nextTurn() {
		if (units != null) {
			for (int i = 0; i < units.length; i++) {
				if (units[i] != null) {
					units[i].nextTurn();
				}
			}
		}
	}

	/**
	 * get the worker with the biggest index (last worker) from this player's array
	 * of workers
	 * 
	 * @return the worker with the biggest index
	 */
	public Unit getLastUnit() {
		Unit u;
		if (hasUnits()) {// if the player has atleast a worker
			u = units[getFirstEmptySlot() - 1];
			return u;
		} else {
			return units[0];
		}
	}

	/**
	 * check if the player has no workers in his arsenal XD
	 * 
	 * @return true if he has no workers; false if he has a worker
	 */
	public boolean hasUnits() {
		for (int i = 0; i < units.length; i++) {
			if (units[i] != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * give this player some gold $$$
	 * 
	 * @param profit the amount to be given to this player
	 */
	public void getPayed(int profit) {
		this.gold += profit;
	}

	/**
	 * prints out general information about the inventory of this Player
	 */
	public void printOutInventory() {
		for (Entry<TileProd, Integer> entry : this.inventory.entrySet()) {
			TileProd product = entry.getKey();
			Integer quantity = entry.getValue();
			if (product != TileProd.NONE) {
				System.out.print(product.toString() + ": " + quantity + "; ");
			}
		}
		System.out.println();
	}

	/**
	 * exchanges the resources in the inventory of this player for gold $
	 */
	public void sellResources() {
		this.lastAction = ActionPlayer.EXCHANGE;
		for (Entry<TileProd, Integer> entry : this.inventory.entrySet()) {
			TileProd product = entry.getKey();
			Integer quantity = entry.getValue();
			int profit = 0;
			if (product == TileProd.ROCK) {
				profit = quantity * 8;
			} else if (product == TileProd.CORN) {
				profit = quantity * 3;
			} else if (product == TileProd.SAND) {
				profit = quantity;
			} else if (product == TileProd.WOOD) {
				profit = quantity;
			}
			getPayed(profit);
			entry.setValue(0);
		}
	}

	/**
	 * perform the action of skipping this round (do nothing) and get payed 1 gold
	 */
	public void skipRound() {
		this.lastAction = ActionPlayer.NOTHING;
		getPayed(1);
	}

	/**
	 * prints out general information about the workers of this player the index:
	 * the position, what the worker is workign in, how many resources accumulted so
	 * far
	 */
	public void printOutUnitsList() {
		System.out.println("Available workers: ");
		for (int i = 0; i < units.length; i++) {
			if (units[i] != null) {
				System.out.print(i + ": in " + units[i].getTile().getPosition().toString() + " working "
						+ units[i].getTile().getTileProd() + " has " + units[i].getResources() + "; ");
			} else {
				System.out.print(i + ": NONE! ");
			}

		}
		System.out.println();
	}

	/**
	 * get the exact number of NON NULL worker objects of this player if there are
	 * non it will return 0
	 * 
	 * @return the number of initiated workers in the workers array of this player
	 */
	public int getNumberOfUnits() {
		int result = 0;

		for (int i = 0; i < units.length; i++) {
			if (units[i] != null) {
				result += 1;
				break;
			}
		}
		return result;
	}

	/**
	 * lose some gold
	 * 
	 * @param loss the amount to be subtracted from the gold bag of this player
	 * @return if there is enough gold it will be subtracted, else it will return
	 *         false
	 */
	public boolean payGold(int loss) {
		if (this.gold >= loss) {
			this.gold -= loss;
			return true;
		}
		return false;
	}

	/**
	 * same as payGold() but this time only 1 gold is lost
	 * 
	 * @return true of there is at least one gold, false if 1 gold cannot be
	 *         subtracted
	 */
	public boolean payGold() {
		if (this.gold >= 1) {
			this.gold--;
			return true;
		}
		return false;
	}

	/**
	 * collect resources and put them in the inventory with each type accordingly
	 * 
	 * @param product  the type of resource (this is the key in the inventory map)
	 * @param quantity the quantity to be added to the (this is the value in the
	 *                 inventory map)
	 */
	public void receiveResources(TileProd product, int quantity) {
		inventory.put(product, inventory.get(product) + quantity);
	}

	/**
	 * gets how much gold this player has
	 * 
	 * @return the amount of gold in this Player's bag $$
	 */
	public int getGold() {
		return this.gold;
	}

	/**
	 * each player could have a predefined number of soldiers this method get that
	 * that number (only used by PlayerWar for the moment)
	 * 
	 * @return int the number of soldiers of this player
	 */
	public int getSoldiers() {
		return this.soldiers;
	}

	public abstract boolean createArmy(int armySize, Tile tile);

}