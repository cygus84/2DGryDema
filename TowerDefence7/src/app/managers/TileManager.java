package app.managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import app.helps.LoadSave;
import app.objects.Tile;

public class TileManager {
	
	public Tile GRASS, WATER, ROAD;
	public BufferedImage atlas;
	public ArrayList<Tile> tiles;
	
	public TileManager() {
		
		tiles = new ArrayList<Tile>();
		
		loadAtlas();
		createTiles();
		
	}

	private void createTiles() {
	
		tiles.add(GRASS = new Tile(getSprite(8, 1)));// id 0
		tiles.add(WATER = new Tile(getSprite(0, 6)));// id 1 etc
		tiles.add(ROAD = new Tile(getSprite(9, 0)));
		
	}

	private void loadAtlas() {
		atlas = LoadSave.getSpriteAtlas();
	}
	
	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}
	
	public BufferedImage getSprite(int x,int y) {
		return atlas.getSubimage(x * 32,y * 32, 32, 32);
	}

}
