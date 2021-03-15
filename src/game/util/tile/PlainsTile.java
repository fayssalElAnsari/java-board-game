package game.util.tile;

import game.util.Position;
import game.util.Tile;

public class PlainsTile extends Tile {

	public PlainsTile(Position position) {
		super(position, TileProd.CORN);
	}

}