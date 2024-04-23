package app.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import app.game.GameStates;

public class KeyboardListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			GameStates.gameState = GameStates.MENU;
			System.out.println("Key A is pressed!");
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			GameStates.gameState = GameStates.PLAYING;
			System.out.println("Key S is pressed!");
		}else if (e.getKeyCode() == KeyEvent.VK_D) {
			GameStates.gameState = GameStates.SETTINGS;
			System.out.println("Key D is pressed!");
		}else if (e.getKeyCode() == KeyEvent.VK_W) {
			System.out.println("Key W is pressed!");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
