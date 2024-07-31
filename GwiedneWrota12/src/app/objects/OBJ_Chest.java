package app.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import app.panele.GamePanel;

public class OBJ_Chest extends SuperObjects {
	
	private GamePanel gp;

public OBJ_Chest(GamePanel gp) {
	
	this.gp = gp;
		
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
