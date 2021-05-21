package game.character;
/**
 * A class generate the units in the fargame
 */
public class WarUnit implements Unit {
	private int golds;
	/**
	 * A public constructor for the war units 
	 */
	public WarUnit() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * get the number of golds
	 * @return the number of golds
	 */
	@Override
	public int getGolds() {
		// TODO Auto-generated method stub
		return this.golds;
	}
	/**
	 * set golds 
	 * @param golds set the golds
	 * @return golds the number of golds
	 */
	@Override
	public void setGolds(int golds) {
		// TODO Auto-generated method stub
		this.golds=golds;
	}
}
