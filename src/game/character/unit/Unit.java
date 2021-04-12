package game.character.unit;

import game.character.Player;
import game.util.Tile;

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
	 * @param owner the new owner of the newly created unit object
	 */
	public Unit(Player owner, Tile newTile) {
		this.owner = owner;
		this.putOnTile(newTile);
		this.tile.setUnit(this);
		this.tile.getUnit().harvest();// the army will start harvesting as soon as it's put on the tile
	}

	public boolean eat() {
		boolean ate = false;
		if (this.owner != null) {
			ate = this.getOwner().loseFood(this.foodConsumption);
			if (ate) {
				System.out.println(" just ate: " + foodConsumption);
			}
		}
		return ate;
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
	 * as the name suggest this unit will either take the required number of food
	 * units from its owner or it will die and give him one gold; if the eating is
	 * successful the unit will be able to put in work by calling the function
	 * harvest()
	 * 
	 */
	public void eatOrDie() {
		if (this.owner != null) {
			if (this.eat()) {
				this.harvest();
			} else {
				this.owner.sell(this);
			}
		}
	}

	/**
	 * performs the actions needed by the unit at the beginning of each turn if the
	 * unit is working in its tile it will add the resources according the unit's
	 * speed if the resources were added to the unit's inventory it will print it
	 * out
	 */
	public void startTurn() {
		eatOrDie();

//		this.getTurnSalary();
	}

	/**
	 * harvest resources from this unit's tile and put it in its inventory
	 */
	protected void harvest() {
		if (this.tile.loseResources(this.getSize())) {
			this.resources = this.getSize();
			System.out.println("  /!\\  added resources: " + this.getSize());
		} else {
			System.out.println(" [WARNING] There are not resources left to be [harvested] on this tile");
		}

	}

	/**
	 * if the player is working will check which type of tile he's on and will get
	 * payed accordingly
	 */
	public boolean getTurnSalary() {
		try {
			this.getPayed(this.tile.getTurnSalary());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * gets how much resources this unit has in its bag
	 * 
	 * @return the amount of resources this unit has extracted
	 */
	public int getResources() {
		return this.resources;
	}

	/**
	 * gets the tile this unit is on right now
	 * 
	 * @return the tile of this unit
	 */
	public Tile getTile() {
		return this.tile;
	}

	/**
	 * add one gold to the bag of this unit $
	 */
	public void getPayed() {
		this.gold++;
	}

	/**
	 * same as getPayed() but with one or more coins
	 * 
	 * @param salary the amount to be added to the bag of gold of this unit
	 */
	public void getPayed(int salary) {
		if (this.owner.getGold() >= salary) {
			this.gold += salary;
		}
	}

	/**
	 * send the resources to the owner of this unit
	 */
	public void sendResources() {
		if (this.owner != null) {
			this.owner.receiveResources(this.tile.getTileProd(), resources);
		}
		this.resources = 0;
	}

	/**
	 * performs the actions the unit needs to do at the end of each turn like
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