package devAgricole.game;

import java.util.Random;

import devAgricole.game.util.Map;

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

    public Game(){
        map = new Map("testMap", 5, 5);
        players = new Player[4];
        // will make this using user input later 
        players[0] = new Player("random");
        players[1] = new Player("Citizen");
        players[2] = new Player("Bro");
        players[3] = new Player("Fayssal");
        Random r = new Random();
        int n = r.nextInt(players.length);
        activePlayer = players[n];
    }

    public Map getMap(){
        return this.map;
    }

    public int getnbRounds(){
        return this.nbRounds;
    }

    public int getCurrentRound(){
        return this.currentRound;
    }

    public void nextTurn(){

    }

    public void nextRound(){
        currentRound++;
    }

    public void findWinner(){
        winner = players[0];
        for(int i = 0; i < players.length-1; i++){
            if(players[i].getGold() < players[i+1].getGold()){
                winner = players[i+1];
            }
        }

    }

    public void gameEnd(){
        findWinner();
    }

}