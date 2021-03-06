package game.character;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import game.character.unit.Unit;
import game.util.ActionPlayer;
import game.util.Tile;
import game.util.tile.TileProd;

public abstract class Player {
	int points = 0;
	protected String name;
	/**
	 * dans ce jeu un objet qui existe doit absoluement avoir une place dans une
	 * tuile c'est a dire au lieu de mettre une liste des armmee dans cette class
	 * c'est mieux de mettre une liste des tuiles qui appartient a ce joueur et a
	 * partir de ces tuiles on peux acceder au armees qui sont dedant
	 **/
	protected List<Tile> tiles;
	protected int soldiers;
	protected int gold;
	protected int foodUnits;
	protected ActionPlayer lastAction;
	protected HashMap<TileProd, Integer> inventory = new HashMap<TileProd, Integer>();

	public HashMap<TileProd, Integer> getInventory() {
		return inventory;
	}

	public void setInventory(HashMap<TileProd, Integer> inventory) {
		this.inventory = inventory;
	}

	/**
	 * public constructor for the Player class each player starts off the game with
	 * 0 gold pieces the last action performed by this player is nothing (or skip)
	 * each player will have 6 empty unit slots that will be filled later in the
	 * game each player will have an inventory having 0 of each resource kind
	 * 
	 * @param name the name to be given to this player
	 */
	public Player(String name) {
		this.name = name;
		this.gold = 0;
		this.tiles = new LinkedList<Tile>();
		this.lastAction = ActionPlayer.NOTHING;
		// if wargame create army else creaate workers
		for (TileProd resource : TileProd.values()) {
			inventory.put(resource, 0);
		}
	}

	/**
	 * sets the resource qauntity to a certain value
	 * 
	 * @param tileProd the resource type
	 * @param quantity the new quantity
	 */
	public void setResource(TileProd tileProd, int quantity) {
		this.inventory.put(tileProd, quantity);
	}

	public int getResource(TileProd tileProd) {
		return this.inventory.get(tileProd);
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
	public boolean loseFood(int foodConsumption) {
		// TODO Auto-generated method stub
		if (this.foodUnits >= foodConsumption) {
			this.foodUnits = this.foodUnits - foodConsumption;
			return true;
		} else {
//			this.foodUnits = 0; // should we eat everything before dying XD
			System.out.println("You don't have enough [FOOD UNITS] to feed.");
			return false;
		}
	}

	/**
	 * sell a unit to slavery for as cheap as 1 gold coin
	 * 
	 * @param unit the unit that will lose its freedom *angry face*
	 */
	public void sell(Unit unit) {
		try {
			unit.getTile().setOwner(null);
			unit.setOwner(null);
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
	 * @param i the index of the wanted unit
	 * @return the unit in index i
	 */
	public Tile getTileByIndex(int i) {
//		return this.units[i];
		return this.tiles.get(i);
	}

	/**
	 * tells the workers to do the actions they need to do at the start of the turn
	 * will iterate through all the tiles of this player and call for each tile the
	 * startTurn() method
	 */
	public void startTurn() {
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).getUnit().startTurn();
		}
	}

	/**
	 * ends the turn for this player buy telling the units to do the actions they
	 * need to do at the end of each turn
	 */
	public void nextTurn() {
		for (int i = 0; i < this.tiles.size(); i++) {
			this.tiles.get(i).getUnit().nextTurn();
		}
	}

	/**
	 * check if the player has no workers in his arsenal XD
	 * 
	 * @return true if he has no workers; false if he has a unit
	 */
	public boolean hasUnits() {
		return !this.tiles.isEmpty();
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
	 * the position, what the unit is workign in, how many resources accumulted so
	 * far
	 */
	public void printOutUnitsList() {
		System.out.println("Available workers: ");
		for (int i = 0; i < this.tiles.size(); i++) {
			if (tiles.get(i) != null) {
				System.out.print(i + ": in " + this.tiles.get(i).getPosition().toString() + " working "
						+ this.tiles.get(i).getTileProd() + " has " + this.tiles.get(i).getUnit().getResources()
						+ "; ");
			} else {
				System.out.print(i + ": NONE! ");
			}

		}
		System.out.println();
	}

	public void printOutTilesList() {
		System.out.println("Player's tiles list: ");

		System.out.println();
	}

	/**
	 * get the exact number of NON NULL unit objects of this player if there are non
	 * it will return 0
	 * 
	 * @return the number of initiated workers in the workers array of this player
	 */
	public int getNumberOfUnits() {
		return this.tiles.size();
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

	public int getFoodUnits() {
		return foodUnits;
	}

	/**
	 * 
	 * @param foodUnits
	 */
	public void setFoodUnits(int foodUnits) {
		this.foodUnits = foodUnits;
	}

	/**
	 * 
	 * @return
	 */
	public abstract int calculateTotalPoints();

	/**
	 * 
	 * @return
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

}