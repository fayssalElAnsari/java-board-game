package devAgricole.game;

import devAgricole.game.util.Tile;

public class Worker {
    private Player owner;
    private int gold = 0;
    private int speed = 100;
    private double step = 1.7;
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
        this.getPayed();
        this.tile = owner.getStartingTile();
        this.tile.addWorker(this);

    }

    public void startTurn(){
        if (working) {
            resources += speed;
            System.out.println("/!\\ added resources: " + speed);
        }
    }

    /**
     * check if the worker can move to newTile by comparing the distance to the step
     * @param newTile the new tile the worker should go to
     * @return True if the move is succesful, false if not;
     */
    public boolean move(int i, Tile newTile){
        if(this.tile.calculateDistance(tile) <= this.step){
            if(this.tile.getNumberOfWorkers() != 0){
                this.tile.emptyASlot(i);
            }
            this.tile = newTile;
            this.tile.setOwner(this.owner);
            // we will make the worker start working automatically
            work();
            return true;
        }        
        return false;

    }

    public int getResources(){
        return this.resources;
    }

    public Tile getTile(){
        return this.tile;
    }

    /**
     * start working in the current tile
     * need to check if the owner of the worker is the owner of the tile 
     * if so he will start workin
     */
    public void work(){
        this.tile.addWorker(this);
        if(!this.tile.isStartingTile()){
            this.working = true;
        }

        // if (this.tile.getOwner() == null || this.tile.getOwner() == map...){
        //     if (this.gold >= 1){
        //         this.working = true;
        //     }
        // }
    }

    public void getPayed(){
        this.gold++;
    }

    public void getPayed(int salary){
        if(this.owner.getGold() >= salary){
            this.gold += salary;
        }
    }

    public void sendResources(){
        this.owner.receiveResources(this.tile.getTileProd(), resources);
        this.resources = 0;
    }

    public void nextTurn(){
        sendResources();
    }
    
}
