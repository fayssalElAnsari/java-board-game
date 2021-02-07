package devAgricole.game;

import java.util.HashMap;
import java.util.Map.Entry;

import devAgricole.game.util.ActionPlayer;
import devAgricole.game.util.Map;
import devAgricole.game.util.Tile;
import devAgricole.game.util.tile.TileProd;
import devAgricole.game.util.tile.TileType;

public class Player {
    private String name;
    private Worker[] workers;
    private Tile startingTile;
    private int gold;
    private ActionPlayer lastAction;
    private HashMap<TileProd, Integer> inventory = new HashMap<TileProd, Integer>();

    public void setLastAction(ActionPlayer action){
        this.lastAction = action;
    }

    public String getName(){
        return this.name;
    }

    public Player(String name) {
        this.name = name;
        this.gold = 15;
        this.lastAction = ActionPlayer.NOTHING;
        workers = new Worker[25];
        for (TileProd resource : TileProd.values()) {
            inventory.put(resource, 0);
        }
    }

    public void buyWorker() {
        this.payGold();
        Worker worker = new Worker(this);
        int i = getFirstEmptySlot();
        if (i != -1) {
            workers[i] = worker;
        }

    }

    public Tile getStartingTile() {
        return this.startingTile;
    }
    
    public void setStartingTile(Tile tile) {
        this.startingTile = tile;
    }

    /**
     * finds the first empty worker slot of this tile
     * 
     * @return
     */
    public int getFirstEmptySlot() {
        int result = -1;
        for (int i = 0; i < workers.length; i++) {
            if (workers[i] == null) {
                result = i;
                break;
            }
        }
        return result;
    }

    public Worker getLastWorker(){
        Worker w;
        if(hasWorkers()){// if the player has atleast a worker
            w = workers[getFirstEmptySlot()-1];
            return w;
        } else {
            buyWorker();
            return workers[0];
        }
        
    }

    /**
     * check if the player has no workers in his arsenal XD
     * @return true if he has no workers; false if he has a worker
     */
    public boolean hasWorkers(){
        return (getFirstEmptySlot() == -1);
    }

    public void moveWorker(Worker worker, Tile tile) {
        this.lastAction = ActionPlayer.DEPLOY;
        for (int i = 0; i < workers.length; i++) {
            if (workers[i] == worker) {
                worker.move(tile);
            }
        }
    }

    public void getPayed(int profit){
        this.gold += profit;
    }

    public void sellResources() {
        this.lastAction = ActionPlayer.EXCHANGE;
        for (Entry<TileProd, Integer> entry : this.inventory.entrySet()) {
            TileProd product = entry.getKey();
            Integer quantity = entry.getValue();
            int profit = 0;
            if(product == TileProd.ROCK){
                profit = quantity*8;
            } else if(product == TileProd.CORN){
                profit = quantity*3;
            } else if(product == TileProd.SAND){
                profit = quantity;
            } else if(product == TileProd.WOOD){
                profit = quantity;
            }
            getPayed(profit);     
            entry.setValue(0);
        }
    }
    
    public void skipRound(){
        this.lastAction = ActionPlayer.NOTHING;
        getPayed(1);
    }

    public void payWorkers(){
        boolean affordable = true;
        for (Worker worker : workers){
            if(worker.getTile().getTileType() == TileType.MOUNTAINS){
                affordable = payGold(5);
                worker.getPayed(5);
            } else if(worker.getTile().getTileType() == TileType.DESERTS){
                payGold(3);
                worker.getPayed(3);
            } else if(worker.getTile().getTileType() == TileType.FORESTS){
                payGold(1);
                worker.getPayed();
            } else if(worker.getTile().getTileType() == TileType.PLAINS){
                payGold(1);
                worker.getPayed();
            }
            if(!affordable){
                worker = null;
            }
        }
    }

    public boolean payGold(int loss){
        if(this.gold >= loss){
            this.gold -= loss;
            return true;
        }
        return false;
        
    }

    public boolean payGold(){
        if(this.gold >= 1){
            this.gold--;
            return true;
        }
        return false;
        
    }

    public void receiveResources(TileProd product, int quantity){
        inventory.put(product, inventory.get(product) + quantity);
    }

	public int getGold() {
		return this.gold;
	}


}
