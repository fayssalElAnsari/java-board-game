package game;

import game.character.PlayerWar;
import game.util.ActionPlayer;
import game.util.Position;

public class WarGame extends Game {

	public WarGame() {
			super();
			// TODO Auto-generated constructor stub
			
	}

	/**
	 * create the players objects and populate the players array with them
	 */
	public void createPlayers() {
		if (debugMode){
			players = new PlayerWar[4];
			// setting names
			players[0] = new PlayerWar("fayssal");
			players[1] = new PlayerWar("aya");
			players[2] = new PlayerWar("mehdi");
			players[3] = new PlayerWar("ziko");
		} else {
			System.out.println("Possible number of players between 2 and 4");
			System.out.println("Chose number of players :>");
			int nbPlayers = Integer.parseInt(scanner.nextLine());
			if (nbPlayers >= 2 && nbPlayers <= 4) {
				players = new PlayerWar[nbPlayers];
				// will make this using user input later
				// setting names
				String playerName;
				for (int i = 0; i < nbPlayers; i++) {
					System.out.println("Enter name of player nb " + i + 1 + " :> ");
					playerName = scanner.nextLine();
					players[i] = new PlayerWar(playerName);
				}
			} else {
				createPlayers();
			}
		}
	}

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

	private void makeChoice(String line) {
		if (line.equals("1")) {
			activePlayer.setLastAction(ActionPlayer.DEPLOY);
			/**
			 * should check if player has some workers already if not he should buy if he
			 * does have wokers already he shoul chose which worker to move and where to
			 * move him to if the move is successful it's the end of the player's turn if
			 * it's not succesful he has to chose a worker again...
			 */
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

	
		/**
	 * 
	 */
	public void showStats() {
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].getName() + " has " + players[i].getGold() + " gold; and "
					+ players[i].getSoldiers() + " soldiers.");
		}
	}

}