package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Heart extends Entity {

	private GamePanel gp;
	
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_pickupOnly;
		value = 2;
		name = "Heart";
		down1 = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
		image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
		image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);
	}
	
	public boolean use(Entity entity) {
		gp.playSE(2);
		gp.ui.addMessage("Life +) " + value);
		entity.life += value;
		return true;
	}
}
 