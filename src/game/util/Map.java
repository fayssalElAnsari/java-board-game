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
				if (ratio <= (double) 2 / (double) 3) {
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
	 * @param tile the tile to check its neighbours
	 * @return the number of adjacent ocean tiles
	 */

	public int getNumberOfAdjacentOceans(Tile tile) {
		int result = 0;
		int x = tile.getPosition().getXCoordinate();
		int y = tile.getPosition().getYCoordinate();

		for (int i = x - 1; i < x + 2; i++) {
			for (int j = y - 2; j < y + 2; j++) {
				if ((Math.abs(i - x) + Math.abs(j + y)) % 2 != 0) {
					try {
						if (tiles[i][j] instanceof OceanTile) {
							result++;
						}
					} catch (Exception e) {

					}
				}
			}
		}

		System.out.println(tile.getPosition().toString() + " has " + result + " ocean tiles.");
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
					tileSymbol = getTileTypeSymbol(tiles[i][j]);
					ownerSymbol = getOwnerSymbol(tiles[i][j]);
					// sets the color symbol for every tile type
					if (tiles[i][j] instanceof OceanTile) {
						colorCodeFg = "\033[34m";
						colorCodeBg = "\033[44m";
					}
					if (tiles[i][j] instanceof DesertsTile) {
						colorCodeFg = "\033[30m";
						colorCodeBg = "\033[103m";
					}
					if (tiles[i][j] instanceof ForestsTile) {
						colorCodeFg = "\033[97m";
						colorCodeBg = "\033[102m";
					}
					if (tiles[i][j] instanceof MountainsTile) {
						colorCodeFg = "\033[97m";
						colorCodeBg = "\033[100m";
					}
					if (tiles[i][j] instanceof PlainsTile) {
						colorCodeFg = "\033[30m";
						colorCodeBg = "\033[107m";
					}
					if (tiles[i][j].getOwner() == GameMain.game.activePlayer) {// bold;underlined
						colorCodeFg = "\033[1;4m";
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
					tileSymbol = getTileTypeSymbol(tiles[i][j]);
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

	/**
	 * this method creates a valid regex to evaluate the validity of a user input
	 * the coordinates chosen by the user should be valid; meaning the tile chosen
	 * should actually exist on this map object and not be null this is long from
	 * being a perfect regex generator for a coordinate system but it's perfectly
	 * functional for a (99,99) coordinate system
	 * 
	 * @return the valid regex for this map object tiles positions
	 */
//	public String createValidCoordinateRegex() {
//		String regex = "";
//
//		int hNbRep, hFirstLimit, hSecondLimit;
//		int wNbRep, wFirstLimit, wSecondLimit;
//	
//		hSecondLimit = (this.getHeight() - 1) / 10;
//		hFirstLimit = (this.getHeight() - 1) % 10;
//
//		wSecondLimit = (this.getWidth() - 1) / 10;
//		wFirstLimit = (this.getWidth() - 1) % 10;
//
//		regex = "^[0-" + hSecondLimit + "]{0," + hSecondLimit + "}[0-" + hFirstLimit + "],[0-" + wSecondLimit + "]{0,"
//				+ wSecondLimit + "}[0-" + wFirstLimit + "]$";
//		System.out.println(regex);
//		return regex;
//	}

}