package app.ui;

import static app.game.GameStates.MENU;
import static app.game.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import app.scenes.Playing;

public class BottomBar {
	
	private MyButton bMenu;
	private int x, y, width, height;
	private Playing playing;
	private ArrayList<MyButton> tileButtons;

	public BottomBar(int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playing = playing;
		
		initButtons();
	}
	
	private void initButtons() {
		
		tileButtons = new ArrayList<>();
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
		
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);	
	}

	
	public void draw(Graphics g) {
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		drawButtons(g);
	}
	

	public void mouseClicked(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		}
		
	}

	
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		
	}

	
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
	}
	
}
