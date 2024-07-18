package app.panele;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import app.sterowanie.KeyHandler;

public class GamePanel extends JPanel implements Runnable{

	// SCRENN SETTINGS
	private final int orginalTileSize = 16; // 16x16 tile
	private final int scale = 3; // scalowanie postaci 3 x 16 = 48 pikselli
	
	private int tileSize = orginalTileSize * scale; //48x48 tile
	private final int maxScrennCol = 16;
	private final int maxScrennRow = 12;
	private final int ScrenWidth = tileSize * maxScrennCol; // 768 pixels
	private final int ScrenHeight = tileSize *maxScrennRow; // 576 pixels
	
	//FPS
	int FPS = 60;
	
	
	private KeyHandler keyH = new KeyHandler();
	private Thread gameThread;
	
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
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000 / FPS; // 0.01666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
					
			// 1 UPDATE: update information as character position
			update();
			// 2 DRAW: draw the screen with the update information
			repaint();
		}	
	}
	
	public void update() {
		
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		}else if(keyH.downPressed == true) {
			playerY += playerSpeed;
		} else if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}else if(keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
		
	}
	
	public void panitComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();
	}
}
