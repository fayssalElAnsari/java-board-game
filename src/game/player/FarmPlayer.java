package game.player;

import java.util.HashMap;
import java.util.List;

import game.util.Tile;
import game.util.Tiletype;

public class FarmPlayer extends Player {

	private HashMap<String, Integer>ressources;
	private int goldDistrib=0;
	public FarmPlayer(String name) {
		super(name);
		this.addGolds(15);
		this.ressources= new HashMap<String, Integer>();
		this.ressources.put(Tiletype.MONTAIN.name(), 0);
		this.ressources.put(Tiletype.DESERT.name(), 0);
		this.ressources.put(Tiletype.PLAIN.name(), 0);
		this.ressources.put(Tiletype.WOOD.name(), 0);
		
	}

	public HashMap<String, Integer> getRessources() {
		return ressources;
	}

	public void payDay() {
		List<Tile> tiles =this.getTileOwned();
		for (int i = 0; i<tiles.size();i++ ) {
			Tile tile = tiles.get(i);
			switch(tile.getType()) {
			case MONTAIN:{
				if(this.getGolds()>=5) {
					this.addGolds(-5);
					this.goldDistrib+=5;
				}else {
					this.lostTile(tile);
				}
			}
			case DESERT:{
				if(this.getGolds()>=3) {
					this.addGolds(-3);
					this.goldDistrib+=3;
				}else {
					this.lostTile(tile);
				}
			}
			case WOOD:{
				if(this.getGolds()>=1) {
					this.addGolds(-1);
					this.goldDistrib+=1;
				}else {
					this.lostTile(tile);
				}
			}
			case PLAIN:{
				if(this.getGolds()>=1) {
					this.addGolds(-1);
					this.goldDistrib+=1;
				}else {
					this.lostTile(tile);
				}
			}
			}
		}
	}
	
	public void tradeRessources() {
		System.out.println("trading ressources");
		this.addGolds(ressources.get(Tiletype.MONTAIN.name())*8);
		this.ressources.replace(Tiletype.MONTAIN.name(), 0);
		
		this.addGolds(ressources.get(Tiletype.PLAIN.name())*2);
		this.ressources.replace(Tiletype.PLAIN.name(), 0);

		this.addGolds(ressources.get(Tiletype.WOOD.name())*2);
		this.ressources.replace(Tiletype.WOOD.name(), 0);
		
		this.addGolds(ressources.get(Tiletype.DESERT.name())*5);
		this.ressources.replace(Tiletype.DESERT.name(), 0);
	}
	
	public void recolteRessources() {
		for(int i =0; i<this.getTileOwned().size();i++) {
			Tile currentTile = this.getTileOwned().get(i);
			switch (currentTile.getType()) {
			case MONTAIN: {
				this.ressources.put(Tiletype.MONTAIN.name(), this.ressources.get(Tiletype.MONTAIN.name())+1);
				break;
			}
			case DESERT: {
				this.ressources.put(Tiletype.DESERT.name(), this.ressources.get(Tiletype.DESERT.name())+1);
				break;
			}
			case PLAIN: {
				this.ressources.put(Tiletype.PLAIN.name(), this.ressources.get(Tiletype.PLAIN.name())+1);
				break;
			}
			case WOOD: {
				this.ressources.put(Tiletype.WOOD.name(), this.ressources.get(Tiletype.WOOD.name())+1);
				break;
			}
				default:
					System.out.println("");
			}
		}
	}

	public int getGoldDistrib() {
		return goldDistrib;
	}
}
