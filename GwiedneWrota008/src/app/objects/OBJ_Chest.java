package app.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObjects {

public OBJ_Chest() {
		
		name = "Chest";
		try {
			image = image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}