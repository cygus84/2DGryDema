package app.narzedzia;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	
	public BufferedImage scaleImage(BufferedImage orginal, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, orginal.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(orginal, 0, 0, width, height, null);
		g2.dispose();
		return scaledImage;
	}

}
