package app.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.JPanel;

public class GameScreen extends JPanel {

	private Random random;
	private BufferedImage img;
	private ArrayList<BufferedImage> sprites;


	public GameScreen(BufferedImage img) {
		System.out.println("Game Screen start");
		this.img = img;

		sprites = new ArrayList<BufferedImage>();
		loadSprites();
		random = new Random();
		
		
		

	}

	private void loadSprites() {
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 20; x++) {
				g.drawImage(sprites.get(getRandInt()), x * 32 , y * 32 , null);
			}
		}
		
		
	}
	

	private int getRandInt() {
		return random.nextInt(100);
	}
	
	private Color getRandColor() {
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		return new Color(r, g, b);
	}
}
