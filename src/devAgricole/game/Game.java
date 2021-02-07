package devAgricole.game;

import java.util.Random;
import java.util.Scanner;

import devAgricole.game.util.ActionPlayer;
import devAgricole.game.util.Map;
import devAgricole.game.util.Position;

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
    Scanner scanner = new Scanner(System.in);

    public Game(){
        map = new Map("testMap", 5, 5);
        createPlayers();
        Random r = new Random();
        int n = r.nextInt(players.length);
        activePlayer = players[n];

    }

    public void createPlayers(){
        players = new Player[4];
        // will make this using user input later 
        // setting names
        players[0] = new Player("fayssal");
        players[1] = new Player("aya");
        players[2] = new Player("mehdi");
        players[3] = new Player("ziko");
        // setting worker spawn tile location
        players[0].setStartingTile(map.getTile(new Position(0, map.getHeight()-1)));
        players[1].setStartingTile(map.getTile(new Position(map.getWidth()-1, map.getHeight()-1)));
        players[2].setStartingTile(map.getTile(new Position(map.getWidth()-1, 0)));
        players[3].setStartingTile(map.getTile(new Position(0, 0)));
        // giving the spawn tiles to their rightfull owners
        this.map.getTile(players[0].getStartingTile().getPosition()).setOwner(players[0]);
        this.map.getTile(players[1].getStartingTile().getPosition()).setOwner(players[1]);
        this.map.getTile(players[2].getStartingTile().getPosition()).setOwner(players[2]);
        this.map.getTile(players[3].getStartingTile().getPosition()).setOwner(players[3]);
    }

    public void startGame(){
        while(this.getCurrentRound() <= this.getnbRounds()){
            for (int i = 0; i < players.length; i++){
                System.out.println("ROUND: " + currentRound + " OF " + nbRounds);
                showStats();
                this.getMap().printMap();
                System.out.println("1 => DEPLOY; 2 => EXCHANGE; 3 => SKIP");
                System.out.println("It's " + activePlayer.getName() + "\'s turn>");
                String choiceOf3 = scanner.nextLine();
                makeChoice(choiceOf3);
                nextTurn();
            }
            this.nextRound();
        }
        this.gameEnd();
    }

    public void showStats(){
        for(int i = 0; i < players.length; i++){
            System.out.println(players[i].getName() + " has " + players[i].getGold() + " and " + players[i].getNumberOfWorkers() + " workers.");
        }
    }

    /**
     * a player can make one of 3 choices
     * 1: deplayer a worker
     * 2: exchange inventory resources for gold
     * 3: skip round and get 1 gold for it
     * @param line the choice made by the user
     */
    private void makeChoice(String line) {
        if ( line.equals("1")){
            activePlayer.setLastAction(ActionPlayer.DEPLOY);
            /**
             * should check if player has some workers already
             * if not he should buy
             * if he does have wokers already he shoul chose which worker to move
             * and where to move him to
             * if the move is successful it's the end of the player's turn
             * if it's not succesful he has to chose a worker again...
             */
            activePlayer.printOutWorkersList();
            System.out.println("chose a worker: ... ");
            int workerIndex = Integer.parseInt(scanner.nextLine());
            System.out.println("chose a position: ... ");
            String position = scanner.nextLine();
            Position newPos = new Position(Integer.parseInt(position.split(",")[0]), Integer.parseInt(position.split(",")[1]));
            activePlayer.moveWorker(activePlayer.getWorkerByIndex(workerIndex), map.getTile(newPos));
        } else if (line.equals("2")){
            activePlayer.setLastAction(ActionPlayer.EXCHANGE);
            // String choiceOf3 = scanner.nextLine();
            activePlayer.sellResources();
        } else if (line.equals("3")){
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

    public Map getMap() {
        return this.map;
    }

    public int getnbRounds(){
        return this.nbRounds;
    }

    public int getCurrentRound(){
        return this.currentRound;
    }

    public void nextTurn(){
        // find the next player
        int n = -1;
        for(int i = 0; i< players.length; i++){
            if(activePlayer == players[i]){
                n = i;
            }
        }
        activePlayer = players[(n+1)%players.length];
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
        System.out.println("The winner is...");
        System.out.println(winner.getName() + " with " + winner.getGold() + " points!!");
    }

}