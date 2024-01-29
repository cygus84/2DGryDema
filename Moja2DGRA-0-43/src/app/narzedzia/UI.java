package app.narzedzia;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import app.entity.Entity;
import app.object.OBJ_Coin_Bronze;
import app.object.OBJ_Heart;
import app.object.OBJ_ManaCrystal;
import app.panele.GamePanel;

public class UI {

	public GamePanel gp;
	public Graphics2D g2;
	public Font arial_40, arial_80B, pusriaB;
	// dodanie obrazkow zycia
	public BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
	//doanie widomosci 
	public boolean messageOn = false;
//	public String message = "";
//	public int mesageCounter = 0;
	public ArrayList<String> message =  new ArrayList<>();
	public ArrayList<Integer> messageCounter = new ArrayList<>();;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0 is the firest screen , 1 is teh 2 screen
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	public int subState = 0;
	public int counter = 0;
	public Entity npc;
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		InputStream is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
		try {
			pusriaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arial_80B = new Font("Arial", Font.BOLD, 80);
		//OBJ_Key key = new OBJ_Key(gp);
		//keyImage = key.image;
		
		// CREAT HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		// dodanie krysztalu many
		Entity crytal = new OBJ_ManaCrystal(gp);
		crystal_full = crytal.image;
		crystal_blank = crytal.image2;
		Entity bronzeCoin = new OBJ_Coin_Bronze(gp);
		coin = bronzeCoin.down1;
		
	}
	
