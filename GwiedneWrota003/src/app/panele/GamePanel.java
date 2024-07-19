package app.panele;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import app.model.Player;
import app.sterowanie.KeyHandler;

public class GamePanel extends JPanel implements Runnable{

	// SCRENN SETTINGS
	private final int orginalTileSize = 16; // 16x16 tile
	private final int scale = 3; // scalowanie postaci 3 x 16 = 48 pikselli
	
	public int tileSize = orginalTileSize * scale; //48x48 tile
	private final int maxScrennCol = 16;
	private final int maxScrennRow = 12;
	private final int ScrenWidth = tileSize * maxScrennCol; // 768 pixels
	private final int ScrenHeight = tileSize *maxScrennRow; // 576 pixels
	
	//FPS
	int FPS = 60;
	
	
	public KeyHandler keyH = new KeyHandler();
	private Thread gameThread;
	public Player player =  new Player(this, keyH);
	
	// Ste palyer default position
	private int  playerX = 100;
	private int  playerY = 100;
	private int  playerSpeed = 4;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(ScrenWidth, ScrenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}


	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
//	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000 / FPS; // 0.01666 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null) {
//						
//			// 1 UPDATE: update information as character position
//			update();
//			// 2 DRAW: draw the screen with the update information
//			repaint();
//			
//			
//			try {
//				double remaningTime = nextDrawTime - System.nanoTime();
//				remaningTime = remaningTime / 1000000;
//				
//				if(remaningTime < 0) {
//					remaningTime = 0;
//				}
//				
//				Thread.sleep((long)remaningTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		}	
//	}
	
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
		
		player.draw(g2);
		
		g2.dispose();
	}
}
