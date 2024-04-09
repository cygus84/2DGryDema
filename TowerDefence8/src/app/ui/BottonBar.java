package app.ui;

import static app.game.GameStates.MENU;
import static app.game.GameStates.SetGameState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import app.objects.Tile;
import app.scenes.Playing;

public class BottonBar {

	private int x, y, width, height;
	private MyButton bMenu, bSave;
	private Playing playing;
	private ArrayList<MyButton> tileBottons;
	private Tile selectTile;

	public BottonBar(int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playing = playing;

		initButtons();
	}

	private void initButtons() {
		tileBottons = new ArrayList<MyButton>();
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
		bSave = new MyButton("Save", 2, 674, 100, 30);

		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);

		// new loop
		int i = 0;
		for (Tile tile : playing.getTileManager().tiles) {
			tileBottons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
			i++;
		}

	}

	private void drawButtons(Graphics g) {
		
		bMenu.draw(g);
		bSave.draw(g);
		drawTileButtons(g);
		drawSelectedTile(g);

	}

	private void drawSelectedTile(Graphics g) {
		if(selectTile != null) {
			g.drawImage(selectTile.getSprite(), 550, 650, 50, 50, null);
			g.setColor(Color.BLACK);
			g.drawRect(550, 650, 50, 50);
		}
		
	}

	private void drawTileButtons(Graphics g) {
		for (MyButton b : tileBottons) {

			// sprite
			g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);

			// mouseover
			if (b.isMouseOver()) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.BLACK);
			}
			// border
			g.drawRect(b.x, b.y, b.width, b.height);

			// mousepressed
			
			
			if (b.isMousePressed()) {
				g.drawRect(b.x + 1, b.y +1, b.width - 2, b.height - 2);
				g.drawRect(b.x + 2, b.y +2 , b.width - 4, b.height - 4);
			}

		}

	}

	public BufferedImage getButtImg(int id) {
		return playing.getTileManager().getSprite(id);
	}

	public void draw(Graphics g) {
		// background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Bottons
		drawButtons(g);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) 
			SetGameState(MENU);
		else if(bSave.getBounds().contains(x, y)) {
				saveLevel();
		} else {
			for (MyButton b : tileBottons) {
				if(b.getBounds().contains(x, y)) {
					selectTile = playing.getTileManager().getTile(b.getId());
					playing.setSelectedTile(selectTile);
					return;
				}
			}
		}

	}

	private void saveLevel() {
		playing.saveLevel();
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		for (MyButton b : tileBottons)
			b.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if(bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else {
			for (MyButton b : tileBottons) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}

	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else if(bSave.getBounds().contains(x, y))
			bSave.setMousePressed(true);
		else {
			for (MyButton b : tileBottons) {
				if(b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}		
			}
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bSave.resetBooleans();
		for(MyButton b : tileBottons) {
			b.resetBooleans();
		}
	}

}
