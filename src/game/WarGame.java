package game;

import game.character.PlayerWar;
import game.util.ActionPlayer;
import game.util.Position;
import game.util.Tile;
import game.util.tile.OceanTile;

public class WarGame extends Game {

	public WarGame() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * create the players objects and populate the players array with them
	 */
	public void createPlayers() {
		if (debugMode) {
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
			 * should check if player has some units left if not he should buy if he does
			 * have workers already he should chose which worker to move and where to move
			 * him to if the move is successful it's the end of the player's turn if it's
			 * not successful he has to chose a worker again...
			 */
			System.out.print("chose a position :> ");
			String position = scanner.nextLine();
			if (!position.matches("[0-9]+,[0-9]+")) {
				System.out.print("/!\\ Please enter a valid coordinate like: 1,1 \n");
				makeChoice("1");
				return;
			}
			Position newPos = new Position(Integer.parseInt(position.split(",")[0]),
					Integer.parseInt(position.split(",")[1]));
			if (newPos.getXCoordinate() < 0 || newPos.getXCoordinate() > this.getMap().getWidth() - 1
					|| newPos.getYCoordinate() < 0 || newPos.getYCoordinate() > this.getMap().getHeight() - 1) {
				System.out.print("/!\\ The tile: " + newPos.toString() + " does not exist on this map XD \n");
				makeChoice("1");
				return;
			}
			if (this.getMap().findTileByPosition(newPos) instanceof OceanTile) {
				System.out.println(" [NOTE]: Please chose another tile this one will [~]drown[~] your army XD");
				makeChoice("1");
			} else if (this.getMap().findTileByPosition(newPos).checkIfEmpty()) {
				System.out.println("/!\\ This tile isn't empty unfortunately, please chose another one.");
				makeChoice("1");
			} else {
				choseArmySize(newPos);

			}

		} else if (line.equals("2")) {
			activePlayer.setLastAction(ActionPlayer.EXCHANGE);
			// String choiceOf3 = scanner.nextLine();
			activePlayer.sellResources();
		} else if (line.equals("3")) {
			activePlayer.setLastAction(ActionPlayer.NOTHING);
			// String choiceOf3 = scanner.nextLine();
			activePlayer.skipRound();
		} else {
			System.out.println("1 => DEPLOY; 2 => EXCHANGE; 3 => SKIP");
			System.out.println("It's " + activePlayer.getName() + "\'s turn>");
			String choiceOf3 = scanner.nextLine();
			makeChoice(choiceOf3);
		}
	}

	/**
	 * function defining only the part of choosing an army size after choosing a
	 * precise tile on the map we need to chose the tile first because each tile
	 * type has a maximum number of soldiers that could be put onto it
	 * 
	 * @param newPos the position of the tile to put the army on
	 */
	private void choseArmySize(Position newPos) {
		// TODO Auto-generated method stub
		System.out.print("you have " + activePlayer.getSoldiers() + " soldiers left; chose army size :> ");
		Tile chosenTile = this.getMap().findTileByPosition(newPos);
		String armySize = scanner.nextLine();
		if (!armySize.matches("[0-9]+")) {
			System.out.println("/!\\ Please enter a valid integer!");
			choseArmySize(newPos);
			return;
		} else {
			int size = Integer.parseInt(armySize);
			if (size < 1) {
				System.out.println("/!\\ number chosen is < 1; please chose a valid number of soldiers!");
				choseArmySize(newPos);
				return;
			} else if (size > chosenTile.getMaxNbSoldiers()) {
				System.out.println("/!\\ Maximum possible number of soldiers on this tile type is ["
						+ chosenTile.getMaxNbSoldiers() + " soldiers] only!");
				choseArmySize(newPos);
				return;
			} else {
				activePlayer.createArmy(size, chosenTile);
			}
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