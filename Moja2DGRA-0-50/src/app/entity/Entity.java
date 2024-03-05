package app.entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import app.narzedzia.Particle;
import app.narzedzia.UtilityTool;
import app.panele.GamePanel;

public class Entity {

	public GamePanel gp;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
			attackRight2, guardUp, guardDown, guardLeft, guardRight;
	public BufferedImage image, image2, image3;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	//e51 zmiana na 2 wymiarowa tablice
	public String dialogues[][] = new String[20][20];
	public Entity attacker;

	// STATE
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	//e51
	public int dialogSet = 0;
	public int dialogueIndex = 0;
	public boolean collision = false;
	public boolean invicible = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	public boolean onPath = false;
	// ep41
	public boolean knockBack = false;
	public String knockBackDirection;
	public boolean guarding = false;
	public boolean transparent = false;
	public boolean offBalnce = false;
	public Entity loot;
	public boolean opened = false;

	// COUNTER
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invicibleCounter = 0;
	public int shotAvailableCounter = 0;
	public int dayingCounter = 0;
	public int hpBarCounter = 0;
	// ep41
	public int knockBackCounter = 0;
	public int guardCounter = 0;
	

	// CHARACTER ATTRIBUTES

	public String name;
	// ep41
	public int defaultSpeed;
	public int speed;
	public int maxLife;
	public int life;
	// dodanie parametrow do many
	public int maxMana;
	public int mana;
	public int ammo;
	// dodanie reszty parametrow
	public int level;
	public int strenght;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int motion1_duration;
	public int motion2_duration;
	public Entity currentWeapon;
	public Entity currentShield;
	public Entity currentLight; // wybrane oswietlenie e45
	public Projectile projectile;// DO KULI OGNIA

	// ITEM ATTRIUBUTES
	public ArrayList<Entity> invetory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public int price;
	public int knockBackPower = 0; // e41
	public boolean stackable = false; // e42
	public int amount = 1; // e42
	public int lightRadius; // e45

