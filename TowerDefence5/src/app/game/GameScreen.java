package app.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.JPanel;

import app.render.Render;

public class GameScreen extends JPanel {

	private Dimension size;
	
	private Game game;

	public GameScreen(Game game) {
		System.out.println("Game Screen start");
		this.game = game;
		
		setPanelSize();


	}

	private void setPanelSize() {
		size = new Dimension(640, 640);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.getRender().render(g);
		}		
	
}
