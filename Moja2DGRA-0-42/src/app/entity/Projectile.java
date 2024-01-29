package app.entity;

import app.panele.GamePanel;

public class Projectile extends Entity {
	
	public Entity user;

	public Projectile(GamePanel gp) {
		super(gp);	
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}
	
	public void update() {
		
		// dodanie metody do kolizji kuli ognia
		if(user == gp.player) {
			// pobranie inedeksu  potwora 
			int monsterIndex = gp.colChecker.checkEtity(this, gp.monster);
			if(monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, attack, knockBackPower);
				generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
				alive = false;
			}
		}
		
		if(user != gp.player) {
			boolean contackPlayer = gp.colChecker.checkPlayer(this);
			if(gp.player.invicible == false && contackPlayer == true) {
				damagePlayer(attack);
				generateParticle(user.projectile, gp.player);
				alive = false;
			}
		}
		
		
		switch(direction) {
		case "up": 
			worldY -= speed;
			break;
		case "down":
			worldY += speed;
			break;
		case "left":
			worldX  -= speed;
			break;
		case "right": 
			worldX += speed;
		break;
		}
		
		life--;
		if(life <= 0) {
			alive = false;
		}
		
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		return haveResource;
	}
	
	public void subtrackResource(Entity user) {
	}
	
	

}
