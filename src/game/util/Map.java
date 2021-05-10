package game.util;

import java.util.Random;

import game.GameMain;
import game.util.tile.DesertsTile;
import game.util.tile.ForestsTile;
import game.util.tile.MountainsTile;
import game.util.tile.OceanTile;
import game.util.tile.PlainsTile;

/**
 * @author fayssal
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
	public Map(String name, int width, int height, int seed) {
		this.name = name;
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		int totalTiles = width * height;
		double ratioLimit = 2;// 2 car c'est (2/3)/(1/3)
		int nbOceans = 0;
		int nbNonOceans = 0;
		double ratio;
		Random r = new Random();

		if (seed == 1) {// start off by by making the two thirds full of oceans then random tiles
						// including oceans since it's >= 2/3 oceans and not strictly > 2/3
			ratioLimit = (double) 2 / (double) 3;
			int n;

			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {
					ratio = (double) nbOceans / totalTiles;
					if (ratio < ratioLimit) {// Haven't reached the wanted ratio limit yet so keep making oceans
						tiles[i][j] = new OceanTile(new Position(i, j));
						nbOceans++;
					} else {// Reached the wanted ratio therefore make random tile types
						n = r.nextInt(5);
						tiles[i][j] = chooseRandomTileType(i, j, n);
						if (tiles[i][j].isOcean) {
							nbOceans++;
						} else {
							nbNonOceans++;
						}
					}
				}
			}
		} else if (seed == 2) {// put an island of random tile in the middle surrounded by oceans tiles only
			int minX = 0;
			int minY = 0;
			int maxX = this.getWidth() - 1;
			int maxY = this.getHeight() - 1;
			int i = 0;
			int j = 0;
			int repetitions = this.getHeight() / 2;
			int n = 0;
			ratio = (double) nbOceans / totalTiles;
			int xDirection = 1;
			int yDirection = 0;

			// we will start off by the first (0,0) tile then go right then enter the loop
			// after defining the directions
			tiles[0][0] = new OceanTile(new Position(i, j));
			i = 1;
			while (ratio < ratioLimit) {
				// reached the limit going right (will go down)
				System.out.println("the diff is: i(" + (i - maxX) + "); j(" + (j - minY) + ")");
				if (i == maxX && j == minY) {
					System.out.println(" !! will go down");
					xDirection = 0;
					yDirection = 1;
					i--;
					maxX--;
				}
				// reached the limit going down (will go left)
				if (j == maxY && i == maxX) {
					System.out.println(" !! will go left");
					xDirection = -1;
					yDirection = 0;
					maxY--;
				}
				// reached the limit going left (will go up)
				if (i == minX && j == maxY) {
					System.out.println(" !! will go up");
					xDirection = 0;
					yDirection = -1;
					minX++;
				}
				// reached the limit going up (will go right)
				if (j == minY && i == minY) {
					System.out.println(" !! will go right");
					xDirection = 1;
					yDirection = 0;
					minY++;
				} else {
					System.out.println(minX + "," + maxX + " ; " + minX + "," + maxX);
				}
				i = i + xDirection;
				j = j + yDirection;
				tiles[i][j] = new OceanTile(new Position(i, j));
				nbOceans++;
				ratio = (double) nbOceans / totalTiles;
				System.out.println(tiles[i][j].toString() + " " + ratio);

			}
			System.out.println("Got out of the loop!");

			for (int j2 = 0; j2 < height; j2++) {
				for (int i2 = 0; i2 < width; i2++) {
					try {
						if (!tiles[i2][j2].isOcean) {
							ratio = (double) nbOceans / totalTiles;
							n = r.nextInt(5);
							System.out.println(tiles[i2][j2].toString());
							tiles[i2][j2] = chooseRandomTileType(i2, j2, n);

						}
						if (tiles[i2][j2].isOcean) {
							nbOceans++;
						} else {
							nbNonOceans++;
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}

			maxX = maxX - 1;

		} else if (seed == 3)

		{// Divide map into sections and alternate between making a section strictly not
			// ocean or ocean

		} else if (seed == 4) {// a combination of mode 2 and 3 by making a couple of islands inside sections
								// surrounded by oceans

		}

		// nuking the f islands
		// this part is independent from the chosen mode since it's another important
		// condition that no single tile island exists
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (getNumberOfAdjacentOceans(tiles[i][j]) == 4 && !tiles[i][j].isOcean) {
					tiles[i][j] = new OceanTile(new Position(i, j));
					nbOceans++;
					nbNonOceans--;
//								System.out.println("island NUKED!!");
				}
			}
		}

		ratio = (double) nbOceans / (double) totalTiles;
		System.out.println("Ocean tiles take around: " + ratio + " of the total map; Total=" + totalTiles + "; Oceans="
				+ nbOceans + "; NonOceans=" + nbNonOceans);
		System.out.println(ratio > ratioLimit);

	}

	/**
	 * choose a random tile type for a tile and return it
	 * 
	 * @param i
	 * @param j
	 * @param n
	 * @return
	 */
	public Tile chooseRandomTileType(int i, int j, int n) {
		Tile tile;
		if (n == 0) {
			tile = new MountainsTile(new Position(i, j));
		} else if (n == 1) {
			tile = new PlainsTile(new Position(i, j));
		} else if (n == 2) {
			tile = new DesertsTile(new Position(i, j));
		} else if (n == 3) {
			tile = new ForestsTile(new Position(i, j));
		} else {
			tile = new OceanTile(new Position(i, j));
		}
		return tile;
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
						if (tiles[i][j].isOcean()) {
							result++;
						}
					}
				} catch (Exception e) {

				}

			}
		}
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
					ownerSymbol = tiles[i][j].getOwnerSymbol();
					// sets the color theme for every tile type
					colorCodeFg = tiles[i][j].colorCodeFg;
					colorCodeFg = tiles[i][j].colorCodeBg;
					if (tiles[i][j].getOwner() == GameMain.game.activePlayer) {// bold;underlined
						colorCodeFg = "\033[1;4;37m";
					}
					if (tiles[i][j].getOwner() == null) {
//						colorCodeFg = colorCodeBg;
						System.out.print(colorCodeBg + colorCodeFg + "      " + "\033[0m");// empty tiles have no text
																							// (6 spaces)
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
					ownerSymbol = tiles[i][j].getOwnerSymbol();
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

	/**
	 * check to see if there are no tiles left to be colonized in this map
	 * 
	 * @return false if there are tiles left; true if the whole map has been
	 *         colonized
	 */
	public boolean noTilesLeft() {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (!tiles[i][j].isOcean && tiles[i][j].getOwner() == null) {
//					System.out.println("there ARE tiles left to be taken in this map :)");
					return false;
				}
			}
		}
		System.out.println("there are no tiles left to be taken in this map :(");
		return true;
	}

}