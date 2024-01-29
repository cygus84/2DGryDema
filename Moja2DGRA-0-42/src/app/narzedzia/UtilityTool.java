package app.narzedzia;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	
	public BufferedImage scaleImage(BufferedImage orginal, int widht, int height) {
		//scalowanie obrazka
		BufferedImage scaledImage = new BufferedImage(widht, height, orginal.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(orginal, 0, 0, widht, height, null);
		g2.dispose();
		return scaledImage;
	}
}
