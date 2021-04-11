package game.util.tile;

import game.util.Position;
import game.util.Tile;

public class PlainsTile extends Tile {

	public PlainsTile(Position position) {
		super(position, TileProd.CORN);
		typeSymbol = 'P';
		colorCodeFg = "\033[30m";
		colorCodeBg = "\033[107m";
		bonusPoints = 1;
	}

}