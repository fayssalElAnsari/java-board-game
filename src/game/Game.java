package game;

import java.io.IOException;
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
		map = new Map("testMap", 10, 10, 1);
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
		clearConsole();
		if (this.map.noTilesLeft()) {
			this.gameEnd();
		}

	}

	public void nextRound() {
		currentRound++;
	}

	/**
	 * finding the winner by calculating the total number of points for each player
	 * the player who has the most amount of points is the winner
	 */
	public void findWinner() {
		winner = players[0];
		for (int i = 0; i < players.length - 1; i++) {
			players[i].calculateTotalPoints();
			if (players[i].getPoints() < players[i + 1].getPoints()) {
				winner = players[i + 1];
			}
		}
	}

	public void gameEnd() {
		findWinner();
		System.out.println("The winner is...");
		System.out.println(winner.getName() + " with " + winner.getPoints() + " points!!");
	}

	public abstract void startGame();

	/*
	 * in case we want to clear the console and use this game as a console game this
	 * will be used to make a constant refresh of the screen there should be a menu
	 * screen where the user can chose the game using arrows then after the creation
	 * of the map object there should be constant refresh of the screen the user
	 * will make the choice by typing the corresponding number from 1 2 or 3 if the
	 * user choses 1 a flashing brackets will appear where the user can move using
	 * the arrows pressing enter will confirm the location choice then the user will
	 * enter the army size as previously done then press enter the screen will have
	 * a constant refresh rat with a maximum limit; the fps rate should be shown on
	 * the screen also some other info about the gameplay like the inventory and
	 * round ... it would be better to implement this functionality using a game
	 * engine meaning the code will make should be reusable in the future for other
	 * projects and easier to modify in case of future improvements to the game
	 * model; there should also be a seed for generating maps which will help with
	 * doing tests for certain actions
	 */
	public static void clearConsole() {
//		final String ESC = "\033[";
//		System.out.print(ESC + "2J");

		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033\143");
			}
		} catch (IOException | InterruptedException ex) {
			System.out.println("couldn't clear console :( " + ex.getMessage());
		}

//		System.out.print("\033[H\033[2J");  
//	    System.out.flush(); 
	}

}