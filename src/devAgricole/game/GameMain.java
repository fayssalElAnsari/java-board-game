package devAgricole.game;

import devAgricole.game.util.Map;

public class GameMain {
    

    public static void main(String[] args){
        Game game = new Game();
        while(game.getCurrentRound() <= game.getnbRounds()){
            game.getMap().printMap();

            game.nextRound();
        }
    }
}
