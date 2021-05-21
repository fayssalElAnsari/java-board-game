package main;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import game.WarGame;
import game.player.Player;
import game.player.WarPlayer;

public class WarMain {

	public static void main(String[] args) {
		int i;
		List<Player> players= new ArrayList<>();
		for( i = 0; i < args.length; i++) {
			players.add(new WarPlayer(args[i]));
		}
		Game game= new WarGame(10, 10, players);
		((WarGame) game).start();
	}
	
}
