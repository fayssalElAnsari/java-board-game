package game.util;

/**
 * A class to create and manage the position .
 */
public class Position {
	
	private int x;
	private int y;
	/**
	 * the constructor of position 
	 * @param x : the x of the position
	 * @param y : the y of position
	 */
	public Position(int x,int y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * get the x of the  position 
	 * @return x : the x of the position
	 */
	public int getX() {
		return x;
	}
	/**
	 * get the y of the  position  
	 * @return y : the y of position
	 */
	public int getY() {
		return y;
	}
	
}
