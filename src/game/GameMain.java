package game;

import java.util.Scanner;

// bugs: les couleurs de map me marchent pas avec tout les systemes d'exploitation a cause du support code ANSII

//TODO: AJOUTERR LES TESSTS POUR CHAQUE METHODE POUUR CHAQUE CLASSE => ziko, mehdi
//TODO: LES ACTIONS PUBLICS ET PRIVEES => aya, fayssal

/**
 * 
 * @author fayss
 *
 */
public class GameMain {
	static Scanner scanner = new Scanner(System.in);
	public static Game game;
	static GameType chosenGameType;// devrait etre public?

	public static void main(String[] args) {
		choseGameType();
		game.startGame();
	}

	public GameType getChosenGameType() {
		return GameMain.chosenGameType;
	}

	public static void choseGameType() {
		System.out.println("1: war; 2: farm");
		System.out.println("Choose game type:> ");
		String gameType = scanner.nextLine();
		if (gameType.equals("1") || gameType.equals("2")) {
			gameTypeChoice(gameType);
		} else {
			choseGameType();
		}

	}

	public static void gameTypeChoice(String gameType) {
		if (gameType.equals("1")) {
//			System.out.println("User chosen: " + WarGame.class.getName());
			chosenGameType = GameType.WARGAME;
			game = new WarGame();
		} else if (gameType.equals("2")) {
//			System.out.println("User chosen: " + FarmGame.class.getName());
			chosenGameType = GameType.FARMGAME;
			game = new FarmGame();
		}
	}

}