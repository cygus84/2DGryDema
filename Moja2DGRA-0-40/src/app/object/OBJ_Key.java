package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Key extends Entity {

	public OBJ_Key(GamePanel gp) {
		super(gp);

		name = "Key";
		down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\n It open the door.";
		price = 100;
	}
}
