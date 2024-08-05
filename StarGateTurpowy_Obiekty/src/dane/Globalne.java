package dane;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class Globalne {
	
	public static Dimension rozmarEkranu = Toolkit.getDefaultToolkit().getScreenSize();
	public static Point punktRysowania = new Point(0, 0);
	public static int rozmiarKafelka = 150;
	public static Point deltakafelka = new Point(0, 0);
	private static Point[] deltyRuchu = {
		new Point(0, 0),
		new Point(0, -1),
		new Point(1,0),
		new Point(0, 1),
		new Point(-1, 0)
	};
	
	public static Point deltaRuchyu(int kierunek) {
		return deltyRuchu[kierunek];
	}
	
}
