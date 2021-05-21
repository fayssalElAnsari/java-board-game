package game;

import java.util.List;

import game.player.FarmPlayer;
import game.player.Player;
import game.util.Tile;
import game.util.Tiletype;
import game.util.Util;

/**
 * A class to create and manage the farmgame
 */
public class FarmGame extends Game {

	private int nbTourJoue;
	/**
	 * public constructor for the farm game class
	 * @param x the x position 
	 * @param y the y position
	 * @param players the list of players in the game
	 */
	public FarmGame(int x, int y, List<Player> players) {
		super(x, y);
		this.players=players;
		this.nbTourJoue=0;
	}
	
	/**
	 * progress of the game with 6 as maximum of towers and choosing the action of the player 
	 * with recolting sorces and paying the player 
	 */
	public void start() {
		this.displayGame();
		while(true) {
			System.out.println("tour : "+this.nbTourJoue);
			for(int i =0; i<this.players.size();i++) {
				Player currentPlayer= this.players.get(i);
				int choixAction = Util.choixActionFarmGame(currentPlayer);
				switch(choixAction) {
				case 1:{
					boolean test;
					int[] pos;
					do {
						pos=Util.choixPos(this.board.getWidth()-1, this.board.getHeight()-1);
						test=this.affectOuvrier(currentPlayer, pos);
					}while(test==false);
					((FarmPlayer)currentPlayer).payDay();
					((FarmPlayer)currentPlayer).recolteRessources();
					break;
				}
				case 2:{
					System.out.println("choix 2");
					((FarmPlayer)currentPlayer).tradeRessources();
					((FarmPlayer)currentPlayer).payDay();
					((FarmPlayer)currentPlayer).recolteRessources();
					break;
				}
				case 3:{
					System.out.println("choix 3");
					((FarmPlayer)currentPlayer).payDay();
					((FarmPlayer)currentPlayer).recolteRessources();
					break;
				}
				
				}
				this.isEndGame(); 
				this.displayGame();
			}
			
			this.nbTourJoue++;
		}
	}

	/**
	 * Affect farmers in the game and making get there own tile with chosing position 
	 * @param player the player of the game
	 * @param pos the position of the player in the board
	 * @return true if the player own this tile
	 */
	public boolean affectOuvrier(Player player, int[] pos) {
		if(this.board.getTiles()[pos[1]][pos[0]].getOwner()!=null) {
			System.out.println("il y a d�j� un joueur sur cette case");
			return false;
		}
		if(this.board.getTiles()[pos[1]][pos[0]].getOwner()==player) {
			System.out.println("vous etes d�j� pr�sent sur cette case");
			return false;
		}
		
		Tile tile = this.board.getTiles()[pos[1]][pos[0]];
		this.newOwner(player, tile);
		return true;
	}
	
	/**
	 * to display the farm Game
	 */
	public void displayGame() {
		for(int i = 0; i<this.players.size();i++) {
			Player player=this.players.get(i);
			System.out.print( "nb tiles owned by player "+(i+1)+ " "+player.getName()+" ("+ player.getTileOwned().size()+") :");
			
			for (int j =0; j<player.getTileOwned().size();j++) {
				System.out.print("("+player.getTileOwned().get(j).getPos().getX()+" , "+player.getTileOwned().get(j).getPos().getY()+", unit:"+player.getTileOwned().get(j).getUnit()+") ");
			}
			System.out.println("");
			System.out.println("golds : "+player.getGolds());
			System.out.print("stock ressources :");
			System.out.print("roche ("+((FarmPlayer)player).getRessources().get(Tiletype.MONTAIN.name())+") ");
			System.out.print("ble ("+((FarmPlayer)player).getRessources().get(Tiletype.PLAIN.name())+") ");
			System.out.print("bois ("+((FarmPlayer)player).getRessources().get(Tiletype.WOOD.name())+") ");
			System.out.println("sable ("+((FarmPlayer)player).getRessources().get(Tiletype.DESERT.name())+")\n");
			
		}
		
		this.board.displayBoard();
		System.out.println("");
	}
	@Override
	/**
	 * The end of the game if  number of towers has passed 6 towers
	 */
	public boolean isEndGame() {
		if(this.nbTourJoue==6) {
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
		return false;
	}

	@Override
	/**
	 * the results of the game 
	 */
	public void resultat() {
		// TODO Auto-generated method stub
		int[] gagne= {-1,-1} ;
		for(int i =0; i<this.players.size();i++) {
			int goldDis=((FarmPlayer)this.players.get(i)).getGoldDistrib();
			System.out.println("joueur :"+this.players.get(i).getName()+" "+goldDis+" points!");
			
			if(gagne[1]<goldDis) {
				gagne[0]=i;
				gagne[1]=goldDis;
			}
		}
		System.out.println("le joueur gagnant est "+this.players.get(gagne[0]).getName()+" avec "+gagne[1]+" points!");
		
		System.exit(0);
	}

}
