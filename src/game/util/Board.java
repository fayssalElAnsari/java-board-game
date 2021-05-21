package game.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	
	private Tile[][] tiles;
	private String name;
	private int width;
	private int height;

		/**
	 * a constructor for the map containing all the tiles as type PLAIN
	 * @param name
	 */
	public void testBoard1() {
		this.name = "testBoard";
		this.width = 5;
		this.height = 5;
		this.tiles= new Tile[height][width];
		
		List<int[]> listNeighbour=new ArrayList<int[]>();
		
		for(int i = 0; i < this.width; i++) {
			for( int j = 0; j < this.height; i++) {
				tiles[i][j] = new Tile(Tiletype.PLAIN, i, j);
			
			
			//System.out.println(y+" "+x+" "+tiles[y][x]);
			//add neighbors to the list
			//south
			if(i+1<height) {
				if (tiles[i+1][j]==null) {
					int[] toadd= {i+1,j};
					listNeighbour.add(toadd);
				}
			}
			//north
			if(i-1>=0) {
				if (tiles[i-1][j]==null) {
					int[] toadd = {i-1,j};
					listNeighbour.add(toadd);
				}
			}
			//west
			if(j-1>=0) {
				if (tiles[i][j-1]==null) {
					int[] toadd= {i,j-1};
					listNeighbour.add(toadd);
				}
			}
			//east
			if(j+1<width) {
				if (tiles[i][j+1]==null) {
					int[] toadd= {j,j+1};
					listNeighbour.add(toadd);
				}
			}
			
			//supprime les doublons
			for(int k = 0;k<listNeighbour.size();k++) {
				if(tiles[listNeighbour.get(k)[0]][listNeighbour.get(k)[1]]!=null) {
					listNeighbour.remove(k);
					k--;
				}
				}
			}
		}
		for(int i =0; i<this.height;i++) {
			for(int j = 0; j<this.width;j++) {
				if(tiles[i][j]==null) {
					tiles[i][j]= new Tile(Tiletype.OCEAN,i,j);
				}
			}
		}
	}

	/**
	 * public constructor of the board in the game 
	 * @param name   the name of the board
	 * @param width  the width of the board
	 * @param height the height of the board
	 */

	public Board(String name, int width, int height ) {
		this.name = name;
		this.width = width;
		this.height = height;  

		/**
		 * the array of tiles of the board
		 */
		this.tiles= new Tile[height] [width];
		/* the total number of tiles in the board */
		int totalTiles = width * height;
		/* the total number of tiles tyoe oceans  in the board */	
		int nbOceans =(int)(totalTiles*0.6+1);
		
		/**
		 *random choice of starting tile to build the board 
		 *starting with 0 to the number of tiles who would be type ocean(2/3)
		 *and the remaining will be what ever type of tiles 
		 */
		Random r = new Random();
		/* the next x randomly */
		int x = r.nextInt(width);
		/* the next y randomly  */
		int y = r.nextInt(height);
		
		List<int[]> listNeighbour=new ArrayList<int[]>(); 
		
		for(int i =0; i<totalTiles-nbOceans;i++) {
			int type=r.nextInt(4);
			switch (type) {
			case 0: {
				tiles[y][x]= new Tile(Tiletype.MONTAIN,x,y);
				break;
				}
			case 1: {
				tiles[y][x]= new Tile(Tiletype.PLAIN,x,y);
				break;
				}
			case 2: {
				tiles[y][x]= new Tile(Tiletype.DESERT,x,y);
				break;
				}
			case 3: {
				tiles[y][x]= new Tile(Tiletype.WOOD,x,y);
				break;
				}
			}
			
			/*
			 * add neighbors  to the list
			 * in south
			 */

			if(y+1<height) {
				if (tiles[y+1][x]==null) {
					int[] toadd= {y+1,x};
					listNeighbour.add(toadd);
				}
			}
			/*
			 * in north
			 */
			if(y-1>=0) {
				if (tiles[y-1][x]==null) {
					int[] toadd= {y-1,x};
					listNeighbour.add(toadd);
				}
			}
			/*
			 * in west
			 */
			if(x-1>=0) {
				if (tiles[y][x-1]==null) {
					int[] toadd= {y,x-1};
					listNeighbour.add(toadd);
				}
			}
			/*
			 * in east
			 */
			if(x+1<width) {
				if (tiles[y][x+1]==null) {
					int[] toadd= {y,x+1};
					listNeighbour.add(toadd);
				}
			}
			
			/*
			 * remove duplicates 
			 */
			for(int k = 0;k<listNeighbour.size();k++) {
				if(tiles[listNeighbour.get(k)[0]][listNeighbour.get(k)[1]]!=null) {
					listNeighbour.remove(k);
					k--;
				}
			}
			int idx=r.nextInt(listNeighbour.size());
			y=listNeighbour.get(idx)[0];
			x=listNeighbour.get(idx)[1];
			listNeighbour.remove(idx);
			
		}
		for(int i =0; i<this.height;i++) {
			for(int j = 0; j<this.width;j++) {
				if(tiles[i][j]==null) {
					tiles[i][j]= new Tile(Tiletype.OCEAN,i,j);
				}
			}
		}
	}

	/**
	 * print out the board 
	 */	
	public void displayBoard() {
		System.out.print("    ");
		for(int j =0;j<this.width;j++) {
			System.out.print(j+"   ");
		}
		System.out.println("\n");
		for(int i=0;i<this.height;i++) {
			System.out.print(i+"   ");
			for(int j =0;j<this.width;j++) {
				System.out.print(tiles[i][j]);
			}
			System.out.println("");
		}
		
	}

	/**
	 * get the array of tiles 
	 * @return tiles
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * get the name of he board
	 * @return name : the name of he board
	 */
	public String getName() {
		return name;
	}

	/**
	 * get the width of the  board
	 * @return width : the idth of the board
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * get the height of the  board
	 * @return height : the height of the board
	 */
	public int getHeight() {
		return height;
	}
	
	
}
