package game;

import java.util.Random;
import java.util.Scanner;

import game.character.Player;
import game.util.ActionPlayer;
import game.util.Map;
import game.util.Position;

public abstract class Game {
	// private static final int winPoints = 1;
	// private static final int drawPoints = 0;
	// private static final int losePoints = -1;

	Scanner scanner = new Scanner(System.in);
	private Map map;
	public Player activePlayer;

	public Player[] players;
	private Player winner;
	public int nbRounds = 10;
	public int currentRound = 1;

	boolean debugMode = true;

	/**
	 * public constructor for the game class
	 */
	public Game() {
		map = new Map("testMap", 8, 8);
		createPlayers();
		// chose the first player in a random way
		Random r = new Random();
		int n = r.nextInt(players.length);
		activePlayer = players[n];
	}

	/**
	 * create the players objects and populate the players array with them
	 */
	public void createPlayers() {
		if (debugMode){
			players = new Player[4];
			// setting names
			players[0] = new Player("fayssal");
			players[1] = new Player("aya");
			players[2] = new Player("mehdi");
			players[3] = new Player("ziko");
		} else {
			System.out.println("Possible number of players between 2 and 4");
			System.out.println("Chose number of players :>");
			int nbPlayers = Integer.parseInt(scanner.nextLine());
			if (nbPlayers >= 2 && nbPlayers <= 4) {
				players = new Player[nbPlayers];
				// will make this using user input later
				// setting names
				String playerName;
				for (int i = 0; i < nbPlayers; i++) {
					System.out.println("Enter name of player nb " + i + 1 + " :> ");
					playerName = scanner.nextLine();
					players[i] = new Player(playerName);
				}
			} else {
				createPlayers();
			}
		}
	}


	public Map getMap() {
		return this.map;
	}

	public int getnbRounds() {
		return this.nbRounds;
	}

	public int getCurrentRound() {
		return this.currentRound;
	}

	/**
	 * IN EACH ROUND THERE ARE AS MUCH TURNSS AS THE NUMBER OF PLAYERS
	 */
	public void nextTurn() {
		// find the next player
		int n = -1;
		this.activePlayer.nextTurn();
		for (int i = 0; i < players.length; i++) {
			if (activePlayer == players[i]) {
				n = i;
			}
		}
		activePlayer = players[(n + 1) % players.length];
	}

	public void nextRound() {
		currentRound++;
	}

	public void findWinner() {
		winner = players[0];
		for (int i = 0; i < players.length - 1; i++) {
			if (players[i].getGold() < players[i + 1].getGold()) {
				winner = players[i + 1];
			}
		}
	}

	public void gameEnd() {
		findWinner();
		System.out.println("The winner is...");
		System.out.println(winner.getName() + " with " + winner.getGold() + " points!!");
	}

	public void startGame() {
		// will be in wargame or farmgame
	}

}