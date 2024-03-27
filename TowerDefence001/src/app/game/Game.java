package app.game;

import javax.swing.JFrame;

public class Game  extends JFrame{
	
	private GameSccreen gameScreen;
	
	public Game() {
		System.out.println("Start the game");
		
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		gameScreen = new GameSccreen();
		add(gameScreen);
		
	}

}
