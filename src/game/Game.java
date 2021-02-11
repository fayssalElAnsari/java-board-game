package game;

import java.util.Random;
import java.util.Scanner;

import game.util.ActionPlayer;
import game.util.Map;
import game.util.Position;

public class Game {
	// private static final int winPoints = 1;
	// private static final int drawPoints = 0;
	// private static final int losePoints = -1;

	private Map map;
	private Player activePlayer;

	private Player[] players;
	private Player winner;
	private int nbRounds = 6;
	private int currentRound = 1;
	Scanner scanner = new Scanner(System.in);

	/**
	 * public constructor for the game class
	 */
	public Game() {
		map = new Map("testMap", 5, 5);
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
		players = new Player[4];
		// will make this using user input later
		// setting names
		players[0] = new Player("fayssal");
		players[1] = new Player("aya");
		players[2] = new Player("mehdi");
		players[3] = new Player("ziko");

		// setting up corners positions
		Position[] corners = new Position[4];
		corners[0] = new Position(0, map.getHeight() - 1);
		corners[1] = new Position(map.getWidth() - 1, map.getHeight() - 1);
		corners[2] = new Position(map.getWidth() - 1, 0);
		corners[3] = new Position(0, 0);
		for (int i = 0; i < corners.length; i++) {
			players[i].setStartingTile(this.map.findTileByPosition(corners[i]));
		}

	}

	/**
	 * 
	 */
	public void startGame() {
		while (this.getCurrentRound() <= this.getnbRounds()) {
			for (int i = 0; i < players.length; i++) {
				System.out.println("ROUND: " + currentRound + " OF " + nbRounds);
				showStats();
				this.getMap().printMap();
				System.out.println("It's " + activePlayer.getName() + "\'s turn: ");
				activePlayer.printOutInventory();
				System.out.println("1 => DEPLOY; 2 => EXCHANGE; 3 => SKIP");
				System.out.print("make your choice :> ");
				String choiceOf3 = scanner.nextLine();
				makeChoice(choiceOf3);
				nextTurn();
			}
			this.nextRound();
		}
		this.gameEnd();
	}

	/**
	 * 
	 */
	public void showStats() {
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].getName() + " has " + players[i].getGold() + " gold; and "
					+ players[i].getNumberOfWorkers() + " workers.");
		}
	}

	/**
	 * a player can make one of 3 choices 1: deploy a worker 2: exchange inventory
	 * resources for gold 3: skip round and get 1 gold for it
	 * 
	 * @param line the choice made by the user
	 */
	private void makeChoice(String line) {
		if (line.equals("1")) {
			activePlayer.setLastAction(ActionPlayer.DEPLOY);
			/**
			 * should check if player has some workers already if not he should buy if he
			 * does have wokers already he shoul chose which worker to move and where to
			 * move him to if the move is successful it's the end of the player's turn if
			 * it's not succesful he has to chose a worker again...
			 */
			if (!activePlayer.hasWorkers()) {
				activePlayer.buyWorker();
			}
			activePlayer.printOutWorkersList();
			System.out.print("chose a worker :> ");
			int workerIndex = Integer.parseInt(scanner.nextLine());
			System.out.print("chose a position :> ");
			String position = scanner.nextLine();
			Position newPos = new Position(Integer.parseInt(position.split(",")[0]),
					Integer.parseInt(position.split(",")[1]));
		} else if (line.equals("2")) {
			activePlayer.setLastAction(ActionPlayer.EXCHANGE);
			// String choiceOf3 = scanner.nextLine();
			activePlayer.sellResources();
		} else if (line.equals("3")) {
			activePlayer.setLastAction(ActionPlayer.NOTHING);
			// String choiceOf3 = scanner.nextLine();
			activePlayer.skipRound();
		} else {
			// System.out.println("1 => DEPLOY; 2 => EXCHANGE; 3 => SKIP");
			System.out.println("It's " + activePlayer.getName() + "\'s turn>");
			String choiceOf3 = scanner.nextLine();
			makeChoice(choiceOf3);
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

}