package game.character.unit;

import game.character.Player;
import game.util.Tile;
import game.util.tile.DesertsTile;
import game.util.tile.ForestsTile;
import game.util.tile.MountainsTile;
import game.util.tile.PlainsTile;

/**
 * 
 * @author fayss
 *
 */
public class Army extends Unit {
	/**
	 * public constructor for a worker the worker will start with only 1 gold which
	 * he will take from the player the worker will spawn in the owner's starting
	 * tile
	 * 
	 * @param owner the new owner of the newly created worker object
	 */
	public Army(Player owner, Tile newTile, int size) {
		super(owner, newTile);// on super we already call putOnTile()
		setSize(size);
		// after defining the attack points we need to attack nearby tiles
		this.conquerEnemies();
		System.out.println(this.toString());
		this.foodConsumption = size + newTile.getBonusFoodConsumption();
	}

	public boolean putOnTile(Tile newTile) {
		// put the army on the newTile if possible
		if (newTile.getOwner() == null) {
			this.tile = newTile;
			this.tile.setOwner(this.owner);
			return true;
		} else {
			System.out.println("/!\\ Tile already has another owner :(");
			return false;
		}
	}

	public void conquerEnemies() {
		Tile adjacentTiles[] = this.tile.getAdjacent();
		for (int i = 0; i < adjacentTiles.length; i++) {
			this.attackTile(adjacentTiles[i]);
		}
	}

	/**
	 * set the size and update the attack points of the army accordingly to the new
	 * size
	 */
	public void setSize(int size) {
		this.size = size;
		this.attackPoints = size + this.tile.getBonusAttackPoints();
	}

	/**
	 * this army will only attack a certain tile from this one the first case is if
	 * the size of the victim army is smaller we calculate the half, if the half is
	 * less than one then it had only one soldier therefore the soldier will change
	 * sides; if it's bigger than one we will divide the army by half
	 * 
	 * @param tile
	 * @return
	 */
	public void attackTile(Tile victimTile) {
		if (victimTile != null) {
			if (victimTile.getUnit() != null) {
				int victimSize = victimTile.getUnit().getAttackPoints();
				// first case
				if (this.getAttackPoints() > victimSize) {
					if (victimTile.getOwner() != this.getOwner()) {
						int newArmySize = victimTile.getUnit().getSize() / 2;
						if (newArmySize < 1) {
							victimTile.setOwner(this.owner);// the units change side automatically
							System.out.println(
									" [ATTACK] Conquered an enemy! His tile" + victimTile.toString() + " is now yours");
						} else {
							int oldSize = victimTile.getUnit().getSize();
							victimTile.getUnit().setSize(newArmySize);
							System.out.println(" [ATTACK] The size of the enemy army has deminished from:" + oldSize
									+ "; to:" + victimTile.getUnit().getSize());
						}
						this.getPayed(2);
					} else {
						victimTile.getUnit().incrementSize();
						this.getPayed();
					}

				} else {
					System.out.println("you have: " + this.getAttackPoints() + "AP; your enemy has " + victimSize);
				}
			} else {
//				System.out.println("VICTIM TILE HAS NO UNITS ERR");
			}
		} else {
//			System.out.println("NO VICTIM TILE ERR");
		}

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

	public String toString() {
		return "Owner:" + this.owner.toString() + "; AP:" + this.getAttackPoints();
	}

	// /**
	// * start working in the current tile need to check if the owner of the worker
	// is
	// * the owner of the tile if so he will start working
	// */
	// public void work() {
	// this.tile.addWorker(this);
	// if (!this.tile.isStartingTile()) {
	// this.working = true;
	// }

	// // if (this.tile.getOwner() == null || this.tile.getOwner() == map...){
	// // if (this.gold >= 1){
	// // this.working = true;
	// // }
	// // }
	// }

	// /**
	// * add one gold to the bag of this worker $
	// */
	// public void getPayed() {
	// this.gold++;
	// }

	// /**
	// * same as getPayed() but with one or more coins
	// *
	// * @param salary the amount to be added to the bag of gold of this worker
	// */
	// public void getPayed(int salary) {
	// if (this.owner.getGold() >= salary) {
	// this.gold += salary;
	// }
	// }

	// /**
	// * send the resources to the owner of this worker
	// */
	// public void sendResources() {
	// this.owner.receiveResources(this.tile.getTileProd(), resources);
	// this.resources = 0;
	// }

	/**
	 * performs the actions the worker needs to do at the end of each turn like
	 * sending the resources to the owner
	 */
	public void nextTurn() {
		sendResources();
	}

}