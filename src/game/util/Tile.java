package game.util;

import game.character.Player;
import game.character.unit.Unit;
import game.util.tile.TileProd;

public abstract class Tile {
	private final Position position;
	private final TileProd tileProd;
	private int resources = 5000;
	private Unit unit;
	private Player owner;
	protected int maxNbSoldiers = 5;

	public int getMaxNbSoldiers() {
		return maxNbSoldiers;
	}

	public void setMaxNbSoldiers(int maxNbSoldiers) {
		this.maxNbSoldiers = maxNbSoldiers;
	}

	/**
	 * public constructor for Tile
	 * 
	 * @param position the position of the time in the map
	 * @param tileProd the type of resource to be produced by the tile
	 */
	public Tile(final Position position, final TileProd tileProd) {
		this.position = position;
		this.tileProd = tileProd;
	}

	/**
	 * gets the position of this tile
	 * 
	 * @return the exact position of this tile
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * gets the owner of this tile
	 * 
	 * @return the player who owns this tile (has workers on it)
	 */
	public Player getOwner() {
		return this.owner;
	}

	/**
	 * sets the owner of this tile to the passed in param
	 * 
	 * @param newOwner the newowner of this tile object
	 */
	public void setOwner(final Player newOwner, Tile newTile) {
		this.owner = newOwner;
		// this.unit = new Unit(this.owner, newTile);
	}

	/**
	 * reduce the resources available for this extraction from this tile accordingly
	 * 
	 * @param loss the amount to be subtracted from this tile
	 * @return true if the amount available for extraction is bigger than the amount
	 *         to be extracted, false if the extraction was not succesful
	 */
	public boolean loseResources(final int loss) {
		if (resources > loss) {
			this.resources = this.resources - loss;
			return true;
		} else
			return false;

	}

	/**
	 * give the tile production resource type
	 * 
	 * @return the tile production type
	 */
	public TileProd getTileProd() {
		return this.tileProd;
	}

	/**
	 * checks if the current tile has an owner and a unit
	 * 
	 * @return
	 */
	public boolean checkIfEmpty() {
		return ((this.owner != null) && (this.unit != null));
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}