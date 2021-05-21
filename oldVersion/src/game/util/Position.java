package game.util;

/**
 * 
 * @author fayss
 *
 */
public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public int getXCoordinate() {
		return this.x;
	}

	public int getYCoordinate() {
		return this.y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setXCoordinate(int x) {
		this.x = x;
	}

	public void setYCoordiante(int y) {
		this.y = y;
	}

	/**
	 * 
	 * move to a direction by 1 tile
	 * 
	 * @param c the direction to move to
	 */
	public void move(char c) {
		if (c == 'N' || c == 'n') {
			this.y = this.y + 1;
		} else if (c == 'S' || c == 's') {
			this.y = this.y - 1;
		} else if (c == 'E' || c == 'e') {
			this.x = this.x + 1;
		} else if (c == 'W' || c == 'w') {
			this.x = this.x - 1;
		} else {
			System.out.println("the direction entered is invalid!");
		}
	}

	/**
	 * 
	 * move to a direction by n tiles
	 * 
	 * @param c the direction to move to
	 * @param n the number of tiles to traverse
	 */
	public void move(char c, int n) {
		if (c == 'N' || c == 'n') {
			this.y = this.y + n;
		} else if (c == 'S' || c == 's') {
			this.y = this.y - n;
		} else if (c == 'E' || c == 'e') {
			this.x = this.x + n;
		} else if (c == 'W' || c == 'w') {
			this.x = this.x - n;
		} else {
			System.out.println("the direction entered is invalid!");
		}
	}

	public double calculateDistance(Position position) {
		return Math.sqrt(
				Math.pow(this.x - position.getXCoordinate(), 2) + (Math.pow(this.y - position.getYCoordinate(), 2)));
	}

}