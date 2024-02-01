package tiles_interactive;

import java.awt.Color;

import app.entity.Entity;
import app.panele.GamePanel;

public class IT_DryTree extends Interactivetile {

	public GamePanel gp;
	
	public IT_DryTree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 =  setup("/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
		destructible = true;	
		life = 3;
	}
	
//	public boolean isCorrectItem(Entity entity) {
//		boolean isCorrectItem = false;
//		if(entity.currentWeapon.type == type_axe) {
//			isCorrectItem = true;
//		}
//		return isCorrectItem;
//	}
	
	public boolean isCorrectItem(Entity entity) {
		return entity.currentWeapon.type == type_axe;
	}
	
	public void playSE() {
		gp.playSE(11);
	}
	
	public Interactivetile getDestroyedForm() {
		Interactivetile tile = new IT_Trunk(gp, worldX / gp.tileSize, worldY / gp.tileSize);
		return tile;		
	}
	
	public Color getParticleColor() {
		Color color = new Color(65, 50, 30);
		return color;
	}

	public int getParticleSize() {
		int size = 6; // 6 pixsel
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLIfe() {
		int maxLife = 20;
		return maxLife;
	}

}
