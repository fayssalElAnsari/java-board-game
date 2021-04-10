package game;

import java.util.Random;
import java.util.Scanner;

import game.character.Player;
import game.util.Map;

/**
 * 
 * @author fayss
 *
 */
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
		map = new Map("testMap", 10, 10);
		createPlayers();
		// chose the first player in a random way
		Random r = new Random();
		int n = r.nextInt(players.length);
		activePlayer = players[n];
	}

	/**
	 * create the players objects and populate the players array with them this
	 * method isn't defined here because we need to make for every game type the
	 * appropriate player type
	 */
	public abstract void createPlayers();

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

	public abstract void startGame();

}