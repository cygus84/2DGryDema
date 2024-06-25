package app.scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import app.game.Game;
import app.helps.LoadSave;
import app.objects.Tile;
import app.ui.Toolbar;

public class Editing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private Tile selectTile;
	private int mouseX, mouseY;
	private int lastTileX, lastTileY, lastTiledId;
	private boolean drawSelected;
	private Toolbar toolbar;

	public Editing(Game game) {
		super(game);
		loadDefaultLvl();
		toolbar = new Toolbar(0, 640, 640, 100, this);	
	}
	
	private void loadDefaultLvl() {
		lvl = LoadSave.GetLevelData("new_level");
	}

	@Override
	public void render(Graphics g) {
		drawLevel(g);
		toolbar.draw(g);
		drawSelectedTile(g);
	}
	
	public void drawLevel(Graphics g) {
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl.length; x++) {
				int id = lvl[y][x];
				g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}
	
	private BufferedImage getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
	}
	
	private void drawSelectedTile(Graphics g) {
		if (selectTile != null && drawSelected) {
			g.drawImage(selectTile.getSprite(), mouseX, mouseY, 32, 32, null);
		}
	}
	
	public void saveLevel() {
		LoadSave.SaveLevel("new_level", lvl);
		game.getPlaying().setLevel(lvl);
	}

	
	public void setSelectedTile(Tile tile) {
		this.selectTile = tile;
		drawSelected = true;
	}

	private void changeTile(int x, int y) {
		if (selectTile != null) {
			int tileX = x / 32;
			int tileY = y / 32;
			if (lastTileX == tileX && lastTileY == tileY && lastTiledId == selectTile.getId())
				return;
			lastTileX = tileX;
			lastTileY = tileY;
			lastTiledId = selectTile.getId();
			lvl[tileY][tileX] = selectTile.getId();
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640) {
			toolbar.mouseClicked(x, y);
		} else {
			changeTile(mouseX, mouseY);
		}

	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640) {
			toolbar.mouseMoved(x, y);
			drawSelected = false;
		} else {
			drawSelected = true;
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;

		}

	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640) {
			toolbar.mousePressed(x, y);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {	
		toolbar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {
		if (y >= 640) {

		} else {
			changeTile(x, y);
		}

	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_R)
			toolbar.rotateSprite();
	}
}
