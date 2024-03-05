package app.object;

import app.entity.Entity;
import app.panele.GamePanel;

public class OBJ_Potion_Red extends Entity {
	
	private GamePanel gp;
	
	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		value = 5;
		type = type_consumable;
		name = "Red Potion";
		down1 =setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		description = "[Red Potion]\n Heal You life by " + value + ".";
		price = 25;
		stackable = true;
		setDialogue();
	}
	
	public void setDialogue() {
		dialogues[0][0] = "You drink the " + name + "!\n"
				+ "You life was recover by " + value + ".";
	}
	
	// dodanie metody do uzycia
	public boolean use(Entity entity) {

		startDialogue(this, 0);
		entity.life += value;
		gp.playSE(2);
		return true;
	}

}
