package game.util.tile;

import game.util.Position;
import game.util.Tile;

public class DesertsTile extends Tile {

	public DesertsTile(Position position) {
		super(position, TileProd.SAND);
		maxNbSoldiers = 3;
	}

}
