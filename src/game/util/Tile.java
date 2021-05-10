package game.util;

import game.GameMain;
import game.character.Player;
import game.character.unit.Unit;
import game.util.tile.TileProd;

/**
 * 
 * @author fayss
 *
 */
public abstract class Tile {
	private final Position position;
	private final TileProd tileProd;
	private int resources = 5000;
	private Unit unit;
	private Player owner;
	protected int maxNbSoldiers = 5;
	protected char typeSymbol = 'T';
	protected String colorCodeFg = "";
	protected String colorCodeBg = "";
	protected boolean isOcean = false;
	protected int bonusAttackPoints = 0;
	protected int bonusFoodConsumption = 1;
	protected int bonusPoints = 0;
	protected int turnSalary = 0;
	protected int exchangeRate = 1;

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
	 * get the salary of this turn
	 * 
	 * @return the salary
	 */
	public int getTurnSalary() {
		return turnSalary;
	}

	public void setTurnSalary(int turnSalary) {
		this.turnSalary = turnSalary;
	}

	public Unit getUnit() {
		return unit;
	}

	public int getBonusFoodConsumption() {
		return bonusFoodConsumption;
	}

	public int getBonusAttackPoints() {
		return bonusAttackPoints;
	}

	public boolean isOcean() {
		return isOcean;
	}

	public char getTypeSymbol() {
		return typeSymbol;
	}

	public int getMaxNbSoldiers() {
		return maxNbSoldiers;
	}

	public void setMaxNbSoldiers(int maxNbSoldiers) {
		this.maxNbSoldiers = maxNbSoldiers;
	}

	/**
	 * gets the first charcter of the owner of a tile in this map ther could be
	 * ambiguity if two owners names start off with the same letter
	 * 
	 * @param tile what tile to get the symbol of the owner for
	 * @return the first charcter of the name of the owner of a passed in tile
	 */
	public char getOwnerSymbol() {
		if (this.getOwner() != null) {
			return this.getOwner().getName().charAt(0);
		}
		return '*';
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
//		if (this.owner != null) {
//			return this.owner;
//		} else {
//			return
//		}
		return this.owner;
	}

	/**
	 * sets the owner of this tile to the passed in param
	 * 
	 * @param newOwner the newowner of this tile object
	 */
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
		if (this.getUnit() != null) {
			this.unit.setOwner(newOwner);// if the owner of this tile changes then the units on it go on his side
		}
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

	// TODO no idea how to improve this method

	/**
	 * 
	 * @return an array containing all the adjacent tiles to this one
	 */
	public Tile[] getAdjacent() {
		int nb = 0;
		Tile tiles[] = new Tile[4];
		try {
			if (!GameMain.game.getMap()
					.findTileByPosition(
							new Position(this.position.getXCoordinate() - 1, this.position.getYCoordinate()))
					.isOcean()) {
				nb++;
				tiles[0] = GameMain.game.getMap().findTileByPosition(
						new Position(this.position.getXCoordinate() - 1, this.position.getYCoordinate()));

			}
		} catch (Exception e) {

		}
		try {
			if (!GameMain.game.getMap()
					.findTileByPosition(
							new Position(this.position.getXCoordinate() + 1, this.position.getYCoordinate()))
					.isOcean()) {
				tiles[1] = GameMain.game.getMap().findTileByPosition(
						new Position(this.position.getXCoordinate() + 1, this.position.getYCoordinate()));
				nb++;
			}
		} catch (Exception e) {

		}
		try {
			if (!GameMain.game.getMap().findTileByPosition(
					new Position(this.position.getXCoordinate(), this.position.getYCoordinate() - 1)).isOcean) {
				tiles[2] = GameMain.game.getMap().findTileByPosition(
						new Position(this.position.getXCoordinate(), this.position.getYCoordinate() - 1));
				nb++;
			}
		} catch (Exception e) {

		}
		try {
			if (!GameMain.game.getMap()
					.findTileByPosition(
							new Position(this.position.getXCoordinate(), this.position.getYCoordinate() + 1))
					.isOcean()) {
				tiles[3] = GameMain.game.getMap().findTileByPosition(
						new Position(this.position.getXCoordinate(), this.position.getYCoordinate() + 1));
				nb++;
			}
		} catch (Exception e) {

		}
		Tile nonEmptyTiles[] = new Tile[nb];
		int i = 0;
		int j = 0;
		while (i < nb && j < 4) {
			if (tiles[j] != null) {
				nonEmptyTiles[i] = tiles[j];
//				System.out.println("!!!! " + nonEmptyTiles[i].toString());
				i++;
			}
			j++;
		}
//		System.out.println("!!!! found " + nb + " tiles nearby");
		return nonEmptyTiles;
	}

	public String toString() {
		String owner = "NO_ONE";
		if (this.getOwner() != null) {
			owner = this.getOwner().toString();
		}
		return "pos: " + this.position.toString() + "; Owner: " + owner + "; Type: " + this.getTypeSymbol();
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

}