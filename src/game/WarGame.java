package game;

import java.util.List;
import java.util.Scanner;

import game.player.Player;
import game.player.WarPlayer;
import game.util.Tile;
import game.util.Tiletype;
import game.util.Util;

/**
 * A class to create and manage the wargame
 */

public class WarGame extends Game {
	/**
	 * number of tours in the game
	 */
	private int nbTourJoue;
	/**
	 * public constructor for the war game class
	 * @param x the x position of the player
	 * @param y the y position of the player
	 * @param players list of players 
	 */
	public WarGame(int x, int y, List<Player> players) {
		super(x,y);
		this.nbTourJoue=0;
		this.players=players;
	}

	/**
	 * progress of the game with 10 as maximum of towers and choosing the action of the player 
	 * Collection food and a distribution  of food 
	 */
	public void start() {
		System.out.println("war game start");
		this.displayGame();
		while(true) {
			for(int i =0; i<this.players.size();i++) {
				Player currentPlayer= this.players.get(i);
				int choixAction = Util.choixActionWarGame(currentPlayer);
				switch (choixAction) {
				case 1: {
					boolean test;
					int[] pos;
					do {
						pos=Util.choixPos(this.board.getWidth()-1, this.board.getHeight()-1);
						test=this.affectArmee(currentPlayer, pos);
					}while(test==false);
					this.isEndGame();
					this.deploiementAdjacent(currentPlayer, pos);
					((WarPlayer)currentPlayer).recolteFood();
					((WarPlayer)currentPlayer).distribFood();
					break;
				}
				case 2 :{
					System.out.println("choix2\n");
					((WarPlayer)currentPlayer).recolteFood();
					((WarPlayer)currentPlayer).distribFood();
					break;
				}
				}
				this.isEndGame();
				this.displayGame();
			}
			this.nbTourJoue++;
			System.out.println("round nb :"+this.nbTourJoue);
		}
		
	}
	/**
	 * Affect players in the game and making them get there own tile with choosing position 
	 * @param player the war game player
	 * @param pos the position of player
	 */
	public boolean affectArmee(Player player, int[] pos) {
		if(this.board.getTiles()[pos[1]][pos[0]].getOwner()!=null) {
			System.out.println("il y a d�j� un joueur sur cette case");
			return false;
		}
		if(this.board.getTiles()[pos[1]][pos[0]].getOwner()==player) {
			System.out.println("vous etes d�j� pr�sent sur cette case");
			return false;
		}
		//get the desired tile 
		Tile tile = this.board.getTiles()[pos[1]][pos[0]];
		int nbUnitDeplo=this.askNbUnitToDeploy(tile);
		if(nbUnitDeplo>((WarPlayer)player).getNbWarAvailable()) {
			System.out.println("vous n'avez pas assez de guerrier");
			return false;
		}
		tile.setUnit(nbUnitDeplo);
		((WarPlayer) player).deployedWar(nbUnitDeplo);
		this.newOwner(player,tile );
		this.attaqueVoisins(player, pos);
		return true;
		
	}
	/**
	 * Neighbors attack depending on the position of the player
	 * check if the owner of the tile in the east is different from null and different from the current player 
	 * check the owner of the tile to the south is different from null and different from the current player 
	 * check the owner of the tile to the north is different from null and different from the current player 
	 * check the owner of the west tile is different from null and different from the current player 
	 * @param player the war player
	 * @param pos the position of the player
	 */
	public void attaqueVoisins(Player player, int[] pos) {
		Tile currentTile=this.board.getTiles()[pos[1]][pos[0]];
		//south
		if(pos[1]+1<this.board.getHeight()){
			Tile tile=this.board.getTiles()[pos[1]+1][pos[0]];
			this.attaque(player, currentTile, tile);
		}
		//north
		if(pos[1]-1>=0){			
			Tile tile=this.board.getTiles()[pos[1]-1][pos[0]];
			this.attaque(player, currentTile, tile);
		}
		//west
		if(pos[0]-1>=0){
			Tile tile=this.board.getTiles()[pos[1]][pos[0]-1];
			this.attaque(player, currentTile, tile);
		}
		//east
		if(pos[0]+1<this.board.getWidth()){
			Tile tile=this.board.getTiles()[pos[1]][pos[0]+1];
			this.attaque(player, currentTile, tile);
		}
		
		
	}
	/**
	 * attacking from current tile to the new tile 
	 * @param player owner of tile
	 * @param currentTile the current tile of player 
	 * @param tile the tile of player
	 */
	public void attaque(Player player, Tile currentTile, Tile tile) {
		if(tile.getOwner()!=player && tile.getOwner()!=null) {
			int currentUnit=currentTile.getUnit();
			int tileUnit= tile.getUnit();
			if(currentTile.getType()==Tiletype.MONTAIN) {
				currentUnit+=2;
			}
			if(tile.getType()==Tiletype.MONTAIN) {
				tileUnit+=2;
			}
			
			if(currentUnit>tileUnit) {
				if(tile.getUnit()==1) {
					tile.getOwner().lostTile(tile);
					this.newOwner(player, tile);
					player.setGolds(player.getGolds()+2);
				}else {
					tile.setUnit((int)(tile.getUnit()/2));
				}
				
			}
		}
	}
	/**
	 * deploiement Adjacent of players in the board accruent to the given position 
	 * @param player the war player
	 * @param pos he position of player
	 */
	public void deploiementAdjacent(Player player, int[] pos) {
		Tile currentTile=this.board.getTiles()[pos[1]][pos[0]];
		//south
		if(pos[1]+1<this.board.getHeight()){
			
			Tile tile=this.board.getTiles()[pos[1]+1][pos[0]];

			this.deploiement(player, currentTile, tile);
		}
		//north
		if(pos[1]-1>=0){
			
			Tile tile=this.board.getTiles()[pos[1]-1][pos[0]];
			this.deploiement(player, currentTile, tile);
		}
		//west
		if(pos[0]-1>=0){
			
			Tile tile=this.board.getTiles()[pos[1]][pos[0]-1];
			this.deploiement(player, currentTile, tile);
		}
		//east
		if(pos[0]+1<this.board.getWidth()){
			
			Tile tile=this.board.getTiles()[pos[1]][pos[0]+1];
			this.deploiement(player, currentTile, tile);
		}
	}
	/**
	 * deploiment of player according to the own tile into the new file
	 * @param player war player
	 * @param currentTile the current tile
	 * @param tile the next tile 
	 */
	public void deploiement(Player player, Tile currentTile, Tile tile) {
		if(currentTile.getUnit()>tile.getUnit() && currentTile.getOwner()==tile.getOwner()) {
			if (tile.getType()==Tiletype.DESERT ||tile.getType()==Tiletype.MONTAIN) {
				if (tile.getUnit()<3) {
					currentTile.setUnit(currentTile.getUnit()-1);
					tile.setUnit(tile.getUnit()+1);
					player.setGolds(player.getGolds()+1);
				}
			}else {
				currentTile.setUnit(currentTile.getUnit()-1);
				tile.setUnit(tile.getUnit()+1);
				player.setGolds(player.getGolds()+1);
			}
		}
	}
	
