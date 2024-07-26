package app;

import javax.swing.JFrame;

import app.panele.GamePanel;

public class StartApp {

	public static void main(String[] args) {
		System.out.println("Gwiezdne Wrota-008");
		// kolizja i reakcja objetu na kolizje 
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setTitle("Gwiezdne Wrota");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		gamePanel.setupGame();
		gamePanel.startGameThread();
		window.setVisible(true);

	}

}
