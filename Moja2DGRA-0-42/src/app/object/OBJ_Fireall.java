package app.object;

import java.awt.Color;

import app.entity.Entity;
import app.entity.Projectile;
import app.panele.GamePanel;

public class OBJ_Fireall extends Projectile {

	private GamePanel gp;

	public OBJ_Fireall(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Fireball";
		speed = 5;
		maxLife = 80;
		life = maxLife;
		attack = 2;
		knockBackPower = 0;
		useCost = 1;
		alive = false;
		getImage();
	}

	private void getImage() {

		up1 = setup("/projectile/fireball_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/fireball_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/fireball_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/fireball_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/fireball_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/fireball_right_2", gp.tileSize, gp.tileSize);

	}

	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtrackResource(Entity user) {
		user.mana -= useCost;
	}

	public Color getParticleColor() {
		Color color = new Color(250, 50, 0);
		return color;
	}

	public int getParticleSize() {
		int size = 10;
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
