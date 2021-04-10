package game.util.tile;

import game.util.Position;
import game.util.Tile;

public class OceanTile extends Tile {

	public OceanTile(Position position) {
		super(position, TileProd.NONE);
		typeSymbol = 'O';
		colorCodeFg = "\033[34m";
		colorCodeBg = "\033[44m";
		isOcean = true;
	}

}