 package app;

import javax.swing.JFrame;

import app.panele.GamePanel;

public class Start {

	public static JFrame windows;
	
	public static void main(String[] args) {
		// Object Detection Loot
		System.out.println("Moja 2D Gra-wersja-0-41 epizod 42");
		
		windows = new JFrame();
		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windows.setResizable(false);
		windows.setTitle("2D Przygoda");
		//windows.setUndecorated(true);
		
		GamePanel gamePanel =  new GamePanel();
		windows.add(gamePanel);
		
		gamePanel.config.loadConfig();
		if(gamePanel.fullScrenOn == true) {
			windows.setUndecorated(true);
		}
		
		windows.pack();
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
		windows.setLocationRelativeTo(null);
		windows.setVisible(true);
	}

}
