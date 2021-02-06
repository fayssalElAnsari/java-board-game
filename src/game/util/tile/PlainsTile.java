package game.util.tile;

import game.util.Tile;
import game.util.*;

public class PlainsTile extends Tile{

    public PlainsTile(Position position){
        super(position,TileType.PLAINS,TileProd.CORN);
    }
    
}
