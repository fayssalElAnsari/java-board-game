package devAgricole.game.util;

import devAgricole.game.util.tile.DesertsTile;
import devAgricole.game.util.tile.TileType;

public class Map {
    private Tile[][] tiles;
    private String name;
    private int width;
    private int height;

    public Map(String name, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                tiles[i][j] = new DesertsTile(new Position(i, j));// position redunduncy??
            }
        }
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
        } else {
            return 'O';
        }
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
            System.out.print(i + "        ");
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

    
}
