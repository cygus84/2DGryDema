package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Shield_Wood extends Entity {

	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);

		type = type_shield;
		name = "Wood Shield";
		down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
		defenseValue = 1;
		description = "[" + name + "]\n Made by wood.";
		price = 375;
	}

}
