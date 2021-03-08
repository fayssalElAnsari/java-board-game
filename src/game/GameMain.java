package game;

import java.util.Scanner;
import game.devagrico.FarmGame;
import game.war.WarGame;

public class GameMain {
	static Scanner scanner = new Scanner(System.in);
	static Game game;()
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
		gameTypeChoice(gameType);
	}

	public static void gameTypeChoice(String gameType) {
		if (gameType.equals("1")) {
			System.out.println("User chosen: " + WarGame.class.getName());
			chosenGameType = GameType.WARGAME;
			game = new WarGame();
		} else if (gameType.equals("2")) {
			System.out.println("User chosen: " + FarmGame.class.getName());
			chosenGameType = GameType.FARMGAME;
			game = new FarmGame();
		}
	}

}
