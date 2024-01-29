package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Potion_Red extends Entity {
	
	private GamePanel gp;
	
	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		value = 5;
		type = type_consumable;
		name = "Red Potion";
		down1 =setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		description = "[Red Potion]\n Heal You life by " + value + ".";
		price = 25;
	}
	// dodanie metody do uzycia
	public void use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drink the " + name + "!\n"
				+ "You life was recover by " + value + ".";
		entity.life += value;
		gp.playSE(2);
	}

}
