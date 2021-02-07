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
    // 

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

    // does the same as findTileByPosition for now
    public Tile getTile(Position pos){
        return findTileByPosition(pos);
    }

    public Tile findTileByPosition(Position pos){
        return tiles[pos.getXCoordinate()][pos.getYCoordinate()];
    }

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

    public char getOwnerSymbol(Tile tile){
        if (tile.getOwner() != null){
            return tile.getOwner().getName().charAt(0);
        }
        return '*';
    }

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

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    
}
