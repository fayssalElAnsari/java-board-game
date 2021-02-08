package devAgricole.game.util;

import java.util.Random;

import devAgricole.game.util.tile.DesertsTile;
import devAgricole.game.util.tile.ForestsTile;
import devAgricole.game.util.tile.MountainsTile;
import devAgricole.game.util.tile.OceanTile;
import devAgricole.game.util.tile.PlainsTile;
import devAgricole.game.util.tile.TileType;

public class Map {
    private Tile[][] tiles;
    private String name;
    private int width;
    private int height;

    // TODO: chose a random Tile
    // TODO: add the condition where all land tiles are connected 

    /**
     * public constructor of the map
     * 
     * @param name the name of the map
     * @param width the width of the map
     * @param height the height of the map
     */
    public Map(String name, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        // Class[] possibleClasses = {DesertsTile.class, ForestsTile.class, MountainsTile.class, PlainsTile.class};
        Random r = new Random();
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                // there should be a better way to do this obviously XD
                int n = r.nextInt(5);
                if(n == 0){
                    tiles[i][j] = new MountainsTile(new Position(i, j));// position redunduncy??
                } else if (n ==1 ){
                    tiles[i][j] = new PlainsTile(new Position(i, j));// position redunduncy??
                } else if (n == 2){
                    tiles[i][j] = new DesertsTile(new Position(i, j));// position redunduncy??
                } else if (n == 3){
                    tiles[i][j] = new ForestsTile(new Position(i, j));// position redunduncy??
                } else if (n ==4) {
                    tiles[i][j] = new OceanTile(new Position(i, j));// position redunduncy??
                }
                
            }
        }
    }

    // /**
    //  * gets a tile in this map using an index not a position?
    //  * @param pos
    //  * @return
    //  */
    // public Tile getTile(Position pos){
    //     return findTileByPosition(pos);
    // }

    /**
     * get a tile using its position in this map only
     * @param pos the position of the wanted tile
     * @return the tile that is in the passed in position
     */
    public Tile findTileByPosition(Position pos){
        return tiles[pos.getXCoordinate()][pos.getYCoordinate()];
    }

    /**
     * gets the symbol for the passed in tile according to it' type
     * if Mountains it will return M for example
     * @param tile the tile to get the symbol of its type
     * @return the symbol of the type of the passed in tile
     */
    public char getTileTypeSymbol(Tile tile){
        if(tile.getTileType() == TileType.MOUNTAINS){
            return 'M';
        } else if (tile.getTileType() == TileType.DESERTS) {
            return 'D';
        } else if (tile.getTileType() == TileType.FORESTS) {
            return 'F';
        } else if (tile.getTileType() == TileType.PLAINS) {
            return 'P';
        } else if (tile.getTileType() == TileType.OCEAN) {
            return 'O';
        }
        return 'O';
    }

    /**
     * gets the first charcter of the owner of a tile in this map
     * ther could be ambiguity if two owners names start off with the same letter
     * @param tile what tile to get the symbol of the owner for
     * @return the first charcter of the name of the owner of a passed in tile
     */
    public char getOwnerSymbol(Tile tile){
        if (tile.getOwner() != null){
            return tile.getOwner().getName().charAt(0);
        }
        return '*';
    }

    /**
     * print out the map in an organized fashion
     */
    public void printMap(){
        // print the header
        System.out.println("*===* "+this.name+" *===*");
        System.out.print("*     ");
        for(int i = 0; i < width; i++){
            System.out.print(i + "         ");
        }
        System.out.println();

        // print the content of the map
        // [tileType, owner first letter, numberOfWorkers]
        char tileSymbol;
        char ownerSymbol;
        int nbWorkers;
        for(int j = 0; j < height; j++){
            System.out.print(j+" ");
            for(int i = 0; i < width; i++){
                tileSymbol = getTileTypeSymbol(tiles[i][j]);
                ownerSymbol = getOwnerSymbol(tiles[i][j]);
                nbWorkers = tiles[i][j].getNumberOfWorkers();
                System.out.print("["+tileSymbol+", "+ownerSymbol+", "+ + nbWorkers + "] ");
            }
            System.out.println();
        }
    }

    /**
     * get the height of this map
     * @return the height of this map
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * get the width of this map
     * @return the width of this map
     */
    public int getWidth(){
        return this.width;
    }

    
}