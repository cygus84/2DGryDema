package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Axe extends Entity {

	public OBJ_Axe(GamePanel gp) {
		super(gp);

		type = type_axe;
		name = "Woodcuters' Axe";
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackArea.width = 30;
		attackArea.height = 30;
		attackValue = 2;
		description = "[Woodcuters' Axe]\n bit rusty bo still \n can cut some tree";
		price = 75;
		knockBackPower = 10;
		motion1_duration = 20;
		motion2_duration = 40;
	}

}
