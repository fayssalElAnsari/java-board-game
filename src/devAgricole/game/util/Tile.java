package devAgricole.game.util;

import devAgricole.game.Player;
import devAgricole.game.Worker;
import devAgricole.game.util.tile.TileProd;
import devAgricole.game.util.tile.TileType;

public abstract class Tile {
    private final Position position;
    private final TileType tileType;
    private final TileProd tileProd;
    private int resources = 5000;
    private static final int maxWorkers = 5;
    private Worker[] workers;
    private Player owner;
    

    public Tile(Position position, TileType tileType, TileProd tileProd){
        this.position = position;
        this.tileType = tileType;
        this.tileProd = tileProd;
    }

    public Position getPosition(){
        return this.position;
    }

    public double calculateDistance(Tile otherTile){
        return this.position.calculateDistance(otherTile.position);
    }

    public Player getOwner(){
        return this.owner;
    }

    public void setOwner(Player newOwner){
        this.owner = newOwner;
    }

    public boolean loseResources(int loss){
        if(resources > loss){
            this.resources = this.resources - loss;
            return true;
        } else return false;
        
    }

    public boolean addWorker(Worker newWorker){
        if(this.owner != null){
            if 
            return true;
        } else return false;
        
    }

    public int getEmptySlots(){
        int empties = 0;
        for(int i=0; i < workers.length; i++){
            if (workers)[i] == null){
                empties++;
            }
        }
        return empties;
    }

    /**
     * finds the first empty worker slot of this tile
     * @return 
     */
    public int getFirstEmptySlot(){
        int result = -1;
        for(int i=0; i < workers.length; i++){
            if(workers[i] == null){
                result = i;
                break;
            }
        }
        return result;
    }

    public boolean emptyASlot(){
        this.workers.
    }


}