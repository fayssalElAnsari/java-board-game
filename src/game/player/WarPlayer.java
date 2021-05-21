package game.player;

import game.util.Tile;
import game.util.Tiletype;

/**
 * A class to create and manage the War player of the game.
 */
public class WarPlayer extends Player {
	
	/* number of War are available */
	private int nbWarAvailable;
	/* number of food that belongs to the player  */
	private int nbFood;
	
	/**
	 * public constructor for the war Player class each player starts off the game with
	 * only 35 of available warriors and 10 number of food belongs to the player
	 * 
	 * @param name the name to be given to this player
	 */	
	public WarPlayer(String name) {
		super(name);
		this.nbWarAvailable=35;
		this.nbFood=10;
		
	}
	/**
	 * get the number of warriors
	 * 
	 * @return the number of warriors
	 */	
	public int getNbWarAvailable() {
		return nbWarAvailable;
	}
	
	/**
	 * deploy the warriors 
	 * 
	 * @param the deployed warriors
	 */	
	public void deployedWar(int deployed) {
		this.nbWarAvailable=this.nbWarAvailable-deployed;
	}
	/**
	 * get the number of food belongs to the player
	 * 
	 * @return the number of food
	 */	
	public int getNbFood() {
		return nbFood;
	}
	
	/**
	 * set the number of food to the player
	 * 
	 * @param nbFood : number of food 
	 */	
	public void setNbFood(int nbFood) {
		this.nbFood = nbFood;
	}
	
	/**
	 * Set number of warriors
	 * 
	 * @param nbWarAvailable : the number of available warriors
	 */	
	public void setNbWarAvailable(int nbWarAvailable) {
		this.nbWarAvailable = nbWarAvailable;
	}
	/**
	 * Collecting food by the player 
	 * if the player get PLAIN Tile he will get +5 number of food 
	 * or +1 if he tile is WOOD Tile type
	 * 
	 * @print the total of food that player has Collected 
	 */	
	
	public void recolteFood() {
		System.out.println("gathering food");
		for(int i = 0; i<this.getTileOwned().size();i++) {
			Tile tile = this.getTileOwned().get(i);
			switch (tile.getType()){
			case PLAIN: {
				this.nbFood+=5;
				break;
			}
			case WOOD:{
				this.nbFood+=1;
				break;
			}
			}
		}
		System.out.println("total food :"+this.nbFood);
	}
	/**
	 * Distribution of food to get left food
	 * if the type tile is DESERT and number of food is less than 2 the number of units will be less with 1 unit
	 * and if the units = 0 the player will lost the tile
	 * but if the number of food bigger than 2 the number of food will be -2
	 * and in case of number of food is less than 1 and the unit in the tile > 0 he will set (unit -1)
	 * or number of food will be -1 
	 * 
	 * @print left food of the player
	 */	
	public void distribFood() {
		for(int i = 0; i<this.getTileOwned().size();i++) {
			Tile tile = this.getTileOwned().get(i);
			switch (tile.getType()){
			case DESERT: {
				for(int j =0; j<tile.getUnit();j++) {
					if(this.nbFood<2) {
						if (tile.getUnit()>0) {
							tile.setUnit(tile.getUnit()-1);
						}
						if(tile.getUnit()==0) {
							this.lostTile(tile);
							return;
						}
					}else {
						this.nbFood-=2; 
					}
				}
				break;
			}
			default :
				for(int j =0; j<tile.getUnit();j++) {
					if(this.nbFood<1) {
						if (tile.getUnit()>0) {
							tile.setUnit(tile.getUnit()-1);
						}
					}else {
						this.nbFood-=1; 
					}
				}
			}
		}
		System.out.println("food left"+this.nbFood);
	}
	

}
