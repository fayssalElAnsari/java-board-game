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
    private static final int maxWorkers = 1;
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
            int i = getFirstEmptySlot();
            workers[i] = newWorker;
            return true;
        } else return false;
        
    }

    public int getEmptySlots(){
        int empties = 0;
        for(int i=0; i < workers.length; i++){
            if (workers[i] == null){
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

    public void emptyASlot(){
        int lastFullSlot = getFirstEmptySlot() -1;
        this.workers[lastFullSlot] = null;
        updateOwner();
    }
    

    public TileType getTileType(){
        return this.tileType;
    }

    public TileProd gTileProd(){
        return this.tileProd;
    }

    public boolean checkIfEmpty(){
        boolean isEmpty = true;
        for(Worker worker: workers){
            if(worker != null){
                isEmpty = false;
            }
        }
        return isEmpty;
    }

    public void updateOwner(){
        if(checkIfEmpty()){
            this.owner = null;
        }
    }


}
