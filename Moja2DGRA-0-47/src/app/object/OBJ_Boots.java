package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Boots extends Entity {
	
	public OBJ_Boots(GamePanel gp) {
		super(gp);
		
		name = "Boots";
		down1 =  setup("/objects/boots", gp.tileSize, gp.tileSize);
		price = 100;
	}
}
