package game;

import game.util.ActionPlayer;
import game.util.Position;

public class WarGame extends Game {

	public WarGame() {
			super();
			// TODO Auto-generated constructor stub
			
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
					+ players[i].getNumberOfWorkers() + " soldiers.");
		}
	}

}