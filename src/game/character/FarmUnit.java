package game.character;

/**
 * A class generate the units in the fargame
 */
public class FarmUnit implements Unit{
	/** number of golds */
	private int golds;
	/**
	 * A public constructor for the Farm units initialized with 0 gold
	 */
	public FarmUnit() {
		this.golds=0;
	}
	/**
	 * get the number of golds
	 * @return the number of golds
	 */
	@Override
	public int getGolds() {
		return this.golds;
	}
	/**
	 * set golds 
	 * @param golds set the golds
	 * @return golds the number of golds
	 */
	@Override
	public void setGolds(int golds) {
		this.golds=golds;
	}

}
