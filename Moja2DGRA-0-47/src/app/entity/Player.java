package app.entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import app.environment.Lighting;
import app.narzedzia.UtilityTool;
import app.object.OBJ_Axe;
import app.object.OBJ_Fireall;
import app.object.OBJ_Key;
import app.object.OBJ_Rock;
import app.object.OBJ_Shield_Wood;
import app.object.OBJ_Sword_Normal;
import app.panele.GamePanel;
import app.sterowanie.KeyHandler;

public class Player extends Entity {

	public KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int standCounter = 0;
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;


	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		// SOLID AREA
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		// ATTACK AREA
//		attackArea.width = 36;
//		attackArea.height = 36;

		steDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}

	public void steDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
//		worldX = gp.tileSize * 12;
//		worldY = gp.tileSize * 13;

		defaultSpeed = 4;
		speed = defaultSpeed;
		direction = "down";

		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		maxMana = 4;
		mana = maxMana;
		ammo = 10;
		strenght = 1; // The more strnght he has , the more damage he give
		dexterity = 1;// The more dextrity he has, les damge recives
		exp = 0;
		nextLevelExp = 5;
		coin = 500;
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		// dodanie ognistej kuli
		projectile = new OBJ_Fireall(gp);
		// projectile = new OBJ_Rock(gp);
		attack = getAttack();// The total attack value is decided by strenght weapon
		defense = getDefense();// The total defnese value is decided by dexterity and sheald
	}
	
	public void setDefaultPosition() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;

		speed = 4;
		direction = "down";
	}
	
	public void restoreLifeAndMana() {
		life = maxLife;
		mana = maxMana;
		invicible = false;
	}

	// metoda do ustawienia inwentrzu
	public void setItems() {
		invetory.clear();
		invetory.add(currentWeapon);
		invetory.add(currentShield);
		invetory.add(new OBJ_Key(gp));
		invetory.add(new OBJ_Axe(gp));

	}

	// metoda do sprawdzenia ataku
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		motion1_duration = currentWeapon.motion1_duration;
		motion2_duration = currentWeapon.motion2_duration;
		return attack = strenght * currentWeapon.attackValue;
	}

	// metoda do sprawdzenia obrony
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}

	public void getPlayerImage() {

		up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
	}
	
	public void getSleepingImage(BufferedImage image) {
		up1 = image;
		up2 = image;
		down1 = image;
		down2 = image;
		left1 = image;
		left2 = image;
		right1 = image;
		right2 = image;
		
	}

	public void getPlayerAttackImage() {

		// dodanie warunokow
		if (currentWeapon.type == type_sword) {

			attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
		}

		if (currentWeapon.type == type_axe) {

			attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
		}

	}

	public void update() {

		if (attacking == true) {
			attacking();
		} else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true || keyH.entertPressed == true) {

			if (keyH.upPressed == true) {
				direction = "up";

			} else if (keyH.downPressed == true) {
				direction = "down";

			} else if (keyH.leftPressed == true) {
				direction = "left";

			} else if (keyH.rightPressed == true) {
				direction = "right";

			}

			// dodanie wyrkywanie kolizji
			collisionOn = false;
			gp.colChecker.checkTile(this);

			// check objects collision
			int objIndex = gp.colChecker.checkObject(this, true);
			pickUpObject(objIndex);// dodanie metody tu

			// Chceck NPC collison
			int npcIndex = gp.colChecker.checkEtity(this, gp.npc);
			interactNPC(npcIndex);

			// Chceck NPC monster
			int monsterIndex = gp.colChecker.checkEtity(this, gp.monster);
			contacktMonster(monsterIndex);
			
			// CHECK INTERACTIVE COLISION TILE
			int iTileIndex = gp.colChecker.checkEtity(this, gp.iTile);
			
			// CHECK EVENT
			gp.eHandler.checkEvent();

			// if collision is fasle player can move
			if (collisionOn == false && keyH.entertPressed == false) {
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

			if (keyH.entertPressed == true && attackCanceled == false) {
				gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}
			attackCanceled = false;

			// przenisienie wulaczenia entera
			// reset przycisku
			gp.keyH.entertPressed = false;

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

		// dodanie fireball
		if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30
				&& projectile.haveResource(this) == true) {

			// SET DEFALT USER PARAMETERS
			projectile.set(worldX, worldY, direction, true, this);

			// SUBSTRACK THE COS MANA AMONITION ITD
			projectile.subtrackResource(this);

			// ADD IT TO THE LIST
//			gp.projectileList.add(projectile);
			// check vacancy epizod 41
			for(int i = 0; i < gp.projectile[1].length; i++) {
				if(gp.projectile[gp.currentMap][i] == null) {
					gp.projectile[gp.currentMap][i] = projectile;
					break;
				}
			}
			
			// resetowanie licznika strzelania
			shotAvailableCounter = 0;

			// dodanie efektu
			gp.playSE(10);
		}

		// tis is need to be outside of key if statment
		if (invicible == true) {
			invicibleCounter++;
			if (invicibleCounter > 60) {
				invicible = false;
				invicibleCounter = 0;
			}
		}
		// doanie licznika strzalu aby nie moc strzelac za szybbko
		if (shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
		
		// sprawdzenie zycia gracza
		
		if(life > maxLife) {
			life = maxLife;
		}
		// sprawdzenie many
		if(mana > maxMana) {
			mana = maxMana;
		}
		// dodanie statusu game over
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum = -1; // spowolnienie po smierci ze by nie kliknac za szybko restart
			gp.stopMusic();
			gp.playSE(12);
		}
		
		
	}

	public void attacking() {
		spriteCounter++;

		if (spriteCounter <= 5) {
			spriteNum = 1;
		}

		if (spriteCounter > 5 && spriteCounter <= 25) {
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
			// chceck monster colission wht tje uopdated worldX , wolrdY and solicdArea
			int monsterIndex = gp.colChecker.checkEtity(this, gp.monster);
			damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
			// dodanie niszczenia obiktow np drzewa
			int iTileIndex = gp.colChecker.checkEtity(this,	gp.iTile);
			damageInteractiveTile(iTileIndex);
			// sprawdzenie czy dotyka plytej do zniszczenia
			int procjectileIndex = gp.colChecker.checkEtity(this, gp.projectile);
			damageProjectile(procjectileIndex);
			

			// After chccking collision retore the orginal date
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHight;

		}

		if (spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}

	// metoda do podnoszenia
	public void pickUpObject(int i) {

		if (i != 999) {
			// PICK ONLY ITEMS
			if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			} 
			// OBSTACLE
			else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
				if(keyH.entertPressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			
			// INVETORY ITEMS
			else {
				
				String text;
				// sprawdzenie czy inventarz jest pelny i ma miejsce
				if (canObtainItem(gp.obj[gp.currentMap][i]) == true) {
				//	invetory.add(gp.obj[gp.currentMap][i]);
					gp.playSE(1);
					text = "Go a " + gp.obj[gp.currentMap][i].name + "!";
				} else {
					text = "You cannot carry any more!";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
		}

	}

	public void interactNPC(int i) {
		if (gp.keyH.entertPressed == true) {
			if (i != 999) {
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
		}
	}

	public void contacktMonster(int i) {
		if (i != 999) {
			if (invicible == false && gp.monster[gp.currentMap][i].dying == false) {
				// jak gracz otrzymuje orbazenia dzwiek
				gp.playSE(6);
				// dodanie ataku potwora
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if (damage < 0) {
					damage = 0;
				}
				life -= damage;
				invicible = true;
			}

		}
	}

	public void damageMonster(int i, Entity attacker,int attack, int knockBackPower) {

		if (i != 999) {
			if (gp.monster[gp.currentMap][i].invicible == false) {
				
				// jak gracz tafi potwora dzwiek
				gp.playSE(5);
				//e41
				if(knockBackPower > 0) {
					//41
					setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
				}
				
				
				
				// dodanie ataku potwora
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if (damage < 0) {
					damage = 0;
				}

				gp.monster[gp.currentMap][i].life -= damage;
				// dodanie wiadomosci o zadawaniu dmg
				gp.ui.addMessage(damage + " damage!");
				gp.monster[gp.currentMap][i].invicible = true;
				// dodanie reakcji na atak potwora
				gp.monster[gp.currentMap][i].damageReaction();
				


				if (gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
					gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					chceckLevelUp();
				}
			}
		}

	}
	
	public void damageInteractiveTile(int i) {
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true
				&& gp.iTile[gp.currentMap][i].invicible == false) {
			
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invicible = true;
			
			//dodanie generatora particle
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			
			if(gp.iTile[gp.currentMap][i].life == 0) {
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
	}
	// e41

	// dodane w ep41
	public void damageProjectile(int i) {
		if(i != 999) {
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile, projectile);
		}
	}

	public void chceckLevelUp() {
		if (exp > nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp * 2;
			maxLife += 2;
			strenght++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			// dodanie dzwieku do levelu
			gp.playSE(8);
			// wyswietlenie tekstu
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n" + "You fell stonger!";

		}
	}

	public void selectItem() {

		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		// pobranie zaznaczonego itemu
		if (itemIndex < invetory.size()) {
			Entity selectedItem = invetory.get(itemIndex);

			if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
				// wybranie broni
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			// wybranie tarczy dla gracza
			if (selectedItem.type == type_shield) {
				// wybrania tarcza
				currentShield = selectedItem;
				defense = getDefense();
			}
			//e45
			if(selectedItem.type == type_light) {
				
				if(currentLight == selectedItem) {
					currentLight = null;
				} else {
					currentLight = selectedItem;
				}
				lightUpdated = true;
			}
			// Napoj jednokrotnego uzycia
			if (selectedItem.type == type_consumable) {
				if(selectedItem.use(this) == true) {
					if(selectedItem.amount > 1) {
						selectedItem.amount--;
					} else {
						invetory.remove(itemIndex);
					}		
				}	
			}
		}
	}
	
	public int searchItemInventory(String itemName) {
		
		int itemIndex = 999;
		
		for(int i = 0;i < invetory.size();i++ ) {
			if(invetory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;		
	}
	
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		// CHECK IF STACKABLE
		if(item.stackable == true) {
			
			int index = searchItemInventory(item.name);
			
			if(index != 999) {
				invetory.get(index).amount++;
				canObtain = true;
			}
			else { // new item so need chceck vacancy
				if(invetory.size() != maxInventorySize) {
					invetory.add(item);
					canObtain = true;
				}
			}
		}
		else {// NOT STACKABLE SO CHECK VACANCY
			if(invetory.size() != maxInventorySize) {
				invetory.add(item);
				canObtain = true;
			}
		}
		return canObtain;
	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.WHITE);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image = null;
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
		// dodanie warunku
		if (invicible == true) {
			// zminie kazda grafie na 70% przejszystosci
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}

		g2.drawImage(image, tempScreenX, tempScreenY, null);

		// RESET DO ALHA PRZYWRUCI GRAFIKE DO NORAMLNEJ
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		// DEBUG TXT
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.WHITE);
//		g2.drawString("ivicible:" + invicibleCounter, 10, 400);

	}
}
