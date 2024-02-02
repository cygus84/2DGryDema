package tiles_interactive;

import java.awt.Rectangle;

import app.panele.GamePanel;

public class IT_Trunk extends Interactivetile {


	public GamePanel gp;
	
	public IT_Trunk(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles_interactive/trunk", gp.tileSize, gp.tileSize);
		
		solidArea = new Rectangle(0, 0, 0, 0);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		
	}
}
