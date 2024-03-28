package app.scenes;

import java.awt.Graphics;

import app.game.Game;
import app.helps.LevelBuild;
import app.managers.TileManager;
import app.ui.BottomBar;
import app.ui.MyButton;
import static app.game.GameStates.*;

public class Playing extends GameScene implements SceneMethods {
	
	private int[][] lvl;
	private TileManager tileManager;
	
	private BottomBar bottomBar;

	public Playing(Game game) {
		super(game);
		
		
		lvl = LevelBuild.getLevelData();
		tileManager = new TileManager();
		bottomBar = new BottomBar(0,640, 640, 100);
		
	}

	

	@Override
	public void render(Graphics g) {
		
		for(int y = 0; y < lvl.length; y++) {
			for(int x = 0;x < lvl.length; x++) {
				int id = lvl[y][x];
				g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
			}
		}
		
		
		bottomBar.draw(g);
	}

	
	@Override
	public void mouseClicked(int x, int y) {
		
		if(y >= 640) {
			bottomBar.mouseClicked(x, y);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
	
		if(y >= 640) {
			bottomBar.mouseMoved(x, y);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(y >= 640) {
			bottomBar.mousePressed(x, y);
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		
			bottomBar.mouseReleased(x, y);
	}

}
