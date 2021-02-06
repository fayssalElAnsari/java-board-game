package devAgricole.game;

import devAgricole.game.util.Map;

public class Game {

    private static final int winPoints = 1;
    private static final int drawPoints = 0;
    private static final int losePoints = -1;

    private Map map;
    private Player activePlayer;


    private Player[] players;
    private Player winner;
    private int nbRounds = 6;


}