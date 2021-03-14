package game.util;

import game.Game;
import game.GameMain;
import game.character.Player;
import game.character.unit.Unit;
import game.character.unit.Army;
import game.character.unit.Worker;
import game.util.tile.TileProd;

public abstract class Tile {
	private final Position position;
	private final TileProd tileProd;
	private int resources = 5000;
	private Unit unit;
	private Army army;
	private Player owner;
	private boolean isSpawnTile;

	/**
	 * public constructor for Tile 
	 * @param position the position of the time in the map
	 * @param tileProd the type of resource to be produced by the tile
	 */
	public Tile(Position position, TileProd tileProd) {
		this.position = position;
		this.tileProd = tileProd;
	}

	/**
	 * gets the position of this tile
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
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
		this.unit = new Worker(this.owner);
	}

	/**
	 * reduce the resources available for this extraction from this tile accordingly
	 * 
	 * @param loss the amount to be subtracted from this tile
	 * @return true if the amount available for extraction is bigger than the amount
	 *         to be extracted, false if the extraction was not succesful
	 */
	public boolean loseResources(int loss) {
		if (resources > loss) {
			this.resources = this.resources - loss;
			return true;
		} else
			return false;

	}

	/**
	 * give the tile production resource type
	 * @return the tile production type
	 */
	public TileProd getTileProd() {
		return this.tileProd;
	}

	/**
	 * checks if the current tile has an owner and a unit
	 * @return
	 */
	public boolean checkIfEmpty() {
		return ((this.owner != null) && (this.unit != null));
	}

	public void setWorker(Unit unit) {
		// if(GameMain.)
		// this.worker = unit;
	}

}
