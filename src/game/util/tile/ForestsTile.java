package game.util.tile;

import game.util.Position;
import game.util.Tile;

public class ForestsTile extends Tile {

	public ForestsTile(Position position) {
		super(position, TileProd.WOOD);
		typeSymbol = 'F';
		colorCodeFg = "\033[97m";
		colorCodeBg = "\033[102m";
		bonusPoints = 2;
		turnSalary = 1;
	}

}