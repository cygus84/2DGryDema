package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Tent extends Entity {

	private GamePanel gp;
	
	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = "Tent";
		down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nYou can sleep until next mornig.";
		price = 300;
		stackable = true;
	}
	
	public boolean use(Entity entity) {
		
		gp.gameState = gp.sleepState;
		gp.playSE(14);
		gp.player.life = gp.player.maxLife;
		gp.player.mana = gp.player.maxMana;
		gp.player.getSleepingImage(down1);
		return true;
	}

}
