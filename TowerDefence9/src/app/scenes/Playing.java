package app.scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import app.game.Game;
import app.helps.LoadSave;
import app.ui.ActionBar;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	
	private ActionBar bottonBar;
	private int mouseX, mouseY;

	public Playing(Game game) {
		super(game);
		loadDefaultLevel();
		
		bottonBar = new ActionBar(0, 640, 640, 100, this);

	}

	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData("new_level");
	}
	
	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	@Override
	public void render(Graphics g) {

		drawLevel(g);
		bottonBar.draw(g);

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

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640) {
			bottonBar.mouseClicked(x, y);
		}

	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640) {
			bottonBar.mouseMoved(x, y);
		} else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;

		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640) {
			bottonBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bottonBar.mouseReleased(x, y);

	}

	@Override
	public void mouseDragged(int x, int y) {

	}

	

}
