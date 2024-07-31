package app.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import app.panele.GamePanel;

public class OBJ_Key extends SuperObjects {
	
	private GamePanel gp;

	public OBJ_Key(GamePanel gp) {
		this.gp = gp;
		
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}	
}
