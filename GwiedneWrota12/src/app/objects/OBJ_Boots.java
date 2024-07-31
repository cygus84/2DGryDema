package app.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import app.panele.GamePanel;

public class OBJ_Boots extends SuperObjects{
	private GamePanel gp;
	
public OBJ_Boots(GamePanel gp) {
	
	this.gp = gp;
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
