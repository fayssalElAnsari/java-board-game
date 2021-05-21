package main;

import java.util.ArrayList;
import java.util.List;

import game.FarmGame;
import game.Game;
import game.WarGame;
import game.player.FarmPlayer;
import game.player.Player;
import game.util.Util;

public class FarmMain {
	
	public static void main(String[] args) {
		int i;
		List<Player> players= new ArrayList<>();
		for( i = 0; i < args.length; i++) {
			players.add(new FarmPlayer(args[i]));
		}
		Game game= new FarmGame(10, 10, players);
		((FarmGame) game).start();
	}

}
