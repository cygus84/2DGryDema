package app.narzedzia;

import java.awt.Rectangle;

import app.entity.Entity;
import app.panele.GamePanel;

public class EventHandler {

	public GamePanel gp;
	public EventRect eventRect[][][];
	
	public int previousEventX, previousEventY;
	public  boolean canTouchEvent = true;
	public int tempMap, tempCol, tempRow;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect =  new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		int map = 0;
		int col = 0;
		int row = 0;
		
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			eventRect[map][col][row] =  new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			
			if(col ==  gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
	}
	
	public void checkEvent() {
		
		// CHECK IF THE PLAYER IS MORE THAN 1 TILE FROM LAST EVENT
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
			
			// dotarl do puntu gdzie ubywa zycie 
			// 1 punkt na mapie
			if(hit( 0, 27, 16, "right") == true) {
				// event happen
				damagetPit(gp.dialogueState);
			}
			
			// 2 punkt na mapie
			else if(hit(0, 23, 19, "any") == true) {
				// event happen
				damagetPit(gp.dialogueState);
			}
			
			// TELEPORT 
			
			// dotarl do puntu gdzie ubywa zycie
//			if(hit(27, 16, "right") == true) {
//				// event happen
//				teleport(gp.dialogueState);
//			}
			

			// dotarcie do punktu gdzie zycie jest dodawane
			else if(hit(0, 23, 12, "up") == true) {
				healingPool(gp.dialogueState);
			}
			
			else if(hit(0 , 10, 39, "any") ==  true) {
				teleport(1, 12, 13);
			}
			
			else if(hit(1, 12, 13, "any") ==  true) {
				teleport(0, 10, 39);
			}
			else if(hit(1, 12, 9, "up") ==  true) {
				speak(gp.npc[1][0]);
			}
		}					
	}
	
	public boolean hit(int map,int col, int row, String regDirection) {
		
		boolean hit =  false;
		
		if(map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(regDirection) || regDirection.contentEquals("any")) {
					hit = true;
					// zmiany w evencie
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
					
				}
			}
			// reset 
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		
		
		return hit;
	}
	
	public void teleport(int map, int col, int row) {
		
		
		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		
//		gp.currentMap = map;
//		gp.player.worldX = gp.tileSize * col;
//		gp.player.worldY = gp.tileSize * row;
//		previousEventX = gp.player.worldX;
//		previousEventY = gp.player.worldY;
		canTouchEvent = false;
		gp.playSE(13);
		
	}
	
	public void speak(Entity entity) {
		
		if(gp.keyH.entertPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}
	
//	public void teleport(int col,int row,int gameState) {
//		gp.gameState = gameState;
//		gp.ui.currentDialogue = "Teleport";
//		gp.player.worldX = gp.tileSize * 37;
//		gp.player.worldY = gp.tileSize * 10;
//		
//	}
	
	public void damagetPit(int gameState) {
		gp.gameState = gameState;
		gp.playSE(6);
		gp.ui.currentDialogue = "You are in to a pit";
		gp.player.life -= 1;
//		eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void healingPool(int gameState) {
		if(gp.keyH.entertPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(2);
			gp.ui.currentDialogue = "You dring the water. \n You life and mana benn recovery.";
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			//eventRect[col][row].eventDone = true;
			gp.aSetter.setMonster();
		}
	}
	
	
}
