package app.environment;

import java.awt.Graphics2D;

import app.panele.GamePanel;

public class EnvironmentManager {
	///  Light, Rain, Fogs etc..
	
	private GamePanel gp;
	private Lighting lighting;
	
	public EnvironmentManager(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public void setup() {
		
		lighting = new Lighting(gp, 350);
	}
	
	public void draw(Graphics2D g2) {
		lighting.draw(g2);
	}

}
