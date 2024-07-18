package app;

import javax.swing.JFrame;

import app.panele.GamePanel;

public class StartApp {

	public static void main(String[] args) {
		System.out.println("Gwiezdne Wrota-002");
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Gwiezdne Wrota");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();

	}

}
