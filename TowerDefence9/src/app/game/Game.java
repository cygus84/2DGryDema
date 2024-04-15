package app.game;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import app.helps.LoadSave;
import app.inputs.KeyboardListener;
import app.inputs.MyMouseListener;
import app.managers.TileManager;
import app.render.Render;
import app.scenes.Editing;
import app.scenes.Menu;
import app.scenes.Playing;
import app.scenes.Settings;

public class Game extends JFrame implements Runnable {

	public GameScreen gameScreen;
	private Thread gameThread;
	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;
	private Editing editing;
	private TileManager tileManager;

	public Game() {
		System.out.println("Start the game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		initClasses();
		createDefaultLvl();

		add(gameScreen);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void createDefaultLvl() {
		int[] arr = new int[400];
		for(int i = 0; i < arr.length; i++)
			arr[i] = 0;
		LoadSave.CreatLevel("new_level", arr);
			
	}
	
	private void initClasses() {
		tileManager = new TileManager();
		render = new Render(this);
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
		editing = new Editing(this);
		
	}



	public void updateGame() {
	}

	public void start() {
		gameThread = new Thread(this) {
		};
		gameThread.start();
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000 / FPS_SET;
		double timePerUpdate = 1000000000 / UPS_SET;
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		int frames = 0;
		long lastTimeCheck = System.currentTimeMillis();
		int updates = 0;
		long now;

		while (true) {
			now = System.nanoTime();
			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				frames++;
				lastFrame = now;
			}
			// Update
			if (now - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = now;
				updates++;
			}
			// checking FPS and UPS
			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				//System.out.println("FPS: " + frames + " UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}

	}
	
	public Render getRender() {
		return render;		
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}
	
	public Editing getEditor() {
		return editing;
	}
	
	public TileManager getTileManager() {
		return tileManager;
	}
}
