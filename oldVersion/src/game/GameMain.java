package game;

import java.util.Scanner;
// bugs: les couleurs de map me marchent pas avec tout les systemes d'exploitation a cause du support code ANSII
// bugs: 1 food unit is added to the actual number of soldiers, probably a mistake when calculating the food consumption bonus
// bugs: at first deployement the resources sent to the user are only 1, which means the nubmer of resources to be produced isn't
//			updated before calling the sendResources() function

//TODO: AJOUTERR LES TESSTS POUR CHAQUE METHODE POUUR CHAQUE CLASSE => ziko, mehdi
//TODO: LES ACTIONS PUBLICS ET PRIVEES => aya, fayssal
// 18/04/2021
//TODO: MAKE LISTS INSTEAD OF ARRAYS TO KEEP TRACK OF ADDED ARMIES, BECAUSE IT WOULD BE EASIER TO ADD/REMOVE
// I THINK IT'S STILL FASTER TO USE ARRAYS BUT THE PERFORMANCE DIFFERENCE ISN'T THAT MUCH SINCE WE WONT HAVE HUGE MAPS
//TODO: TO KEEP TRACK OF DEPLOYED ARMIES IT'S BETTER TO USE A LIST OF TILES INSIDE THE PLAYER CLASS
//TODO: USE CLASSES FOR PRODUCTION TYPES INSTEAD OF ENUMS

//TODO: BY PRIORITY: 
// 2. CANGE PRODUCTION TYPES TO CLASSES
// 3. IMPLEMENT INTERFACES FOR ACTIONS
// 4. FIX FARM GAME

/**
 * is it possible to create an instance of a class and duplicate it multiple times as needed?
 * we want to make the choice of the gametype war/farm in the beginning and define different
 *  values for the same parameters
 * for instance the amount of points for each tile type should change in the beginning,
 *  and amount for exchanging resources to gold/points
 * 
 */

/**
 * should the behavior of putting armies be defined inside the Tile Type, since it depends on it
 * meaning the maximum number of units to be put inside the tile cannot exceed the maximum limit
 * also for the possiblity of putting an army in the tile (in the case of an ocean tile) 
 * right now it is beign defined inside the WarGame class, if we do the tests for this case it 
 * would be possible to ovewrite the old army list since the alreadyHasOwner check is being done 
 * in the WarGame class and not in the Tile/TilType class
 */

/**
 * 
 * @author fayss
 *
 */
public class GameMain {
	private static final String WARGAME_CHOICE = "1";
	private static final String FARMGAME_CHOICE = "2";
	static Scanner scanner = new Scanner(System.in);
	public static Game game;
	static GameType chosenGameType;// use static instead of getter ??

	public static void main(String[] args) {
		promptChooseGameType();
		game.startGame();
	}

	/**
	 * ask the user to make a choice of game type
	 */
	public static void promptChooseGameType() {
		System.out.println("1: war; 2: farm");
		System.out.println("Choose game type:> ");
		String gameType = scanner.nextLine();
		if (gameType.equals(WARGAME_CHOICE) || gameType.equals(FARMGAME_CHOICE)) {
			defineGameTypeChoice(gameType);
		} else {
			promptChooseGameType();
		}
	}

	/**
	 * process the game type choice of the user
	 * 
	 * @param gameType the String passed by the user which will define the game type
	 *                 choice
	 */
	public static void defineGameTypeChoice(String gameType) {
		if (gameType.equals(WARGAME_CHOICE)) {
			chosenGameType = GameType.WARGAME;
			game = new WarGame();
		} else if (gameType.equals(FARMGAME_CHOICE)) {
			chosenGameType = GameType.FARMGAME;
			game = new FarmGame();
		}
	}

}