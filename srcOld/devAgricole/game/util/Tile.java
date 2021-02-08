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
    private boolean isSpawnTile;
    

    public Tile(Position position, TileType tileType, TileProd tileProd){
        this.position = position;
        this.tileType = tileType;
        this.tileProd = tileProd;
        this.workers = new Worker[maxWorkers];

    }

    /**
     * check if this tile is a workers spawning tile
     * @return true if it's only for spawning working and cannot be worked on 
     */
    public boolean isStartingTile(){
        return this.isSpawnTile;
    }

    /**
     * gets the position of this tile
     * @return the exact position of this tile
     */
    public Position getPosition(){
        return this.position;
    }

    /**
     * calculate the distance between this tile and another one
     * @param otherTile the tile to calculate the distance between it and this tile object
     * @return the distance between the two tiles 
     */
    public double calculateDistance(Tile otherTile){
        return this.position.calculateDistance(otherTile.position);
    }


    /**
     * gets the owner of this tile
     * @return the player who owns this tile (has workers on it)
     */
    public Player getOwner(){
        return this.owner;
    }

    /**
     * sets the owner of this tile to the passed in param
     * @param newOwner the newowner of this tile object
     */
    public void setOwner(Player newOwner){
        this.owner = newOwner;
    }

    /**
     * reduce the resources available for this extraction from this tile accordingly 
     * @param loss the amount to be subtracted from this tile
     * @return true if the amount available for extraction is bigger than the amount to be extracted, 
     * false if the extraction was not succesful
     */
    public boolean loseResources(int loss){
        if(resources > loss){
            this.resources = this.resources - loss;
            return true;
        } else return false;
        
    }

    /**
     * add a worker to this tile and start working if it's not a spawn tile
     * @param newWorker the new worker object to be added to this tile
     * @return true if the worker was added to this tile successfully, 
     * false if the worker could not be added to this tile's array of workers
     */
    public boolean addWorker(Worker newWorker){
        if(this.owner != null){
            int i = getFirstEmptySlot();
            workers[i] = newWorker;
            return true;
        } else return false;
        
    }

    /**
     * gets the number of empty slots still available in this tile
     * @return the number of empty woker slots in this tile
     */
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
     * @return  the first empty workers array slot in this tile
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

    /**
     * empties the last full slot from this tile's array of workers
     * by setting it to null
     */
    public void emptyASlot(){
        int lastFullSlot = getFirstEmptySlot();
        this.workers[lastFullSlot] = null;
        updateOwner();
    }

    public void emptyASlot(int i){
        this.workers[i] = null;
        updateOwner();
    }
    

    public TileType getTileType(){
        return this.tileType;
    }

    public TileProd getTileProd(){
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
        if(!isSpawnTile){
            if(checkIfEmpty()){
                this.owner = null;
            }
        }
    }

    public int getNumberOfWorkers(){
        int result = 0;
        for(int i=0; i < workers.length; i++){
            if(workers[i] != null){
                result += 1;
                break;
            }
        }
        return result;
    }

	public void setIsStartingTile() {
        this.isSpawnTile = true;
	}


}