	// TYPE
	public int type; // 0 player, 1 NPC, 2 = Monster
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickupOnly = 7;
	public final int type_obstacle = 8;
	public final int type_light = 9;// e45

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public int getLeftX() {
		return worldX + solidArea.x;
	}

	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}

	public int getTopY() {
		return worldY + solidArea.y;
	}

	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}

	public int getCol() {
		return (worldX + solidArea.x) / gp.tileSize;
	}

	public int getRow() {
		return (worldY + solidArea.y) / gp.tileSize;

	}
	
	public void setLoot(Entity loot) {
		
	}

	public void setAction() {

	}

	public int getXdistance(Entity target) {
		int xDistance = Math.abs(worldX - target.worldX);
		return xDistance;
	}

	public int getYdistance(Entity target) {
		int yDistance = Math.abs(worldY - target.worldY);
		return yDistance;
	}

	public int getTileDisatnce(Entity target) {
		int tileDistance = (getXdistance(target) + getYdistance(target)) / gp.tileSize;
		return tileDistance;
	}

	public int getGoalCol(Entity target) {
		int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
		return goalCol;
	}

	public int getGoalRow(Entity target) {
		int goalRow = (target.worldY + target.solidArea.y) / gp.tileSize;
		return goalRow;
	}
	
	//e 51
	public void resetCounter() {
	 spriteCounter = 0;
	 actionLockCounter = 0;
	 invicibleCounter = 0;
	 shotAvailableCounter = 0;
	 dayingCounter = 0;
	 hpBarCounter = 0;
	 knockBackCounter = 0;
	 guardCounter = 0;	
	 // brakuje offBalanceCounter
	}

	public void damageReaction() {

	}

	public void speak() {
		
		/*
		 * if (dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}

		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		 * 
		 * usuwamy w e51 i przenosimy switcha do nowej metody
		 */
		
		
	}
	// e 51
	public void facePlayera() {
		switch (gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	// e51
	public void startDialogue(Entity entity, int setNumber) {
		gp.gameState = gp.dialogueState;
		gp.ui.npc = entity;
		dialogSet = setNumber;
	}
	public void interact() {

	}

	public boolean use(Entity entity) {
		return false;
	}

	public void checkDrop() {

	}

	public void dropItem(Entity droppedItem) {

		for (int i = 0; i < gp.obj[1].length; i++) {
			if (gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX; // this is a dead monster X
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
			}
		}
	}

	public Color getParticleColor() {
		Color color = null;
		return color;
	}

	public int getParticleSize() {
		int size = 0;
		return size;
	}

	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}

	public int getParticleMaxLIfe() {
		int maxLife = 0;
		return maxLife;
	}

	public void generateParticle(Entity generator, Entity targe) {
		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLIfe();

		Particle p1 = new Particle(gp, targe, color, size, speed, maxLife, -2, -1);
		gp.particleList.add(p1);
		Particle p2 = new Particle(gp, targe, color, size, speed, maxLife, 2, -1);
		gp.particleList.add(p2);
		Particle p3 = new Particle(gp, targe, color, size, speed, maxLife, -2, 1);
		gp.particleList.add(p3);
		Particle p4 = new Particle(gp, targe, color, size, speed, maxLife, 2, 1);
		gp.particleList.add(p4);

	}

	public void checkCollision() {
		collisionOn = false;
		gp.colChecker.checkTile(this);
		gp.colChecker.checkObject(this, false);
		gp.colChecker.checkEtity(this, gp.npc);
		gp.colChecker.checkEtity(this, gp.monster);
		gp.colChecker.checkEtity(this, gp.iTile);
		boolean contactPlayer = gp.colChecker.checkPlayer(this);

		// WYKRYWANIE KONTAKTU
		if (this.type == 2 && contactPlayer == true) {
			damagePlayer(attack);
		}
	}

	public void update() {
		// e41
		if (knockBack == true) {

			checkCollision();

			if (collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			} else if (collisionOn == false) {
				switch (knockBackDirection) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			knockBackCounter++;
			if (knockBackCounter == 10) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
			else if(attacking == true) {
				attacking();
				
			}
		} else {

			setAction();
			checkCollision();

			// IF COLLISON IS FALSE, PLAYER CANN MOVE
			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}

		}
		// tis is need to be outside of key if statment
		if (invicible == true) {
			invicibleCounter++;
			if (invicibleCounter > 40) {
				invicible = false;
				invicibleCounter = 0;
			}
		}

		// doanie licznika strzalu aby nie moc strzelac za szybbko
		if (shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}

	}
	
	public void checkAttackOrNot(int rate, int straight, int horizontal) {
		
		boolean targetInRange =  false;
		int xDis = getXdistance(gp.player);
		int yDis = getYdistance(gp.player);
		
		switch(direction) {
		case "up":
			if(gp.player.worldY < worldY && yDis < strenght && xDis < horizontal ) {
				targetInRange = true;
			}
			break;
		case "down":
			if(gp.player.worldY > worldY && yDis < strenght && xDis < horizontal ) {
				targetInRange = true;
			}
			break;
		case "left":
			if(gp.player.worldX < worldX && xDis < strenght && yDis < horizontal ) {
				targetInRange = true;
			}
			break;
		case "right":
			if(gp.player.worldX > worldX && xDis < strenght && yDis < horizontal ) {
				targetInRange = true;
			}
			break;
		}
		
		if(targetInRange == true) {
		// CHECK IF IT INITIATES AN ATTACK
			int i = new Random().nextInt(rate);
			if(i == 0) {
				attacking = true;
				spriteNum = 1;
				spriteCounter = 0;
				shotAvailableCounter = 0;
			}
		}
	}

	public void checkShootOrNot(int rate, int shotInterval) {
		// Check if it pojectile
		int i = new Random().nextInt(rate);
		if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
			projectile.set(worldX, worldY, direction, alive, this);
			// chceck vacancy
			for (int ii = 0; ii < gp.projectile[1].length; ii++) {
				if (gp.projectile[gp.currentMap][ii] == null) {
					gp.projectile[gp.currentMap][ii] = projectile;
					break;
				}
			}

			shotAvailableCounter = 0;
		}
	}

	public void chceckStartChasingOrNot(Entity target, int distance, int rate) {

		if (getTileDisatnce(target) < distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = true;
			}
		}
	}

	public void chceckStopChasingOrNot(Entity target, int distance, int rate) {

		if (getTileDisatnce(target) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = false;
			}
		}
	}

	public void getRandomDirection() {
		// Get a random direction
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
	
	public String getOppositeDierction(String direction) {
		
		String oppositeDirection = "";
		
		switch(direction) {
		case "up": oppositeDirection = "down"; break;
		case "down": oppositeDirection = "up"; break;
		case "left": oppositeDirection = "right"; break;
		case "right": oppositeDirection = "left"; break;
		}
		return oppositeDirection;
	}
	
	public void attacking() {
		spriteCounter++;

		if (spriteCounter <= motion1_duration) {
			spriteNum = 1;
		}

		if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
			spriteNum = 2;

			// SAVE TO CURRENT worldX, wolrdY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHight = solidArea.height;

			// Adjust player,s sworldX/y for the attack
			switch (direction) {
			case "up":
				worldY -= attackArea.height;
				break;
			case "down":
				worldY += attackArea.height;
				break;
			case "left":
				worldX -= attackArea.width;
				break;
			case "right":
				worldX += attackArea.width;
				break;
			}

			// attackArea become solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			if(type == type_monster) {
				if(gp.colChecker.checkPlayer(this) == true) {
					damagePlayer(attack);
				}
				
			} else { // attackArea player
				// chceck monster colission wht tje uopdated worldX , wolrdY and solicdArea
				int monsterIndex = gp.colChecker.checkEtity(this, gp.monster);
				gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
				// dodanie niszczenia obiktow np drzewa
				int iTileIndex = gp.colChecker.checkEtity(this,	gp.iTile);
				gp.player.damageInteractiveTile(iTileIndex);
				// sprawdzenie czy dotyka plytej do zniszczenia
				int procjectileIndex = gp.colChecker.checkEtity(this, gp.projectile);
				gp.player.damageProjectile(procjectileIndex);	
			}
			
			

			// After chccking collision retore the orginal date
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHight;

		}

		if (spriteCounter > motion2_duration) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}

	public void damagePlayer(int attack) {
		if (gp.player.invicible == false) {
			int damage = attack - gp.player.defense;
			
			// Get an opposite direction of this attacker
			String canGuardDirection = getOppositeDierction(direction);
			
			if(gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)) {
				// parry
				if(gp.player.guardCounter < 10) {
					damage = 0;
					gp.playSE(16);
					setKnockBack(this, gp.player, knockBackPower);
					offBalnce = true;
					spriteCounter =- 60;
				} else {
					damage /= 3;
					gp.playSE(15);
				}
				
				
			} else {
				// not guardind
				gp.playSE(6);
				if (damage < 1) {
					damage = 1;
				}
			}	
			
			if(damage != 0) {
				gp.player.transparent = true;
			}
			// we can give damge
			gp.player.life -= damage;
			gp.player.invicible = true;
		}
	}
	
	public void setKnockBack(Entity target, Entity attacker,int knockBackPower) {
		
		this.attacker = attacker;
		target.knockBackDirection = attacker.direction;
		target.speed += knockBackPower;
		target.knockBack = true;
		
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;

		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			int tempScreenX = screenX;
			int tempScreenY = screenY;

			switch (direction) {
			case "up":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = up1;
					}
					if (spriteNum == 2) {
						image = up2;
					}
				}
				if (attacking == true) {
					tempScreenY = screenY - gp.tileSize;
					if (spriteNum == 1) {
						image = attackUp1;
					}
					if (spriteNum == 2) {
						image = attackUp2;
					}
				}
				break;
			case "down":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = down1;
					}
					if (spriteNum == 2) {
						image = down2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackDown1;
					}
					if (spriteNum == 2) {
						image = attackDown2;
					}
				}
				break;
			case "left":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = left1;
					}
					if (spriteNum == 2) {
						image = left2;
					}
				}
				if (attacking == true) {
					tempScreenX = screenX - gp.tileSize;
					if (spriteNum == 1) {
						image = attackLeft1;
					}
					if (spriteNum == 2) {
						image = attackLeft2;
					}
				}
				break;
			case "right":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = right1;
					}
					if (spriteNum == 2) {
						image = right2;
					}
					break;
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackRight1;
					}
					if (spriteNum == 2) {
						image = attackRight2;
					}
					break;
				}
			}
			// MONSTER HP BAR
			if (type == 2 && hpBarOn == true) {
				// kastujemujemy zycie do double
				double oneScale = (double) gp.tileSize / maxLife;
				double hpBarValue = oneScale * life;
				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
				// inkremetujemy licznik
				hpBarCounter++;

				if (hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}

			// dodanie warunku
			if (invicible == true) {
				hpBarOn = true;
				// resetwpawnie po ataku
				hpBarCounter = 0;
				// zminie kazda grafie na 70% przejszystosci
				changeAlpha(g2, 0.4f);
				// g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			}

			if (dying == true) {
				dyingAnimation(g2);
			}

			g2.drawImage(image, tempScreenX, tempScreenY, null);

			// RESET DO ALHA PRZYWRUCI GRAFIKE DO
			changeAlpha(g2, 1f);
			// g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


			
		}
	}

	public void dyingAnimation(Graphics2D g2) {

		dayingCounter++;

		int i = 5;

		if (dayingCounter <= i) {
			changeAlpha(g2, 0f);
		}
		if (dayingCounter > i && dayingCounter <= i * 2) {
			changeAlpha(g2, 1f);
		}
		if (dayingCounter > i * 2 && dayingCounter <= i * 3) {
			changeAlpha(g2, 0f);
		}
		if (dayingCounter > i * 3 && dayingCounter <= i * 4) {
			changeAlpha(g2, 1f);
		}
		if (dayingCounter > i * 4 && dayingCounter <= i * 5) {
			changeAlpha(g2, 0f);
		}
		if (dayingCounter > i * 5 && dayingCounter <= i * 6) {
			changeAlpha(g2, 1f);
		}
		if (dayingCounter > i * 6 && dayingCounter <= i * 7) {
			changeAlpha(g2, 0f);
		}
		if (dayingCounter > i * 7 && dayingCounter <= i * 8) {
			changeAlpha(g2, 0f);
		}
		if (dayingCounter > i * 8) {
			// dying = false;
			alive = false;
		}
	}

	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}

	public BufferedImage setup(String imagePath, int width, int height) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResource(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void serachPath(int goalCol, int goalRow) {

		int startCol = (worldX + solidArea.x) / gp.tileSize;
		int startRow = (worldY + solidArea.y) / gp.tileSize;

		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);

		if (gp.pFinder.search() == true) {

			// next worldX and wolrdY
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			// entity solidarea position
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;

			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			} else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			} else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				// left or right
				if (enLeftX > nextX) {
					direction = "left";
				}
				if (enLeftX < nextX) {
					direction = "right";
				}
			} else if (enTopY > nextY && enLeftX > nextX) {
				// up or left
				direction = "up";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
			} else if (enTopY > nextY && enLeftX < nextX) {
				// up or right
				direction = "up";
				if (collisionOn == true) {
					direction = "right";
				}
			} else if (enTopY < nextY && enLeftX > nextX) {
				// down or left
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
			} else if (enTopY < nextY && enLeftX < nextX) {
				// down or right
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "right";
				}
			}
			// if reach the goal stop search
//			int nextCol = gp.pFinder.pathList.get(0).col;
//			int nextRow = gp.pFinder.pathList.get(0).row;
//			if(nextCol == goalCol && nextRow == goalRow) {
//				onPath = false;
//			}
		}
	}

	public int getDetected(Entity user, Entity target[][], String targetName) {

		int index = 999;

		// CHECK THE SURROUNDING OBJECT
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();

		switch (user.direction) {
		case "up":
			nextWorldY = user.getTopY() - gp.player.speed;
			break;
		case "down":
			nextWorldY = user.getBottomY() + gp.player.speed;
			break;
		case "left":
			nextWorldX = user.getLeftX() - gp.player.speed;
			break;
		case "right":
			nextWorldX = user.getRightX() + gp.player.speed;
			break;
		}
		int col = nextWorldX / gp.tileSize;
		int row = nextWorldY / gp.tileSize;

		for (int i = 0; i < target[1].length; i++) {
			if (target[gp.currentMap][i] != null) {
				if (target[gp.currentMap][i].getCol() == col && target[gp.currentMap][i].getRow() == row
						&& target[gp.currentMap][i].name.equals(targetName)) {

					index = i;
					break;
				}
			}
		}
		return index;
	}
}
