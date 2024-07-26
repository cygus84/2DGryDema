package app.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObjects{
public OBJ_Boots() {
		
		name = "Boots";
		try {
			image = image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
