package app.monster;

import java.util.Random;

import app.entity.Entity;
import app.object.OBJ_Coin_Bronze;
import app.object.OBJ_Heart;
import app.object.OBJ_ManaCrystal;
import app.object.OBJ_Rock;
import app.panele.GamePanel;

public class MON_GreenSlime extends Entity {

	public GamePanel gp;

	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;

		// ustawnie parametrow
		type = type_monster;
		name = "Green Slime";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 4;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		// dodanie rzutu kamieniem
		projectile = new OBJ_Rock(gp);

		// ustawnie wymiarow solid
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
		setAction();
	}

	public void getImage() {
		up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
	}
	
	public void update() {
		super.update();
		
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistanece = (xDistance + yDistance) / gp.tileSize;
		
		// jesli jesetes bliko 5 plytek potwor atakuje 
		if(onPath == false && tileDistanece < 5) { 
			
			int r = new Random().nextInt(100) + 1;
			if(r > 50) {
				onPath = true;
			}	
		}
		
//		if(onPath == true && tileDistanece > 20) {
//			onPath = false;
//		}
		
	}

	public void setAction() {

		if (onPath == true) {

//			int goalCol = 12;
//			int goalRow = 9;

			int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

			serachPath(goalCol, goalRow);
			
			// dodanie 2 kondycji jak gracz jest blisko 
			

			// dodanie ataku kamieniem
			int i = new Random().nextInt(200) + 1;
			if (i > 197 && projectile.alive == false && shotAvailableCounter == 30) {
				projectile.set(worldX, worldY, direction, alive, this);
				//gp.projectileList.add(projectile);
				// epizod 41
				
				// chceck vacancy
				for(int ii = 0; ii < gp.projectile[1].length; ii++) {
					if(gp.projectile[gp.currentMap][ii] == null) {
						gp.projectile[gp.currentMap][ii] = projectile;
						break;
					}
				}
				
				
				shotAvailableCounter = 0;
			}

		} else {

			actionLockCounter++;

			if (actionLockCounter == 120) {
				Random random = new Random();
				int i = random.nextInt(100) + 1; // pick up o number from 1 to 100

				if (i <= 25) {
					direction = "up";
				}

				if (i > 25 && i <= 50) {
					direction = "down";
				}

				if (i > 50 && i <= 75) {
					direction = "left";
				}

				if (i > 75 && i <= 100) {
					direction = "right";
				}

				actionLockCounter = 0;
			}
		}

	}

	public void damageReaction() {
		actionLockCounter = 0;
		// direction = gp.player.direction;  // zmieniami ze by po zatakowaniu sledzil gracza a nie uciekal
		onPath = true;
	}

	public void checkDrop() {
		// CAST DIE
		int i = new Random().nextInt(100) + 1;
		// SET THE MONSTER DROP
		if (i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if (i >= 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}

		if (i >= 75 && i < 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
	}
}
