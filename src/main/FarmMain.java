package main;

import java.util.List;

import game.FarmGame;
import game.Game;
import game.WarGame;
import game.player.Player;
import game.util.Util;

public class FarmMain {
	
	public static void main(String[] args) {
		//Board board= new Board("test", 10, 20);
		//board.displayBoard();
		List<Player> players= Util.createWarPlayers();
		Game game= new WarGame(10, 20, players);
		((WarGame) game).start();
	}

}
