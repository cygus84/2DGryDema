package app.environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import app.panele.GamePanel;

public class Lighting {

	public GamePanel gp;
	public BufferedImage darknesFilter;
	public int dayCounter;
	public float filterAlpha = 0f;
	
	// Day State
	public final int day = 0;
	public final int dusk = 1;
	public final int night = 2;
	public final int dawn = 3;
	public int dayState = day;
	
	public Lighting(GamePanel gp) {
		this.gp = gp;
		setLightSource();
		
	}
	
	public void setLightSource() {
		// Create a buffered image
				darknesFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2 = (Graphics2D)darknesFilter.getGraphics();
				
				// Create a screen-size rectangel area
				Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));
				
				
				if(gp.player.currentLight == null) {
					g2.setColor(new Color(0, 0, 0.1f, 0.98f));
				} else {
					// Get the center x and y of the light cicle
					int centerX = gp.player.screenX + (gp.tileSize) / 2;
					int centerY = gp.player.screenY + (gp.tileSize) / 2;
					
					// create a gradation effect within the light circle
					Color color[] = new Color[12];
					float fraction[] = new float[12];
					
					color[0] = new Color(0, 0, 0.1f, 0.1f);
					color[1] = new Color(0, 0, 0.1f, 0.42f);
					color[2] = new Color(0, 0, 0.1f, 0.52f);
					color[3] = new Color(0, 0, 0.1f, 0.61f);
					color[4] = new Color(0, 0, 0.1f, 0.69f);
					color[5] = new Color(0, 0, 0.1f, 0.76f);
					color[6] = new Color(0, 0, 0.1f, 0.82f);
					color[7] = new Color(0, 0, 0.1f, 0.87f);
					color[8] = new Color(0, 0, 0.1f, 0.91f);
					color[9] = new Color(0, 0, 0.1f, 0.94f);
					color[10] = new Color(0, 0, 0.1f, 0.96f);
					color[11] = new Color(0, 0, 0.1f, 0.98f);
					
					fraction[0] = 0f;
					fraction[1] = 0.4f;
					fraction[2] = 0.5f;
					fraction[3] = 0.6f;
					fraction[4] = 0.65f;
					fraction[5] = 0.7f;
					fraction[6] = 0.75f;
					fraction[7] = 0.8f;
					fraction[8] = 0.85f;
					fraction[9] = 0.9f;
					fraction[10] = 0.95f;
					fraction[11] = 1f;
					
					// CREATE A GRADATION PAINT SETTINGS FOR THE LIGHT CIRCELE
					RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius,fraction, color);
					
					// SET A GRADAIET TO DATA ON G2
					g2.setPaint(gPaint);
				}				
				
				g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
				
				g2.dispose();
	}
	
	public void update() {
		
		if(gp.player.lightUpdated == true) {
			setLightSource();
			gp.player.lightUpdated = false;
		}
		
		// Check the status of the day 
		if(dayState == day) {
			dayCounter++;
			
			if(dayCounter > 600) {
				dayState = dusk;
				dayCounter = 0;
			}
		}
		
		if(dayState == dusk) {
			
			filterAlpha += 0.001f;
			
			if(filterAlpha > 1f) {
				filterAlpha = 1f;
				dayState = night;
			}
		}
		
		if(dayState == night) {
			
			dayCounter++;
			
			if(dayCounter > 36000) { // zmieniamy do z 600 na 36000
				dayState = dawn;
				dayCounter = 0;
			}
		}
		
		if(dayState == dawn) {
			
			filterAlpha -= 0.001f;
			
			if(filterAlpha < 0f) {
				filterAlpha = 0;
				dayState = day;
			}
		}
			
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
		g2.drawImage(darknesFilter, 0, 0, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// Debug
		
		String situatione = "";
		
		switch(dayState) {
		case day: situatione = "Day"; break;
		case dusk: situatione = "Dusk"; break;
		case night: situatione = "Night"; break;
		case dawn: situatione = "Dawn"; break;
		}
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(35f)); // zmieniam z 50 na 35
		g2.drawString(situatione, 800, 500);
	}
	
}
