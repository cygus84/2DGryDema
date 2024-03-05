package app.sterowanie;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import app.panele.GamePanel;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed, entertPressed, shotKeyPressed, spacePressed;
	// debug
	public boolean checkDrawTime = false;

	public GamePanel gp;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		// titel state
		if (gp.gameState == gp.titleState) {
			tileState(code);
		}
		// this is a play state
		else if (gp.gameState == gp.playState) {
			playState(code);
		}
		// PAUSE STATE
		else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		// DIALOG STATE
		else if (gp.gameState == gp.dialogueState) {
			dialogeState(code);
		}
		// CHARACTER STATE
		else if (gp.gameState == gp.characterState) {
			characterState(code);
		}
		// OPTIONS STATE
		else if (gp.gameState == gp.optionState) {
			optionsState(code);
		}
		// GAME OVER STATE
		else if (gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}

		// TRADE STATE
		else if (gp.gameState == gp.tradeState) {
			tradeState(code);
		}
		// MAP STATE
				else if (gp.gameState == gp.mapState) {
					mapState(code);
				}
		
	}

	public void tileState(int code) {

		if (gp.ui.titleScreenState == 0) {
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			// dodanie wcisniecia entera aby aktywowac
			if (code == KeyEvent.VK_ENTER) {
				// start gry
				if (gp.ui.commandNum == 0) {
					gp.ui.titleScreenState = 1;
					gp.gameState = gp.playState;
					gp.playMusic(0);
				} // ladowanie
				if (gp.ui.commandNum == 1) {
					//e50
					gp.saveLoad.load();
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if (gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		// titel screnn 1
		else if (gp.ui.titleScreenState == 1) {
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			// dodanie wcisniecia entera aby aktywowac
			if (code == KeyEvent.VK_ENTER) {

				if (gp.ui.commandNum == 0) {
					System.out.println("Do sam fighter specific stuff!");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if (gp.ui.commandNum == 1) {
					gp.saveLoad.load();
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if (gp.ui.commandNum == 2) {
					System.out.println("Do sam sorcer specific stuff!");
					gp.gameState = gp.playState;
					// gp.playMusic(0);
				}
				if (gp.ui.commandNum == 3) {
					gp.ui.titleScreenState = 0;
				}
			}
		}
	}

	public void playState(int code) {
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}

		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}

		if (code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}

		if (code == KeyEvent.VK_ENTER) {
			entertPressed = true;
		}

		if (code == KeyEvent.VK_F) {
			shotKeyPressed = true;
		}

		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionState;
		}
		//map keay
		if (code == KeyEvent.VK_M) {
			gp.gameState = gp.mapState;
		}

		//map mini keay
		if (code == KeyEvent.VK_X) {
			if(gp.map.miniMapOn == false) {
				gp.map.miniMapOn = true;
			} else {
				gp.map.miniMapOn = false;
			}
		}
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		
		// debug
		if (code == KeyEvent.VK_T) {
			if (checkDrawTime == false) {
				checkDrawTime = true;
			} else if (checkDrawTime == true) {
				checkDrawTime = false;
			}
		} // brak dubuga mapy
	}

	public void pauseState(int code) {
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
	}

	public void dialogeState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			entertPressed = true;
		}
	}

	public void characterState(int code) {
		if (code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}

		// dodanie wybrania ewkipunku
		if (code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
		
		playerInventory(code);

	}

	public void optionsState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_ENTER) {
			entertPressed = true;
		}

		int maxComandNum = 0;
		switch (gp.ui.subState) {
		case 0:
			maxComandNum = 5;
			break;
		case 3:
			maxComandNum = 1;
			break;
		}

		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxComandNum;
			}
		}

		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if (gp.ui.commandNum > maxComandNum) {
				gp.ui.commandNum = 0;
			}
		}

		if (code == KeyEvent.VK_A) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.chceckVolume();
					gp.playMusic(9);
				}
				if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playMusic(9);
				}

			}
		}

		if (code == KeyEvent.VK_D) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.chceckVolume();
					gp.playMusic(9);
				}
				if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playMusic(9);
				}
			}
		}
	}

	public void gameOverState(int code) {

		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(9);
		}

		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if (gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(9);
		}

		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.resetGame(false);
				gp.playMusic(0);
			} else if (gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.resetGame(true);
			}
		}
	}

	public void tradeState(int code) {

		if (code == KeyEvent.VK_ENTER) {
			entertPressed = true;
		}

		if (gp.ui.subState == 0) {
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
				gp.playSE(9);
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 2) {
					gp.ui.commandNum = 2;
				}
				gp.playSE(9);
			}
		}
		
		if(gp.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
		
		if(gp.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
	}
	
	public void mapState(int code) {
		if(code == KeyEvent.VK_M) {
			gp.gameState = gp.playState;
		}
	}

	public void playerInventory(int code) {
		// dodanie poruszania sie po ewkipunku
		if (code == KeyEvent.VK_W) {
			if (gp.ui.playerSlotRow != 0) {
				gp.ui.playerSlotRow--;
				gp.playSE(9);
			}
		}

		if (code == KeyEvent.VK_A) {
			if (gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
				gp.playSE(9);
			}
		}

		if (code == KeyEvent.VK_S) {
			if (gp.ui.playerSlotRow != 3) {
				gp.ui.playerSlotRow++;
				gp.playSE(9);
			}
		}

		if (code == KeyEvent.VK_D) {
			if (gp.ui.playerSlotCol != 4) {
				gp.ui.playerSlotCol++;
				gp.playSE(9);
			}
		}
	}
	
	public void npcInventory(int code) {
		// dodanie poruszania sie po ewkipunku
		if (code == KeyEvent.VK_W) {
			if (gp.ui.npcSlotRow != 0) {
				gp.ui.npcSlotRow--;
				gp.playSE(9);
			}
		}

		if (code == KeyEvent.VK_A) {
			if (gp.ui.npcSlotCol != 0) {
				gp.ui.npcSlotCol--;
				gp.playSE(9);
			}
		}

		if (code == KeyEvent.VK_S) {
			if (gp.ui.npcSlotRow != 3) {
				gp.ui.npcSlotRow++;
				gp.playSE(9);
			}
		}

		if (code == KeyEvent.VK_D) {
			if (gp.ui.npcSlotCol != 4) {
				gp.ui.npcSlotCol++;
				gp.playSE(9);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}
		if (code == KeyEvent.VK_ENTER) {
			entertPressed = false;
		}
		
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
	}

}
