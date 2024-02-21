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

	public void setAction() {
		if (onPath == true) {

			// check if it stops chasing
			chceckStopChasingOrNot(gp.player, 15, 100);

			// search direction to go
			serachPath(getGoalCol(gp.player), getGoalRow(gp.player));

			// Check if it pojectile
			checkShootOrNot(200, 30);

		} else {
			// jesli jesetes bliko 5 plytek potwor atakuje
			// chect if stop chasing
			chceckStartChasingOrNot(gp.player, 5, 100);

			// Get a random direction
			getRandomDirection();

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
