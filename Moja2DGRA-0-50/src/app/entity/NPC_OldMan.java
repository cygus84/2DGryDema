package app.entity;

import java.awt.Rectangle;
import java.util.Random;

import app.panele.GamePanel;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
		dialogSet = -1;
		
		getImage();
		setDialog();
		
	}

	public void getImage() {
		
		up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
	}
	
	public void setDialog() {
		//e51 zmiana tablicy 
		dialogues[0][0] = "Hello, lad.";
		dialogues[0][1] = "So you come to this island to \n find the treasuer?";
		dialogues[0][2] = "I used to be  agreat wizard but now...\n Im a bit to old for taking am adveture.";
		dialogues[0][3] = "Well, good luck on you.";
		
		//e51
		dialogues[1][0] = "If you become tired, rest at the water";
		dialogues[1][1] = "However, the monster reappear if you rest.\n"
				+ "I dont  know why but thats how it works";
		dialogues[1][3] = "In any case, dont push yourself to hard.";
		
		dialogues[2][0] = "I wonder how to open that door...";
		
	}
	
	public void setAction() {
		
		if(onPath == true) {
			
//			int goalCol = 12;
//			int goalRow = 9;
			
		int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
		int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
			
			serachPath(goalCol, goalRow);
			
		} else {
			
			actionLockCounter++;
			
			if(actionLockCounter == 120) {
				Random random =  new Random();
				int i =  random.nextInt(100) + 1; // pick up o number from 1 to 100
				
				if (i <= 25) {
					direction = "up";
				}
				
				if (i > 25 && i <= 50) {
					direction = "down";
				}
				
				if (i > 50 && i <= 75) {
					direction = "left";
				}
				
				if (i > 75 && i <= 100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
			}	
		}
	}
	
	public void speak() {
		//super.speak();
		facePlayera();
		startDialogue(this, dialogSet);
		dialogSet++;
		
		if(dialogues[dialogSet][0] == null) {
			//dialogSet = 0; calosc powtarza rozmwoy
			dialogSet--; // ostatnia wypowied
		}
		
//		if(gp.player.life < gp.player.maxLife) {
//			
//		}
	}
}
