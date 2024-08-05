package modele;

import java.awt.Point;

public class Kafelek {
	
	private int typ;
	private Point wspolrzedne;
	
	
	public Kafelek() {
		ustaw(0, 0, 0);
	}
	
	public Kafelek(int typ, int x, int y) {
		ustaw(typ, x, y);
	}
	
	private void ustaw(int typ, int x, int y) {
		this.typ = typ;
		wspolrzedne = new Point(x, y);
	}

	public int getTyp() {
		return typ;
	}

	public Point getWsporzedne() {
		return wspolrzedne;
	}
	
	public int getX() {
		return wspolrzedne.x;
	}

	public int getY() {
		return wspolrzedne.y;
	}
	
	public boolean czyZawiera(Point sprawdzane) {
		return wspolrzedne.equals(sprawdzane);
	}

}
