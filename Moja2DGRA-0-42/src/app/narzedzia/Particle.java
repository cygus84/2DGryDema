package app.narzedzia;

import java.awt.Color;
import java.awt.Graphics2D;

import app.entity.Entity;
import app.panele.GamePanel;

public class Particle extends Entity{

	public Entity generator;
	public Color color;
	public int size;
	public int xd;
	public int yd;
	
	public Particle(GamePanel gp, Entity generator, Color color,
			int size, int speed, int maxLife, int xd, int yd) {
		super(gp);
		this.generator = generator;
		this.color = color;
		this.size = size;
		this.speed = speed;
		this.life = maxLife;
		this.xd = xd;
		this.yd = yd;
		int offSet = (gp.tileSize /2) - (size / 2); // wysierodkowanie na drzewie
		worldX = generator.worldX + offSet;
		worldY = generator.worldY + offSet;
		
	}
	
	public void update() {
		life--;
		
		if(life < maxLife / 3) {
			yd++;
		}
		 
		worldX += xd * speed ;
		worldY += yd * speed;
		if(life == 0) {
			alive = false;
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		g2.setColor(color);
		g2.fillRect(screenX, screenY, size, size);
	}

}