	/**
	 * asking the number of units to deploy according to the type of tile 
	 * if the type tile is desert or montain the number units to be deployed is 3 as max 
	 * it the type tile is a plain or wood, number of units to be deployed is 5 s max
	 * @param tile the tile in the board
	 * @return the choice have been chosen by the player
	 */
	public int askNbUnitToDeploy(Tile tile) {
		int nbMax=0;
		
		if (tile.getType()==Tiletype.DESERT ||tile.getType()==Tiletype.MONTAIN) {
			nbMax=3;
		}else {
			nbMax=5;
		}
		
		int choix=0;
		Scanner entree = new Scanner(System.in);
		while(choix<1 || choix>nbMax) {
			System.out.println("Combien de troupe voulez vous deployer? (max:"+nbMax+")");;
			try {
				choix=entree.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("il faut entrer un chiffre entre 1 et "+nbMax);
				entree.nextLine();
			}
		}
		
		return choix;
		
	}
	/**
	 * the game will be ended when the number of towers is more than 10 or when he gets ocen tile type 
	 */
	@Override
	public boolean isEndGame() {
		if(this.nbTourJoue==10) {
			this.resultat();
			return true;
		}
		for(int i =0; i<board.getTiles().length;i++) {
			for(int j =0; j<board.getTiles()[0].length;j++) {
				if(board.getTiles()[i][j].getOwner()!=null && board.getTiles()[i][j].getType()!=Tiletype.OCEAN ) {
					return false;
				}
			}
		}
		
		this.resultat();
		return true;
	}
	/**
	 * the final results of game and the number of point fo each player in the game 
	 */
	@Override
	public void resultat() {
		int[] gagne= {-1,-1} ;
		for(int i =0; i<this.players.size();i++) {
			int nbPoint=0;
			Player current = this.players.get(i);
			for(int j =0; j<current.getTileOwned().size();j++) {
				switch (current.getTileOwned().get(i).getType()) {
				
				case PLAIN: {
					nbPoint++;
					break;
				}
				case WOOD: {
					nbPoint+=2;
					break;
				}
				case MONTAIN: {
					nbPoint+=4;
					break;
				}
				case DESERT: {
					nbPoint+=4;
					break;
				}
				}
				nbPoint+=current.getGolds();
				if(current.getTileOwned().size()>=10) {
					nbPoint+=5;
				}
			}
			System.out.println("joueur :"+current.getName()+" "+nbPoint+" points!");
			if(gagne[1]<nbPoint) {
				gagne[0]=i;
				gagne[1]=nbPoint;
			}	
		}
		
		
		System.out.println("le joueur gagnant est "+this.players.get(gagne[0]).getName()+" avec "+gagne[1]+" points!");
		System.exit(0);
	}

}
