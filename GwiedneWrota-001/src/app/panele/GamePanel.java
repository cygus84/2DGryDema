package app.panele;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	// SCRENN SETTINGS
	private final int orginalTileSize = 16; // 16x16 tile
	private final int scale = 3; // scalowanie postaci 3 x 16 = 48 pikselli
	
	private int tileSize = orginalTileSize * scale; //48x48 tile
	private final int maxScrennCol = 16;
	private final int maxScrennRow = 12;
	private final int ScrenWidth = tileSize * maxScrennCol; // 768 pixels
	private final int ScrenHeight = tileSize *maxScrennRow; // 576 pixels
	
	private Thread gameThread;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(ScrenWidth, ScrenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
	}


	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		
		
	}
}
