package game.util;

import game.Player;
import game.Worker;
import game.util.tile.TileProd;
import game.util.tile.TileType;

public abstract class Tile {
    private final Position position;
    private final TileType tileType;
    private final TileProd tileProd;
    private int resources = 5000;
    private Worker[] workers;
    private Player owner;
    

    public Tile(Position position, TileType tileType, TileProd tileProd){
        this.position = position;
        this.tileType = tileType;
        this.tileProd = tileProd;
    }

    


}
