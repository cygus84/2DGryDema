package app.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObjects {

	public OBJ_Key() {
		
		name = "Key";
		try {
			image = image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}	
}
