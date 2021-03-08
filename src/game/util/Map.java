package game.util;

import java.util.Random;

import game.util.tile.DesertsTile;
import game.util.tile.ForestsTile;
import game.util.tile.MountainsTile;
import game.util.tile.OceanTile;
import game.util.tile.PlainsTile;

public class Map {
	private Tile[][] tiles;
	private String name;
	private int width;
	private int height;
	// TODO: add the condition where all land tiles are connected
	// TODO: at least two ocean tiles in a map

	/**
	 * public constructor of the map
	 * 
	 * @param name the name of the map
	 * @param width the width of the map
	 * @param height the height of the map
	 */
	public Map(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		// Class[] possibleClasses = {DesertsTile.class, ForestsTile.class,
		// MountainsTile.class, PlainsTile.class};
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				tiles[i][j] = new OceanTile(new Position(i, j));
			}
		}

		Random r = new Random();
		for (int j = 1; j < height-1; j++) {
			for (int i = 1; i < width-1; i++) {
				// there should be a better way to do this obviously XD
				int n = r.nextInt(5);
				if (getNumberOfAdjacentOceans(tiles[i][j]) == 4){
					n = r.nextInt(4);
					// System.out.println("/!\\ found an island inside the island :O");
				}
				if (n == 0) {
					tiles[i][j] = new MountainsTile(new Position(i, j));// position redundancy??
				} else if (n == 1) {
					tiles[i][j] = new PlainsTile(new Position(i, j));// position redundancy??
				} else if (n == 2) {
					tiles[i][j] = new DesertsTile(new Position(i, j));// position redundancy??
				} else if (n == 3) {
					tiles[i][j] = new ForestsTile(new Position(i, j));// position redundancy??
				} else if (n == 4) {
					tiles[i][j] = new OceanTile(new Position(i, j));// position redundancy??
				}

			}
		}
		


	}

	/**
	 * 
	 * 	check the tile to the top, left, down, right
		if three of these tile are ocean then the next random choice shouldn't have ocean in it
		to check we could increment a number from 0 whenever we find an ocean tile
		so when the number is equal to 3 the new tile choice pool shouldn't contain ocean tile
		there are also 4 cases where there could be only 3 adjacent tiles 
		that is when the current tile is in the edges of the map
		so when we are on the edges we should start out by one
		and another 4 cases that is the corners with 2 starting ocean tiles
		meaning we will assume that everything that isn't showing on the map is an ocean
		the easiest way to do this is to actually surround the map with ocean tiles like the teacher did
		for now we will do the EZ way

	 * @param tile the tile to check its neighbours
	 * @return the number of adjacent ocean tiles
	 */

	public int getNumberOfAdjacentOceans(Tile tile){
		int result = 0;
		int x = tile.getPosition().getXCoordinate();
		int y = tile.getPosition().getYCoordinate();

		if(tiles[x-1][y] instanceof OceanTile){
			result++;
		}
		if(tiles[x+1][y] instanceof OceanTile){
			result++;
		}
		if(tiles[x][y-1] instanceof OceanTile){
			result++;
		}
		if(tiles[x][y+1] instanceof OceanTile){
			result++;
		}
		// for(int i = x; i < x+3; i=i+2){
		// 	for(int j = y; j < y+3; j=j+2){
		// 		if(tiles[x][y] instanceof OceanTile){
		// 			result++;
		// 		}
		// 	}
		// }
		System.out.println(tile.getPosition().toString() + " has " + result + " ocean tiles.");
		return result;
	}

	/**
	 * get a tile using its position in this map only
	 * 
	 * @param pos the position of the wanted tile
	 * @return the tile that is in the passed in position
	 */
	public Tile findTileByPosition(Position pos) {
		return tiles[pos.getXCoordinate()][pos.getYCoordinate()];
	}

	/**
	 * gets the symbol for the passed in tile according to it' type of Mountains it
	 * will return M for example
	 * 
	 * @param tile the tile to get the symbol of its type
	 * @return the symbol of the type of the passed in tile
	 */
	public char getTileTypeSymbol(Tile tile) {
		if (tile instanceof MountainsTile) {
			return 'M';
		} else if (tile instanceof DesertsTile) {
			return 'D';
		} else if (tile instanceof ForestsTile) {
			return 'F';
		} else if (tile instanceof PlainsTile) {
			return 'P';
		} else if (tile instanceof OceanTile) {
			return 'O';
		}
		return 'O';
	}

	/**
	 * gets the first charcter of the owner of a tile in this map ther could be
	 * ambiguity if two owners names start off with the same letter
	 * 
	 * @param tile what tile to get the symbol of the owner for
	 * @return the first charcter of the name of the owner of a passed in tile
	 */
	public char getOwnerSymbol(Tile tile) {
		if (tile.getOwner() != null) {
			return tile.getOwner().getName().charAt(0);
		}
		return '*';
	}

	/**
	 * print out the map in an organized fashion
	 */
	public void printMap() {
		// print the header
		System.out.println("*===* " + this.name + " *===*");
		System.out.print("*    ");
		for (int i = 0; i < width; i++) {
			System.out.print(i + "      ");
		}
		System.out.println();
		// print the content of the map
		// [tileType, owner first letter, numberOfWorkers]
		char tileSymbol;
		char ownerSymbol;
		for (int j = 0; j < height; j++) {
			System.out.print(j + " ");
			for (int i = 0; i < width; i++) {
				tileSymbol = getTileTypeSymbol(tiles[i][j]);
				ownerSymbol = getOwnerSymbol(tiles[i][j]);
				System.out.print("[" + tileSymbol + ", " + ownerSymbol + "] ");
			}
			System.out.println();
		}
	}

	/**
	 * get the height of this map
	 * 
	 * @return the height of this map
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * get the width of this map
	 * 
	 * @return the width of this map
	 */
	public int getWidth() {
		return this.width;
	}

}
