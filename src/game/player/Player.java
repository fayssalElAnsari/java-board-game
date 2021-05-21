package game.player;

import java.util.ArrayList;
import java.util.List;

import game.util.Tile;

/**
 * A class to create and manage the player of the game.
 */

public abstract class Player {
	/*The name of player*/
	private String name;
	/*The number of golds of the player */
	private int golds;
	 /*A list of owned tiles by the player*/
	private List<Tile> tileOwned;
	
	/**
	 * public constructor for the Player class each player starts off the game with
	 * 0 gold pieces the last action performed by this player is nothing (or skip)
	 * each player will get his own tile
	 * @param name the name to be given to this player
	 */	
	public Player(String name) {
		/*The name of player*/
		this.name=name;
		 /*The number of golds initialize with 0  */
		this.golds=0;
		 /*Array of tile owned by the player*/
		this.tileOwned = new ArrayList<Tile>();
		
	}
	/**
	 * add the Gold
	 * 
	 * @param toadd the number of golds added
	 */	
	public void addGolds(int toadd) {
		this.golds=this.golds+toadd;
	}
	
	/**
	 * adding the tile to be owned by the player
	 * 
	 * @param tile the tile will get owned by the player
	 */	
	public void addOwnedTile(Tile tile) {
		this.tileOwned.add(tile);
	}
	
	/**
	 * get the number of Golds
	 * 
	 * @return number of golds that the player own 
	 */	
	public int getGolds() {
		return golds;
	}
	
	/**
	 * set the number of Golds
	 * 
	 * @param the number of golds to be set 
	 */	
	public void setGolds(int golds) {
		this.golds = golds;
	}
	
	/**
	 * get the name of the player
	 * 
	 * @return the name of the player
	 */	
	public String getName() {
		return name;
	}
    
	/**
	 * create the list of tiles 
	 * 
	 * @return the tile owned by the player
	 */
	public List<Tile> getTileOwned() {
		return tileOwned;
	}
	
	/**
	 * lose tile if the other player owned the tile
	 * @param the tile
	 * @return the tile owned by the player
	 */	
	public void lostTile(Tile tile) {
		for(int i = 0; i <tileOwned.size();i++) {
			if(tile==tileOwned.get(i)) {
				System.out.println("tile lost");
				tileOwned.get(i).setOwner(null);
				tileOwned.remove(i);
			}
		}
	}
	
	
	
}
