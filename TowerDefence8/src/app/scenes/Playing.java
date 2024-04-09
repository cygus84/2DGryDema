package app.scenes;

import java.awt.Graphics;

import app.game.Game;
import app.helps.LevelBuild;
import app.helps.LoadSave;
import app.managers.TileManager;
import app.objects.Tile;
import app.ui.BottonBar;
import app.ui.MyButton;
import static app.game.GameStates.*;

public class Playing extends GameScene implements SceneMethods {
	
	private int[][] lvl;
	private TileManager tileManager;
	private Tile selectTile;
	private BottonBar bottonBar;
	private int mouseX, mouseY;
	private int lastTileX, lastTileY, lastTiledId;
	private boolean drawSelected;

	public Playing(Game game) {
		super(game);
		
		
		lvl = LevelBuild.getLevelData();
		tileManager = new TileManager();
		bottonBar = new BottonBar(0, 640, 640,100, this);
		
		//LoadSave.CreateFile();
		//LoadSave.WriteToFile();
		//LoadSave.ReadFromFile();
		
		createDefaultLvl();
		loadDefaukltLvl();
	}
	
	public void saveLevel() {
		LoadSave.SaveLevel("new_level", lvl);
		
	}
	
	private void loadDefaukltLvl() {
		lvl = LoadSave.GetLevelData("new_level");
	}

	private void createDefaultLvl() {
		int[] arr = new int[400];
		for(int i = 0; i < arr.length; i++)
			arr[i] = 0;
		LoadSave.CreatLevel("new_level", arr);
			
	}

	public TileManager getTileManager() {
		return tileManager;
	}
	

	@Override
	public void render(Graphics g) {
		
		for(int y = 0; y < lvl.length; y++) {
			for(int x = 0;x < lvl.length; x++) {
				int id = lvl[y][x];
				g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
			}
		}
		bottonBar.draw(g);
		drawSelectedTile(g);
	}

	

	private void drawSelectedTile(Graphics g) {
		if(selectTile != null && drawSelected) {
			g.drawImage(selectTile.getSprite(), mouseX, mouseY, 32, 32, null);
		}
		
	}
	
	public void setSelectedTile(Tile tile) {
		this.selectTile = tile;
		drawSelected = true;
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(y >= 640) {
			bottonBar.mouseClicked(x, y);
		} else {
			changeTile(mouseX, mouseY);
		}
		
	}

	private void changeTile(int x, int y) {
		if(selectTile != null) {
			int tileX = x / 32;
			int tileY = y / 32;
			if(lastTileX == tileX && lastTileY == tileY && lastTiledId == selectTile.getId())
				return;
			lastTileX = tileX;
			lastTileY = tileY;
			lastTiledId = selectTile.getId();
			lvl[tileY][tileX] = selectTile.getId();
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if(y >= 640) {
			bottonBar.mouseMoved(x, y);
			drawSelected = false;
		} else {
			drawSelected = true;
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
			
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(y >= 640) {
			bottonBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		if(y >= 640) {
			bottonBar.mouseReleased(x, y);
		}
	}

	@Override
	public void mouseDragged(int x, int y) {
		if(y >= 640) {
			
		} else {
			changeTile(x, y);
		}
		
	}

}
