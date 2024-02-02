package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Key extends Entity {

	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_consumable;
		name = "Key";
		down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\n It open the door.";
		price = 100;
		stackable = true;
	}
	
	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;
		
		int objIndex = getDetected(entity, gp.obj, "Door");
		
		if(objIndex != 999) {
			gp.ui.currentDialogue = "You use the " + name + " and open the dor";
			gp.playSE(3);
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "What are you doing?";
			return false;
		}
		
		
	}
}
