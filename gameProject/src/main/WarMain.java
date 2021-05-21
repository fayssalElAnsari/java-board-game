package main;

import java.util.List;

import game.FarmGame;
import game.Game;
import game.WarGame;
import game.player.Player;
import game.util.Util;

public class WarMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Board board= new Board("test", 10, 20);
		//board.displayBoard();
		
		
		
		int choix =Util.typeGame();
		switch (choix) {
		case 1: {
			List<Player> players= Util.createWarPlayers();
			Game game= new WarGame(10, 20, players);
			((WarGame) game).start();
			break;
			}
		case 2 :{
			List<Player> players= Util.createFarmPlayers();
			Game game= new FarmGame(10, 20, players);
			((FarmGame) game).start();
			break;
			}
			
		}
		
	}
	
}
