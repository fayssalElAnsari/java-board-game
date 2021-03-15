package game.character.unit;

import game.character.Player;
import game.character.unit.Unit;
import game.util.Tile;
import game.util.tile.DesertsTile;
import game.util.tile.ForestsTile;
import game.util.tile.MountainsTile;
import game.util.tile.PlainsTile;

public class Army extends Unit {

    private Player owner;
	private Tile tile;
    private int size;

    /**
     * public constructor for a worker the worker will start with only 1 gold which
     * he will take from the player the worker will spawn in the owner's starting
     * tile
     * 
     * @param owner the new owner of the newly created worker object
     */
	public Army(Player owner, int size) {
        super(owner);
		this.size = size;


	}

	public boolean putOnTile(Tile newTile) {
		if (newTile.getOwner() == this.owner || newTile.getOwner() == null) {
			this.tile = newTile;
			this.tile.setOwner(this.owner);
			return true;
		} else {
			System.out.println("/!\\ Tile already has another owner :(");
		}
		return false;
	}

	/**
	 * performs the actions needed by the worker at the beginning of each turn if
	 * the worker is working in its tile it will add the resources according the
	 * worker's speed if the resources were added to the worker's inventory it will
	 * print it out
	 */
	public void startTurn() {
			System.out.println("  /!\\  added resources: ");
			this.getTurnSalary();
	}

	/**
	 * if the player is working will check which type of tile he's on and will get
	 * payed accordingly
	 */
	public boolean getTurnSalary() {
		boolean result = false;
			if (this.tile instanceof MountainsTile) {
				this.getPayed(5);
				return true;
			} else if (this.tile instanceof DesertsTile) {
				this.getPayed(3);
				return true;
			} else if (this.tile instanceof ForestsTile) {
				this.getPayed();
				return true;
			} else if (this.tile instanceof PlainsTile) {
				this.getPayed();
				return true;
			} else {
				return result;
			}
	}

	

	/**
	 * gets the tile this worker is on right now
	 * 
	 * @return the tile of this worker
	 */
	public Tile getTile() {
		return this.tile;
	}

	// /**
	//  * start working in the current tile need to check if the owner of the worker is
	//  * the owner of the tile if so he will start working
	//  */
	// public void work() {
	// 	this.tile.addWorker(this);
	// 	if (!this.tile.isStartingTile()) {
	// 		this.working = true;
	// 	}

	// 	// if (this.tile.getOwner() == null || this.tile.getOwner() == map...){
	// 	// if (this.gold >= 1){
	// 	// this.working = true;
	// 	// }
	// 	// }
	// }

	// /**
	//  * add one gold to the bag of this worker $
	//  */
	// public void getPayed() {
	// 	this.gold++;
	// }

	// /**
	//  * same as getPayed() but with one or more coins
	//  * 
	//  * @param salary the amount to be added to the bag of gold of this worker
	//  */
	// public void getPayed(int salary) {
	// 	if (this.owner.getGold() >= salary) {
	// 		this.gold += salary;
	// 	}
	// }

	// /**
	//  * send the resources to the owner of this worker
	//  */
	// public void sendResources() {
	// 	this.owner.receiveResources(this.tile.getTileProd(), resources);
	// 	this.resources = 0;
	// }

	/**
	 * performs the actions the worker needs to do at the end of each turn like
	 * sending the resources to the owner
	 */
	public void nextTurn() {
		sendResources();
	}


}