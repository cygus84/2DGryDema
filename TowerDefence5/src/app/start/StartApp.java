package app.start;

import app.game.Game;

public class StartApp {

	public static void main(String[] args) {
		System.out.println("Start Tower Defence 005");
		
		Game game = new Game();
		game.initInputs();
		game.start();

	}

}
