package tiles_interactive;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import app.entity.Entity;
import app.panele.GamePanel;

public class Interactivetile extends Entity {

	public GamePanel gp;
	public boolean destructible = false;
	
	public Interactivetile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;	
		return isCorrectItem;
	}
	
	public void playSE() {
		
	}
	
	public Interactivetile getDestroyedForm() {
		Interactivetile tile = null;
		return tile;		
	}
	
	public void update() {
		if (invicible == true) {
			invicibleCounter++;
			if (invicibleCounter > 20) {
				invicible = false;
				invicibleCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
	
				g2.drawImage(down1, screenX, screenY, null);
		}
	}
}
