package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Lantern  extends Entity{

	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		type = type_light;
		name = "Lantern";
		down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
		description = "[Lantern]\nIlluminates your \nsurroundings.";
		price = 200;
		lightRadius = 250;
	}

}
