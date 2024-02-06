package app.monster;

import java.util.Random;

import app.entity.Entity;
import app.object.OBJ_Coin_Bronze;
import app.object.OBJ_Heart;
import app.object.OBJ_ManaCrystal;
import app.object.OBJ_Rock;
import app.panele.GamePanel;

public class MON_Orck  extends Entity{

	public MON_Orck(GamePanel gp) {
		super(gp);
		
		// ustawnie parametrow
		type = type_monster;
		name = "Orc";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 10;
		life = maxLife;
		attack = 8;
		defense = 2;
		exp = 10;
		
		// ustawnie wymiarow solid
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 40;
		solidArea.height = 44;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 48;
		attackArea.height = 48;
		motion1_duration = 40;
		motion2_duration = 85;

		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		up1 = setup("/monster/orc_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/orc_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/orc_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/orc_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/orc_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/orc_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/orc_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/orc_down_2", gp.tileSize, gp.tileSize);
	}

	public void getAttackImage() {
		attackUp1 = setup("/monster/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
		attackUp2 = setup("/monster/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
		attackDown1 = setup("/monster/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
		attackDown2 = setup("/monster/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
		attackLeft1 = setup("/monster/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
		attackLeft2 = setup("/monster/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
		attackRight1 = setup("/monster/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
		attackRight2 = setup("/monster/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);
	}
	public void setAction() {
		if (onPath == true) {

			// check if it stops chasing
			chceckStopChasingOrNot(gp.player, 15, 100);

			// search direction to go
			serachPath(getGoalCol(gp.player), getGoalRow(gp.player));
		} else {
			// jesli jesetes bliko 5 plytek potwor atakuje
			// chect if stop chasing
			chceckStartChasingOrNot(gp.player, 5, 100);

			// Get a random direction
			getRandomDirection();
		}
		
		// Check if it attack
		if(attacking == false) {
			checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize);
		}
	}

	public void damageReaction() {
		actionLockCounter = 0;
		// direction = gp.player.direction; // zmieniami ze by po zatakowaniu sledzil
		// gracza a nie uciekal
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
