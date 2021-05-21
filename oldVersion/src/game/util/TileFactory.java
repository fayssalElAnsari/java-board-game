package game.util;

import game.util.tile.DesertsTile;
import game.util.tile.ForestsTile;
import game.util.tile.MountainsTile;
import game.util.tile.OceanTile;
import game.util.tile.PlainsTile;

public class TileFactory {

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

}
