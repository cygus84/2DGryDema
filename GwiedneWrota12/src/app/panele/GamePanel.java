package app.panele;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import app.model.Player;
import app.narzedzia.AssetSetter;
import app.narzedzia.CollisionChecker;
import app.narzedzia.UI;
import app.objects.SuperObjects;
import app.sound.Sound;
import app.sterowanie.KeyHandler;
import app.tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	// SCRENN SETTINGS
	public final int orginalTileSize = 16; // 16x16 tile
	public final int scale = 3; // scalowanie postaci 3 x 16 = 48 pikselli
	
	public int tileSize = orginalTileSize * scale; //48x48 tile
	public final int maxScrennCol = 16;
	public final int maxScrennRow = 12;
	public final int ScrenWidth = tileSize * maxScrennCol; // 768 pixels
	public final int ScrenHeight = tileSize *maxScrennRow; // 576 pixels
	
	//WORLD MAP SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	
	//FPS
	int FPS = 60;
	
	// system
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler();
	public Sound music = new Sound();
	public Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public Thread gameThread;
	
	// entity and objects
	public Player player =  new Player(this, keyH);
	public SuperObjects obj[] = new SuperObjects[10];

	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(ScrenWidth, ScrenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
		
		playMusic(0);
	}


	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	public void run() {
		
		double drawInterval = 1000000000/ FPS; //0,1666
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime ) / drawInterval;
			timer += (currentTime - lastTime );
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if(timer > 1000000000) {
				System.out.println("FPS " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
		
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		// debug
		long drawStart = 0;
		if(keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();
		}
		
		// tile
		tileM.draw(g2);
		// object
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null ) {
				obj[i].draw(g2, this);
			}		
		}
		//player
		player.draw(g2);
		
		//UI
		ui.draw(g2);
		
		// debugin	
		if(keyH.checkDrawTime == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd = drawStart;
			g2.setColor(Color.WHITE);
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
		}	
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
		
		
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}
