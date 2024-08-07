package app.scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import app.game.Game;
import app.ui.MyButton;
import static app.game.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

	private BufferedImage img;
	private ArrayList<BufferedImage> sprites;
	private Random random;
	private MyButton bPlaying, bSettings, bQuit;

	public Menu(Game game) {
		super(game);
		sprites = new ArrayList<BufferedImage>();

		random = new Random();
		importImg();
		loadSprites();
		initButtons();
		
	}

	private void initButtons() {
		
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffSet = 100;
		
		bPlaying = new MyButton("Play", x, y, w, h);
		
		bSettings = new MyButton("Settings", x, y + yOffSet, w, h);
		
		bQuit = new MyButton("Qiut", x, y + yOffSet * 2, w, h);
		
	}

	@Override
	public void render(Graphics g) {
		
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
		
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadSprites() {
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
			}
		}
	}

	private int getRandInt() {
		return random.nextInt(100);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		}else if(bSettings.getBounds().contains(x,y)) {
			SetGameState(SETTINGS);
		}else if(bQuit.getBounds().contains(x,y)) {
			System.exit(0);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);
		
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		}else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMouseOver(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}

		
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		}else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMousePressed(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
