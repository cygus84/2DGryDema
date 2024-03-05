package app.entity;

import app.object.OBJ_Axe;
import app.object.OBJ_Key;
import app.object.OBJ_Potion_Red;
import app.object.OBJ_Shield_Blue;
import app.object.OBJ_Shield_Wood;
import app.object.OBJ_Sword_Normal;
import app.panele.GamePanel;

public class NPC_Merchant extends Entity{
	
	
	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialog();
		setItems(); // nie zapomniec wywoalac metdoy 
	}

	public void getImage() {
		
		up1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
	}
	
	public void setDialog() {
		dialogues[0][0] = "Hello, lad.\n You found me\n I have some good stuff\n Do You want to trade?";
		dialogues[1][0] = "Come again, he he!";
		dialogues[2][0] = "You need more coin to bay that!";
		dialogues[3][0] = "You cannot carry any more!";	
		dialogues[4][0] = "You cannot sell an equpied item!";
		
	}
	
	public void setItems() {
		
		invetory.add(new OBJ_Potion_Red(gp));
		invetory.add(new OBJ_Key(gp));
		invetory.add(new OBJ_Sword_Normal(gp));
		invetory.add(new OBJ_Axe(gp));
		invetory.add(new OBJ_Shield_Wood(gp));
		invetory.add(new OBJ_Shield_Blue(gp));
		
	}
	
	public void speak() {
		
		facePlayera();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
		
	}
}
