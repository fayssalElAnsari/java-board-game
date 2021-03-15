package game.character;

import java.util.HashMap;
import java.util.Map.Entry;

import game.character.unit.Unit;
import game.character.unit.Worker;
import game.util.ActionPlayer;
import game.util.tile.DesertsTile;
import game.util.tile.ForestsTile;
import game.util.tile.MountainsTile;
import game.util.tile.PlainsTile;
import game.util.tile.TileProd;

public class Player {
	private String name;
	private Unit[] units;
	private Worker[] workers;
	private int gold;
	private ActionPlayer lastAction;
	private HashMap<TileProd, Integer> inventory = new HashMap<TileProd, Integer>();

	/**
	 * public constructor for the Player class each player starts off the game with
	 * only 15 gold pieces the last action performed by this player is nothing (or
	 * skip) each player will have 6 empty worker slots that will be filled later in
	 * the game each player will have an inventory having 0 of each resource kind
	 * @param name the name to be given to this player
	 */
	public Player(String name) {
		this.name = name;
		this.gold = 15;
		this.lastAction = ActionPlayer.NOTHING;
		workers = new Worker[6];
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
	 * get the worker in index i from the array of worker[] workers
	 * 
	 * @param i the index of the wanted worker
	 * @return the worker in index i
	 */
	public Worker getWorkerByIndex(int i) {
		return this.workers[i];
	}

	/**
	 * buy a worker using 1 gold from it's owner when a worker is bought he will be
	 * spawned in the owner's spawning tile but he will not start working as no
	 * worker is able to work in the spawn tile this buyWorker method does not take
	 * in any params so it will search for the firstemptyslot and put the newly
	 * bought worker there
	 * 
	 * @return the worker that has just been bought
	 */
	public Worker buyWorker() {
		this.payGold();
		Worker worker = new Worker(this);
		int i = getFirstEmptySlot();
		if (i != -1) {
			workers[i] = worker;
		}
		return worker;
	}

	/**
	 * same as buyworker() but the player's empty worker slot is determined by the
	 * params
	 * 
	 * @param i the index of the empty player's slot
	 * @return the newly created player
	 */
	public Worker buyWorker(int i) {
		this.payGold();
		Worker worker = new Worker(this);
		workers[i] = worker;
		return worker;
	}

	/**
	 * finds the first empty worker slot of this tile
	 * 
	 * @return the index of the first empty slot in this tile
	 */
	public int getFirstEmptySlot() {
		int result = -1;
		for (int i = 0; i < workers.length; i++) {
			if (workers[i] == null) {
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
		for (int i = 0; i < workers.length; i++) {
			workers[i].startTurn();
		}
	}

	/**
	 * ends the turn for this player buy telling the workers to do the actions they
	 * need to do at the end of each turn
	 */
	public void nextTurn() {
		for (int i = 0; i < workers.length; i++) {
			if (workers[i] != null) {
				workers[i].nextTurn();
			}
		}
	}

	/**
	 * get the worker with the biggest index (last worker) from this player's array
	 * of workers
	 * 
	 * @return the worker with the biggest index
	 */
	public Worker getLastWorker() {
		Worker w;
		if (hasWorkers()) {// if the player has atleast a worker
			w = workers[getFirstEmptySlot() - 1];
			return w;
		} else {
			buyWorker();
			return workers[0];
		}
	}

	/**
	 * check if the player has no workers in his arsenal XD
	 * 
	 * @return true if he has no workers; false if he has a worker
	 */
	public boolean hasWorkers() {
		for (int i = 0; i < workers.length; i++) {
			if (workers[i] != null) {
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
	 * at the beginning of each turn the player needs to pay his workers accordignly
	 * to what type of tile they are working on
	 */
	public void payWorkers() {
		boolean affordable = true;
		for (Worker worker : workers) {
			if (worker.getTile() instanceof MountainsTile) {
				affordable = payGold(5);
				worker.getPayed(5);
			} else if (worker.getTile() instanceof DesertsTile) {
				payGold(3);
				worker.getPayed(3);
			} else if (worker.getTile() instanceof ForestsTile) {
				payGold(1);
				worker.getPayed();
			} else if (worker.getTile() instanceof PlainsTile) {
				payGold(1);
				worker.getPayed();
			}
			if (!affordable) {
				worker = null;
			}
		}
	}

	/**
	 * prints out general information about the workers of this player the index:
	 * the position, what the worker is workign in, how many resources accumulted so
	 * far
	 */
	public void printOutWorkersList() {
		System.out.println("Available workers: ");
		for (int i = 0; i < workers.length; i++) {
			if (workers[i] != null) {
				System.out.print(i + ": in " + workers[i].getTile().getPosition().toString() + " working "
						+ workers[i].getTile().getTileProd() + " has " + workers[i].getResources() + "; ");
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
	public int getNumberOfWorkers() {
		int result = 0;
		for (int i = 0; i < workers.length; i++) {
			if (workers[i] != null) {
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

}