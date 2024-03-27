package app.game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameSccreen extends JPanel {
	
	public GameSccreen() {
		System.out.println("Game Screen start");
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		g.fillRect(50, 50, 100, 100);
		//g.drawRect(50, 50, 100, 100);
		
	}
}
