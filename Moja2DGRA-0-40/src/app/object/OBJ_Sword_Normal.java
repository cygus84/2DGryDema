package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Sword_Normal  extends Entity{

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = "Normale Sword";
		down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nAn old sword.";
		attackValue = 1;
		attackArea.width = 30;
		attackArea.height = 30;
		price = 100;
		knockBackPower = 2;
	}

}
