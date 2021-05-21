package game.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import game.player.FarmPlayer;
import game.player.Player;
import game.player.WarPlayer;

/**
 * A class to create and manage the position .
 */
public class Util {

	/*
	 * asking to chose what kind of game to play 
	 * 1 for war game
	 * 2 for farm game
	 * */
	/**
	 * asking to chose what kind of game to play 
	 * 1 for war game
	 * 2 for farm game
	 * @return choix
	 */
	public static int typeGame() {
		int choix=0;
		Scanner entree = new Scanner(System.in);
		while(choix<1 || choix>2) {
			System.out.println("a quel jeu voulez vous jouer?");
			System.out.println("1 : war game");
			System.out.println("2 : farm game");
			try {
				choix=entree.nextInt();
			} catch (Exception e) {
				System.out.println("il faut entrer un chiffre entre 1 et 2");
				entree.nextLine();
			}
		}
		
		return choix;
		
	}

	/**
	 * choosing the position with x and y 
	 * @param maxX for the board
	 * @param  maxY for the board
	 * @return choix the choice that player have chosen
	 */
	public static int[] choixPos(int maxX, int maxY) {
		
		int[] choix= {-1,-1};
		Scanner entree = new Scanner(System.in);
		while(choix[0]<0 || choix[0]>maxX) {
			System.out.println("choix du X:");
			try {
				choix[0]=entree.nextInt();
				if(choix[0]<0 || choix[0]>maxX) {
					System.out.println("il faut entrer un chiffre entre 0 et "+maxX);
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("il faut entrer un chiffre entre 0 et "+maxX);
				entree.nextLine();
			}
		}
		while(choix[1]<0 || choix[1]>maxY) {
			System.out.println("choix du Y:");
			try {
				choix[1]=entree.nextInt();
				if(choix[1]<0 || choix[1]>maxY) {
					System.out.println("il faut entrer un chiffre entre 0 et "+maxY);
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("il faut entrer un chiffre entre 0 et "+maxY);
				entree.nextLine();
			}
		}
		
		return choix;
	}

	/**
	 * choosing the action of player in war game to deploy troops and pass to the next round
	 * @param player the player of the game
	 * @return choix the choice of action of the player
	 */
	public static int choixActionWarGame(Player player) {
		int choix=0;
		Scanner entree = new Scanner(System.in);
		while(choix<1 || choix>2) {
			System.out.println("joueur actuel : "+ player.getName());
			System.out.println("choisissez une action :");
			System.out.println("1 : deployer des troupes");
			System.out.println("2 : passer son tour");
			try {
				choix=entree.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("il faut entrer un chiffre entre 1 et 2");
				entree.nextLine();
			}
		}
		
		return choix;
	}

	/**
	 * choosing the action of player in farm game to choose the game and exchange resources and pass to the next round
	 * @param player the player of the game
	 * @return choix the choice of action of the player
	 */
	public static int choixActionFarmGame(Player player) {
		int choix =0;
		Scanner entree = new Scanner(System.in);
		while(choix<1 || choix>3) {
			System.out.println("joueur actuel : "+ player.getName());
			System.out.println("choisissez une action :");
			System.out.println("1 : deployer un ouvrier");
			System.out.println("2 : echanger ses ressources");
			System.out.println("3 : passer son tour");
			try {
				choix=entree.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("il faut entrer un chiffre entre 1 et 3");
				entree.nextLine();
			}
		}
		return choix;		
	}
	/**
	 * create farm players between 2 and 4 players and the name of player 
	 * @return the players of this game
	 */
	public static List<Player> createFarmPlayers(){
		Scanner entree = new Scanner(System.in);
		int nbJoueur= 0;
		List<Player> players = new ArrayList<>();
		while(nbJoueur<2 || nbJoueur>4 ) {
			System.out.println("entrez le nombre de joueur (min 2, max 4)");
			try {
				nbJoueur=entree.nextInt();
			} catch (Exception e) {
				System.out.println("il faut entrer un chiffre entre 2 et 4");
				entree.nextLine();
			}
			
		}
		for(int i = 0; i<nbJoueur;i++) {
			System.out.println("entrez le nom du joueur "+(i+1));
			String name=entree.next();
			FarmPlayer player = new FarmPlayer(name);
			players.add(player);
		}
		
		return players;
		
	}
	
	/**
	 * create war players between 2 and 4 players and the name of player 
	 * @return the players of this game
	 */
	public static List<Player> createWarPlayers(){
		Scanner entree = new Scanner(System.in);
		int nbJoueur= 0;
		List<Player> players = new ArrayList<>();
		while(nbJoueur<2 || nbJoueur>4 ) {
			System.out.println("entrez le nombre de joueur (min 2, max 4)");
			try {
				nbJoueur=entree.nextInt();
			} catch (Exception e) {
				System.out.println("il faut entrer un chiffre entre 2 et 4");
				entree.nextLine();
			}
			
		}
		for(int i = 0; i<nbJoueur;i++) {
			System.out.println("entrez le nom du joueur "+(i+1));
			String name=entree.next();
			WarPlayer player = new WarPlayer(name);
			players.add(player);
		}
		
		return players;
	}
}
