package game.util;

import java.util.Random;

import game.GameMain;
import game.util.tile.DesertsTile;
import game.util.tile.ForestsTile;
import game.util.tile.MountainsTile;
import game.util.tile.OceanTile;
import game.util.tile.PlainsTile;

/**
 * now I will try to
 * 
 * @author fayss
 *
 */
public class Map {
	private Tile[][] tiles;
	private String name;
	private int width;
	private int height;
	private boolean colorEnabled = true;

	/**
	 * public constructor of the map
	 * 
	 * @param name   the name of the map
	 * @param width  the width of the map
	 * @param height the height of the map
	 */
	public Map(String name, int width, int height) {
		int nbOceans = 0;
		int nbNonOceans = 0;
		int totalTiles = width * height;
		this.name = name;
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];

		Random r = new Random();
		double ratio = (double) nbOceans / (double) nbNonOceans;// div by 0?
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				ratio = (double) nbOceans / (double) nbNonOceans;
				int n = r.nextInt(5);
				if (ratio <= ((double) 2 / (double) 3)) {
					tiles[i][j] = new OceanTile(new Position(i, j));
					nbOceans++;
				} else {
					n = r.nextInt(5);
					if (n == 0) {
						tiles[i][j] = new MountainsTile(new Position(i, j));// position redundancy??
						nbNonOceans++;
					} else if (n == 1) {
						tiles[i][j] = new PlainsTile(new Position(i, j));// position redundancy??
						nbNonOceans++;
					} else if (n == 2) {
						tiles[i][j] = new DesertsTile(new Position(i, j));// position redundancy??
						nbNonOceans++;
					} else if (n == 3) {
						tiles[i][j] = new ForestsTile(new Position(i, j));// position redundancy??
						nbNonOceans++;
					} else if (n == 4) {
						tiles[i][j] = new OceanTile(new Position(i, j));// position redundancy??
						nbOceans++;
					}
				}

			}
		}
		// nuking the f islands
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (getNumberOfAdjacentOceans(tiles[i][j]) >= 4) {
					tiles[i][j] = new OceanTile(new Position(i, j));// position redundancy??
					nbOceans++;
//					System.out.println("island NUKED!!");
				}
			}
		}
		ratio = (double) nbOceans / (double) nbNonOceans;
		System.out.println("the ratio of oceans to non oceans is: " + ratio + "; Total=" + totalTiles + "; Oceans="
				+ nbOceans + "; NonOceans=" + nbNonOceans);
		System.out.println(ratio > ((double) 2 / (double) 3));

	}

	/**
	 * 
	 * check the tile to the top, left, down, right if three of these tile are ocean
	 * then the next random choice shouldn't have ocean in it to check we could
	 * increment a number from 0 whenever we find an ocean tile so when the number
	 * is equal to 3 the new tile choice pool shouldn't contain ocean tile there are
	 * also 4 cases where there could be only 3 adjacent tiles that is when the
	 * current tile is in the edges of the map so when we are on the edges we should
	 * start out by one and another 4 cases that is the corners with 2 starting
	 * ocean tiles meaning we will assume that everything that isn't showing on the
	 * map is an ocean the easiest way to do this is to actually surround the map
	 * with ocean tiles like the teacher did for now we will do the EZ way
	 * 
	 * @param tile the tile to check its neighbors
	 * @return the number of adjacent ocean tiles
	 */

	public int getNumberOfAdjacentOceans(Tile tile) {
		int result = 0;
		int x = tile.getPosition().getXCoordinate();
		int y = tile.getPosition().getYCoordinate();
		try {
			if (tiles[x - 1][y].isOcean()) {
				result++;
			}
		} catch (Exception e) {
			result++;
		}
		try {
			if (tiles[x + 1][y].isOcean()) {
				result++;
			}
		} catch (Exception e) {
			result++;
		}
		try {
			if (tiles[x][y - 1].isOcean()) {
				result++;
			}
		} catch (Exception e) {
			result++;
		}
		try {
			if (tiles[x][y + 1].isOcean()) {
				result++;
			}
		} catch (Exception e) {
			result++;
		}

//		System.out.println(tile.getPosition().toString() + " has " + result + " ocean tiles.");
		return result;
	}

	/**
	 * same as getNumberOfAdjacentOceans() but with checking all 9 neighbors this
	 * time
	 * 
	 * @param tile
	 * @return
	 */
	public int getNumberOfAdjacentOceans2(Tile tile) {
		int result = 0;
		int x = tile.getPosition().getXCoordinate();
		int y = tile.getPosition().getYCoordinate();
		for (int i = x - 1; i < x + 2; i++) {
			for (int j = y - 2; j < y + 2; j++) {
				try {
					if (!(i == x && j == y)) {
						if (tiles[i][j] instanceof OceanTile) {
							result++;
						}
					}
				} catch (Exception e) {

				}

			}
		}
//		System.out.println(tile.getPosition().toString() + " has " + result + " ocean tiles.");
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
		System.out.print("     ");
		for (int i = 0; i < width; i++) {
			System.out.print(String.format("%02d", i) + "    ");
		}
		System.out.println();
		if (colorEnabled) {
			// print the content of the map
			// [tileType, owner first letter, numberOfWorkers]
			char tileSymbol;
			char ownerSymbol;
			String colorCodeFg = "";
			String colorCodeBg = "";
			for (int j = 0; j < height; j++) {
				System.out.print(String.format("%02d", j) + " ");
				for (int i = 0; i < width; i++) {
					tileSymbol = tiles[i][j].getTypeSymbol();
					ownerSymbol = getOwnerSymbol(tiles[i][j]);
					// sets the color symbol for every tile type
					colorCodeFg = tiles[i][j].colorCodeFg;
					colorCodeFg = tiles[i][j].colorCodeBg;
					if (tiles[i][j].getOwner() == GameMain.game.activePlayer) {// bold;underlined
						colorCodeFg = "\033[1;4;37m";
					}
					if (tiles[i][j].getOwner() == null) {
//						colorCodeFg = colorCodeBg;
						System.out.print(colorCodeBg + colorCodeFg + "      " + "\033[0m");
					} else {
						System.out
								.print(colorCodeBg + colorCodeFg + "[" + tileSymbol + ", " + ownerSymbol + "]\033[0m");
					}
				}
				System.out.println();
			}
		} else {
			// print the content of the map
			// [tileType, owner first letter, numberOfWorkers]
			char tileSymbol;
			char ownerSymbol;
			for (int j = 0; j < height; j++) {
				System.out.print(j + " ");
				for (int i = 0; i < width; i++) {
					tileSymbol = tiles[i][j].getTypeSymbol();
					ownerSymbol = getOwnerSymbol(tiles[i][j]);
					System.out.print("[" + tileSymbol + ", " + ownerSymbol + "] ");
				}
				System.out.println();
			}
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