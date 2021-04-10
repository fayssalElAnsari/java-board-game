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
public abstract class Unit {
	protected Player owner;
	protected int gold = 0;
	protected Tile tile;
	protected int size = 1;
	protected int resources = 0;
	protected int attackPoints = 1;
	protected int foodConsumption = 1;

	/**
	 * 
	 * @param owner the new owner of the newly created worker object
	 */
	public Unit(Player owner, Tile newTile) {
		this.owner = owner;
		this.putOnTile(newTile);
		this.tile.setUnit(this);
	}

	public int getAttackPoints() {
		return this.attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean putOnTile(Tile newTile) {
		if (newTile.getOwner() == null) {
			this.tile = newTile;
			this.tile.setOwner(this.owner);
			return true;
		} else if (newTile.getOwner() == this.owner) {
			System.out.println("/!\\ You already own this tile :)");
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
	 * gets how much resources this worker has in its bag
	 * 
	 * @return the amount of resources this worker has extracted
	 */
	public int getResources() {
		return this.resources;
	}

	/**
	 * gets the tile this worker is on right now
	 * 
	 * @return the tile of this worker
	 */
	public Tile getTile() {
		return this.tile;
	}

	/**
	 * add one gold to the bag of this worker $
	 */
	public void getPayed() {
		this.gold++;
	}

	/**
	 * same as getPayed() but with one or more coins
	 * 
	 * @param salary the amount to be added to the bag of gold of this worker
	 */
	public void getPayed(int salary) {
		if (this.owner.getGold() >= salary) {
			this.gold += salary;
		}
	}

	/**
	 * send the resources to the owner of this worker
	 */
	public void sendResources() {
		this.owner.receiveResources(this.tile.getTileProd(), resources);
		this.resources = 0;
	}

	/**
	 * performs the actions the worker needs to do at the end of each turn like
	 * sending the resources to the owner
	 */
	public void nextTurn() {
		sendResources();
	}

	public void incrementSize() {
		if (this.tile.getMaxNbSoldiers() > this.size) {
			System.out.println(" [SUPPORT] You have succesfully trained [1 soldier] on"
					+ this.getTile().getPosition().toString() + " !");
			this.size++;
		} else {
			System.out.println(" [INFO] You can't increase the army size on" + this.getTile().getPosition().toString()
					+ " anymore!");
		}

	}
}