	public void addMessage(String text) {
//		message = text;
//		messageOn = true;
		
		message.add(text);
		messageCounter.add(0);
		
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(pusriaB);
		g2.setColor(Color.WHITE);
		
		// Title state
		if(gp.gameState == gp.titleState) {
			drawTtitleScreen();
		}
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			// do playStaff:
			// zycie
			drawPlayerLife();
			drawMessage();
		}
		// PUASE STATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// DIALOG STATE
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}	
		//CHRACTER STATE
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		// OPTIONS STATE
		if(gp.gameState == gp.optionState) {
			drawOptionsScreen();
		}
		
		// GAME OVER  STATE
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		
		// TRANSITION STATE
		if(gp.gameState == gp.transitionState) {
			drawTransitionScreen();
		}
		
		// TRADE STATE
				if(gp.gameState == gp.tradeState) {
					drawTradeScreen();
				}
	}
	
	public void drawPlayerLife() {
		// testowy parametr do sprawdzenia
		//gp.player.life = 3;
		// DRAW BLANK HEART
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;
		// DRAW MAX LIFE
		while(i < gp.player.maxLife / 2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		// RESET
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;
		
		// DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if( i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		// DRAW  MAX MANA 
		x = (gp.tileSize /2) -5;
		y =(int)(gp.tileSize * 1.5);
		i = 0;
		while(i < gp.player.maxMana) {
			g2.drawImage(crystal_blank, x, y, null);
			i++;
			x += 35;
		}
		// DRWA MANA
		x = (gp.tileSize /2) -5;
		y =(int)(gp.tileSize * 1.5);
		i = 0;
		while(i < gp.player.mana) {
			g2.drawImage(crystal_full, x, y, null);
			i++;
			x += 35;
		}	
	}
	
	public void drawMessage() {
		// kordynaty wiadomosci 
		int messageX = gp.tileSize;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
		
		for(int i = 0; i < message.size();i ++) {
			if(message.get(i) != null) {
				g2.setColor(Color.BLACK);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				g2.setColor(Color.WHITE);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;// msessage counter ++
				messageCounter.set(i, counter);
				messageY += 50;
				// dodanie znikniecia po czasie 
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
				
			}
		}
		
	}
	
	public void drawTtitleScreen() {
		
		if(titleScreenState == 0) {
			
			// title name
			g2.setColor(new Color(0, 0, 0)); // tlo zmiana koloru
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
			String text = "Moja 2D przygoda";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;
			// shadow do napisow
			g2.setColor(Color.GRAY);
			g2.drawString(text, x + 5, y + 5);
			// main color
			g2.setColor(Color.WHITE);
			g2.drawString(text, x, y);
			
			//blue boy image
			x = gp.screenWidth / 2 - (gp.tileSize * 2) /2;
			y += gp.tileSize * 2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
			
			// menu
			// new game
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize * 3;
			g2.drawString(text, x, y);
			// strzalka wyboru
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			// load game
			text = "LOAD GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize * 1;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			// QUIT
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize * 1;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}	
		} else if (titleScreenState == 1) {
			// class selection screen
			g2.setColor(Color.WHITE);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Select you class!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 2;
			g2.drawString(text, x, y);
			
			text = "Fighter";
			x = getXforCenteredText(text);
			y += gp.tileSize * 2;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Thief";
			x = getXforCenteredText(text);
			y += gp.tileSize* 2;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Sorcer";
			x = getXforCenteredText(text);
			y += gp.tileSize* 2;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize* 2;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
			}
		}

		
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight /2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		// window
		int x = gp.tileSize * 3;
		int y = gp.tileSize / 2;
		int widht = gp.screenWidth - (gp.tileSize * 6);
		int height = gp.tileSize * 4;
		drawSubWindow(x, y, widht, height);
	
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
		
	}
	
	public void drawCharacterScreen() {
		// CREATA A FRAME
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize * 10;
		// wywwolanie metody rysowaania
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(20f));
		//g2.setFont(arial_40);
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Mana", textX, textY);
		textY += lineHeight;
		g2.drawString("Strenght", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("AttaCK", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 10;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;
		
		// VALUE
		int taliX = (frameX + frameWidth) - 30;
		// RESET TEXT Y
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strenght);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAllghtToRightText(value, taliX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		// dodanie obrazkow miecza i tarczy
		// miecz
		g2.drawImage(gp.player.currentWeapon.down1, taliX - gp.tileSize, textY - 14, null);
		textY += gp.tileSize;
		// tarcza
		g2.drawImage(gp.player.currentShield.down1, taliX - gp.tileSize, textY - 24, null);
		textY += gp.tileSize;
		
		
	}
	
	public void drawInventory(Entity entity, boolean cursor) {
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if(entity == gp.player) {
			// FRAME SLOT
			frameX = gp.tileSize * 12;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		} else {
			// FRAME SLOT
			frameX = gp.tileSize * 2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;	
		}
		
		 // DRAW FRAME
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;
		
		// DRAW PLAYER ITEMS
		for(int i = 0; i < entity.invetory.size(); i++) {
			
			//EQUIP CURSOR
			if(entity.invetory.get(i) == entity.currentWeapon
			   || entity.invetory.get(i) == entity.currentShield) {
				
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(entity.invetory.get(i).down1, slotX, slotY, null);
			
			// DISPLAY AMOUNT
			if(entity == gp.player && entity.invetory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(25F));
				int amountX;
				int amountY;
				
				String s = "" + entity.invetory.get(i).amount;
				amountX = getXforAllghtToRightText(s, slotX + 44);
				amountY = slotY + gp.tileSize;
				
				// SHADOW
				g2.setColor(new Color(60, 60, 60));
				g2.drawString(s, amountX, amountY);
				// NUMBER
				g2.setColor(Color.white);
				g2.drawString(s, amountX -3, amountY - 3);
					
			}
			
			slotX += slotSize;
					
			// czas 16:50
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
			
		}
		
		// CURSOR
		if(cursor == true) {
			int cursorX = slotXstart + (slotSize * slotCol);
			int cursorY = slotYstart + (slotSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			
			// DRAW CURSOR
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(3)); // zmniejszenie obwudki
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			
			// DESCRIPTION FRAME;
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize * 3;
			
			// DRAW DESCRIPTION TEXT
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(20f));
			
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			if (itemIndex < entity.invetory.size()) {
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);// przniesienie ze by znikalo
				// dodanie rozdzielenialini
				for(String line: entity.invetory.get(itemIndex).description.split("\n")) {
					g2.drawString(line, textX, textY);
					textY += 32;
				}
			}
		}
	}
	
	public void drawOptionsScreen() {
		
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(20f));
		
		// SUB WINDOWS
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: option_Top(frameX, frameY); break;
		case 1: options_fullScreenNotification(frameX, frameY); break;
		case 2: options_control(frameX, frameY); break;
		case 3: options_endGameConformation(frameX, frameY); break;
		}
		// reset entera
		gp.keyH.entertPressed = false;
		
	}
	
	public void option_Top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// Title
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		// FULL SCREEN ON/OFF
		textX = frameX + gp.tileSize;
		textY += gp.tileSize * 2;
		g2.drawString("Full Screen", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.entertPressed == true) {
				if(gp.fullScrenOn == false) {
					gp.fullScrenOn = true;
				} else if(gp.fullScrenOn == true) {
					gp.fullScrenOn = false;
				}
				subState = 1;
			}	
		}
		
		// MUSIC
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}
		// SE
		textY += gp.tileSize;
		g2.drawString("SE", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
			
		}
		//CONTORL
		textY += gp.tileSize;
		g2.drawString("Control", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.entertPressed == true) {
				subState = 2;
				commandNum = 0;
				
			}
		}
		// END GAME
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.entertPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		// BACK
		textY += gp.tileSize * 2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.entertPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
				
			}
		}
		
		// FULL SCREEN CHECK BOX
		textX = frameX + (int)(gp.tileSize * 4.5);
		textY = frameY + gp.tileSize * 2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect( textX, textY, 24, 24);
		if(gp.fullScrenOn == true) {
			g2.fillRect(textX, textY, 24, 24);;
		}
		
		// MUSIC VOLUME
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24); // 120 / 5 = 24
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		// SE VOLUME
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		//dodanie zapisu po zmianach
		gp.config.saveConfig();
		
		
	}
	
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0, 0, 0,150 ));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f)); // BYLO 110 ALE ZMIENIAM NA 70
		
		text = "GAME OVER";
		// SHADOW
		g2.setColor(Color.BLACK);
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.drawString(text, x, y);
		// MAIN
		g2.setColor(Color.WHITE);
		g2.drawString(text, x - 4, y - 4);
		// RETRY
		g2.setFont(g2.getFont().deriveFont( 50f)); // BYLO 50 ZMIENIAM NA 30
		text = "RETRY";
		x = getXforCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}
		
		
		// BACK TO TITLE SCREEN
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
		
	}
	
	public void options_fullScreenNotification(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;
		
		currentDialogue = "The change will take\n effect after the \nrestarting the game.";
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// BACK
		textY = frameY + gp.tileSize * 9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX -25, textY);
			if(gp.keyH.entertPressed == true) {
				subState = 0;
			}
		}
		
	}
	
	public void options_control(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//TITLE
		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Confirm/Attack", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Shoot/Cast", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Character screen", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Pause", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Options", textX, textY);
		textY += gp.tileSize;
		
		
		textX = frameX + gp.tileSize * 6;
		textY = frameY + gp.tileSize * 2;
		
		g2.drawString("WASD", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY);
		textY += gp.tileSize;
		g2.drawString("F", textX, textY);
		textY += gp.tileSize;
		g2.drawString("C", textX, textY);
		textY += gp.tileSize;
		g2.drawString("P", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ESC", textX, textY);
		textY += gp.tileSize;
		
		// BACK BUTON
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize * 9;
		g2.drawString("BACK", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.entertPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	
	public void options_endGameConformation(int frameX, int frameY ) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;
		
		currentDialogue = "Quit the game and \nreturn to the title screen?";
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.tileSize * 3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.entertPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
			}
		}
		
		
		// NO
		text = "No";
		textX = getXforCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.entertPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}	
	}
	
	public void drawTransitionScreen() {
		
		counter++;
		g2.setColor(new Color(0, 0, 0, counter * 5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		if(counter == 50) {
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
			gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
		}
		
	}
	
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0: trade_select(); break;
		case 1: trade_bay(); break;
		case 2: trade_sell(); break;
		}
		gp.keyH.entertPressed = false; // reset enetera
	}
	
	public void trade_select() {
		
		drawDialogueScreen();
		
		// DRAW WINDOWS
		int x = gp.tileSize * 15;
		int y = gp.tileSize * 4;
		int width = gp.tileSize * 3;
		int height = (int)(gp.tileSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		// DRAW TEXT
		x += gp.tileSize;
		y += gp.tileSize;
		
		g2.drawString("Bay", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - 24, y);
			if(gp.keyH.entertPressed == true) {
				subState = 1;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Sell", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 24, y);
			if(gp.keyH.entertPressed == true) {
				subState = 2;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Leave", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - 24, y);
			if(gp.keyH.entertPressed == true) {
				commandNum = 0;
				gp.gameState = gp.dialogueState;
				currentDialogue = "Come again, he he!";
			}
		}
	}
	
	public void trade_bay() {
		
		// DRAW PLAYER INVENTORY
		drawInventory(gp.player, false);
		// DRAW NPC INVENTORY
		drawInventory(npc, true);
		
		// DRAW HIT WINDOWS
		int x = gp.tileSize * 2;
		int y = gp.tileSize * 9;
		int width = gp.tileSize * 6;
		int height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Back", x + 24, y + 60);
		
		// PLAYER COIN WINDOWS
		 x = gp.tileSize * 12;
		 y = gp.tileSize * 9;
		 width = gp.tileSize * 6;
		 height = gp.tileSize * 2;
		 drawSubWindow(x, y, width, height);
		 g2.drawString("Your Coin: " + gp.player.coin, x + 24, y + 60);
		 
		 // DRAW PRICE WINDOWS
		 int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow); 
		 if(itemIndex < npc.invetory.size()) {
			 x = (int)(gp.tileSize * 5.5);
			 y = (int)(gp.tileSize * 5.5);
			 width =(int) (gp.tileSize * 2.5);
			 height = gp.tileSize;
			 drawSubWindow(x, y, width, height);
			 g2.drawImage(coin, x + 10, y + 8, 32, 32, null); 
			 
			 int price = npc.invetory.get(itemIndex).price; // pobranie z npc ceny produktu
			 String text = "" + price;
			 x = getXforAllghtToRightText(text, gp.tileSize * 8 - 20);
			 g2.drawString(text, x, y + 34);
			 
			 // BAY AN ITEM
			 if(gp.keyH.entertPressed == true) {
				 if(npc.invetory.get(itemIndex).price > gp.player.coin) {
					 subState = 0;
					 gp.gameState = gp.dialogueState;
					 currentDialogue = "You need more coin to bay that!";
					 drawDialogueScreen();
				 }
				 else {
					 if(gp.player.canObtainItem(npc.invetory.get(itemIndex)) == true) {
						 gp.player.coin -= npc.invetory.get(itemIndex).price;
					 }
					 else {
						 subState = 0;
						 gp.gameState = gp.dialogueState;
						 currentDialogue = "You cannot carry any more!";
					 }
				 }
			 } 
		 }
	}
	
	public void trade_sell() {
		// DRAW PALYER ITEMS
		drawInventory(gp.player, true);
		// DRAW HIT WINDOWS
		int x;
		int y;
		int width;
	    int height;
		
	 // DRAW HIT WINDOWS
	 		x = gp.tileSize * 2;
	 		y = gp.tileSize * 9;
	 		width = gp.tileSize * 6;
	 		height = gp.tileSize * 2;
	 		drawSubWindow(x, y, width, height);
	 		g2.drawString("[ESC] Back", x + 24, y + 60);
	 		
	 		// PLAYER COIN WINDOWS
	 		 x = gp.tileSize * 12;
	 		 y = gp.tileSize * 9;
	 		 width = gp.tileSize * 6;
	 		 height = gp.tileSize * 2;
	 		 drawSubWindow(x, y, width, height);
	 		 g2.drawString("Your Coint: " + gp.player.coin, x + 24, y + 60);
	 		 
	 		 // DRAW PRICE WINDOWS
	 		 int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow); 
	 		 if(itemIndex < gp.player.invetory.size()) {
	 			 x = (int)(gp.tileSize * 15.5);
	 			 y = (int)(gp.tileSize * 5.5);
	 			 width =(int) (gp.tileSize * 2.5);
	 			 height = gp.tileSize;
	 			 drawSubWindow(x, y, width, height);
	 			 g2.drawImage(coin, x + 10, y + 8, 32, 32, null); 
	 			 
	 			 int price = gp.player.invetory.get(itemIndex).price / 2; // pobranie z npc ceny produktu
	 			 String text = "" + price;
	 			 x = getXforAllghtToRightText(text, gp.tileSize * 18 - 20);
	 			 g2.drawString(text, x, y + 34);
	 			 
	 			 // SELL AN ITEM
	 			 if(gp.keyH.entertPressed == true) {
	 				if(gp.player.invetory.get(itemIndex) == gp.player.currentWeapon ||
	 						gp.player.invetory.get(itemIndex) == gp.player.currentShield){
	 							commandNum = 0;
	 							subState = 0;
	 							gp.gameState = gp.dialogueState;
	 							currentDialogue = "You cannot sell an equpied item!";
	 						} else {
	 							if(gp.player.invetory.get(itemIndex).amount > 1) {
	 								gp.player.invetory.get(itemIndex).amount--;
	 							} else {
	 								gp.player.invetory.remove(itemIndex);
		 							
	 							}
	 							gp.player.coin += price;
	 						}
	 			 } 
	 		 }
	}
	
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height- 10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth /2 - lenght /2;
		return x;
	}
	
	public int getXforAllghtToRightText(String text, int tailX) {
		int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - lenght;
		return x;
	}
}
