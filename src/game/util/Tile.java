package game.util;

import java.util.List;

import game.character.Unit;
import game.player.Player;

public class Tile {
    /* numbr of units */
	private int nbUnit;
	/* list of units */
	private List<Unit> listUnit;
	private Position pos;
	/* the type of tile */
	private Tiletype type;
	/* the first symbol of each type of tiles in he board */
	private String symbole;
	/* the player who owns the tile */
	private Player owner;
	/* list of tile */
	public Tile() {}
	
	/**
	 * public constructor of the Tile in the game 
	 * @param Tiletypr  the type of tile
	 * @param x  the x position of the tile in the board
	 * @param y the y position of the tile in board
	 */
	public Tile(Tiletype type, int x, int y) {
		this.nbUnit=0;
		this.type=type;
		this.pos=new Position(x, y);
		switch(type) {
		case DESERT:
			this.symbole="D";
			break;
		case OCEAN:
			this.symbole="O";
			break;
		case MONTAIN:
			this.symbole="M";
			break;
		case WOOD:
			this.symbole="W";
			break;
		case PLAIN:
			this.symbole="P";
			break;
		}
		
	}
	/**
	 * get the position of tile in the board
	 * @return position of the tile
	 */
	public Position getPos() {
		return pos;
	}
	/**
	 * print the first letter of each tile type as a symbol
	 * @print the symbol of each first letter of tiles
	 */
	public String toString() {
		if(this.owner==null) {
			return symbole+"   ";
		}else {
			return symbole+":"+owner.getName().charAt(0)+" " ;
		}
		
	}
	/**
	 * get the number of Unis in the tile
	 * @return number of units
	 */
	public int getUnit() {
		return nbUnit;
	}
	/**
	 * set a number of units
	 */
	public void setUnit(int unit) {
		this.nbUnit = unit;
	}
	/**
	 * get the type of tile
	 * @return type of tile
	 */
	public Tiletype getType() {
		return type;
	}
	/**
	 * get the player owner of the tile
	 * @return the owner of the tile
	 */
	public Player getOwner() {
		return owner;
	}
	/**
	 * set a player as an owner of the tile
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	/**
	 * get the list of units
	 * @return list of units
	 */
	public List<Unit> getListUnit() {
		return listUnit;
	}
	/**
	 * get the first letter of each tile type as a symbol
	 * @return the first symbol of tile type
	 */
	public String getSymbole() {
		return symbole;
	}
	
	
	
}
