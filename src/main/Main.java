package main;

import java.util.List;

import game.FarmGame;
import game.Game;
import game.WarGame;
import game.player.Player;
import game.util.Util;

/**
 * The Main class for users to play a WarGame or a FarmGame .
 */

public class Main {
	 /**
	    * Main function to interact with a computer to play a game of shifumi by a control terminal.
	    * @param args Arguments given by the user in the control terminal.
	    * @exception NumberFormatException Only integer are authorized otherwise that exception is triggered.
	    */
	/**
	 * Main function to interact between players to play a war game (case1) or a farm game (case2)
	 * @param Arguments given by the user in the control terminal.
	 * 
	 */
	public static void main(String[] args) {

				
		int choix =Util.typeGame();
		switch (choix) {
		case 1: {
			List<Player> players= Util.createWarPlayers();
			Game game= new WarGame(10, 10, players);
			((WarGame) game).start();
			break;
			}
		case 2 :{
			List<Player> players= Util.createFarmPlayers();
			Game game= new FarmGame(10, 10, players);
			((FarmGame) game).start();
			break;
			}
			
		}
		
	}
}
