package game;

import java.util.List;

import game.player.Player;
import game.util.Board;
import game.util.Tile;

/**
 * A class to create and manage the game
 */
public abstract class Game {
	/** the board of the game*/
	protected Board board;
	/**  the list of players in the game*/
	protected List<Player> players;
	/**
	 * public constructor of the game
	 * @param x
	 * @param y
	 */
	public Game(int x, int y) {
		this.board=new Board("test", x, y);
	}
	
	public void displayGame() {
		for(int i = 0; i<this.players.size();i++) {
			Player player=this.players.get(i);
			System.out.print( "nb tiles owned by player "+(i+1)+ " "+player.getName()+" ("+ player.getTileOwned().size()+") :");
			
			for (int j =0; j<player.getTileOwned().size();j++) {
				System.out.print("("+player.getTileOwned().get(j).getPos().getX()+" , "+player.getTileOwned().get(j).getPos().getY()+", unit:"+player.getTileOwned().get(j).getUnit()+") ");
			}
			System.out.println("");
			System.out.println("golds : "+player.getGolds());
		}
		
		this.board.displayBoard();
		System.out.println("");
	}
	/**
	 * make a new owner of tile 
	 * @param player the new owner of tile
	 * @param tile the tile 
	 */
	public void newOwner(Player player, Tile tile) {
		player.addOwnedTile(tile);
		tile.setOwner(player);
	}
	
	/**
	 * gives the winner of the game
	 */
	public abstract void resultat();
	/**
	 * check if the game is over 
	 */
	public abstract boolean isEndGame();
	
}
