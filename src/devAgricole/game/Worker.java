package devAgricole.game;

import devAgricole.game.util.Tile;

public class Worker {
    private Player owner;
    private int gold = 0;
    private int speed = 100;
    private int step = 1;
    private Tile tile;
    private int resources = 0;
    private boolean working = false;

    /**
     * public constructor for a worker
     * the worker will start with only 1 gold which he will take from the player
     * the worker will spawn in the owner's starting tile
     * @param owner the new owner of the newly created worker object
     */
    public Worker(Player owner){
        this.owner = owner;
        this.gold = 1;
        this.tile = owner.startingTile;

    }

    /**
     * check if the worker can move to newTile by comparing the distance to the step
     * @param newTile the new tile the worker should go to
     * @return True if the move is succesful, false if not;
     */
    public boolean move(Tile newTile){
        if(this.tile.calculateDistance(tile) <= this.step){
            this.tile = newTile;
            return true;
        }        
        return false;

    }

    public Tile getTile(){
        return this.tile;
    }

    /**
     * start working in the current tile
     */
    public void work(){
        if (this.tile.getOwner() == null){
            if (this.gold >= 1){
                this.working = true;

            }
        }
    }

    public void getPayed(){
        this.gold++;
    }

    public void sendResources(){
        tile.
        this.resources = 0;
    }

    public void die(){
        
    }
    
}
