package game.character.unit;

import game.character.Player;
import game.util.Tile;

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
		super(owner, newTile);// on super we already call putOnTile() maybe it's better not to and do it here?
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
	 * size also the points of food
	 */
	public void setSize(int size) {
		this.size = size;
		this.attackPoints = size + this.tile.getBonusAttackPoints();
		this.foodConsumption = size * this.tile.getBonusFoodConsumption();
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
	 * gets the tile this worker is on right now
	 * 
	 * @return the tile of this worker
	 */
	public Tile getTile() {
		return this.tile;
	}

	public String toString() {
		return "Owner:" + this.getOwner().toString() + "; AP:" + this.getAttackPoints();
	}

}