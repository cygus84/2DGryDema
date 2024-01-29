package app.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Chest extends Entity {

	public OBJ_Chest(GamePanel gp) {
		super(gp);

		name = "Chest";
		down1 = setup("/objects/chest", gp.tileSize, gp.tileSize);
		price = 120;
	}
}
