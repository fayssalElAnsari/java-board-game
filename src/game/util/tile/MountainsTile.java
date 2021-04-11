package game.util.tile;

import game.util.Position;
import game.util.Tile;

public class MountainsTile extends Tile {

	public MountainsTile(Position position) {
		super(position, TileProd.ROCK);
		maxNbSoldiers = 3;
		typeSymbol = 'M';
		colorCodeFg = "\033[97m";
		colorCodeBg = "\033[100m";
		bonusAttackPoints = 2;
		bonusPoints = 4;
	}

}