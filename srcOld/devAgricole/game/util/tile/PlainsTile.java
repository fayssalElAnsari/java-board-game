package devAgricole.game.util.tile;

import devAgricole.game.util.Tile;
import devAgricole.game.util.*;

public class PlainsTile extends Tile{

    public PlainsTile(Position position){
        super(position,TileType.PLAINS,TileProd.CORN);
    }
    
}
