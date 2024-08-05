package panele;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import dane.Globalne;
import modele.Kafelek;
import narzedzia.Obrazek;
import java.awt.Color;

public class PanelMapy extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean isRysowanie;
	private ArrayList<Kafelek> kafelki; 

	public PanelMapy() {
		setBackground(new Color(0, 0, 0));
		isRysowanie = false;
		kafelki = new ArrayList<Kafelek>();
		kafelki.add(new Kafelek());
	}

	private void rysowanieKafelka(Graphics g, Kafelek kafelek) {
		g.drawImage(
				Obrazek.kafelka(kafelek.getTyp()).getImage(),
				Globalne.punktRysowania.x + ((kafelek.getX() - Globalne.deltakafelka.x) * Globalne.rozmiarKafelka),
				Globalne.punktRysowania.y + ((kafelek.getY() - Globalne.deltakafelka.y) * Globalne.rozmiarKafelka),
				null
				);
	}
	
	private void rysowaStrzalek(Graphics g) {
		for(int k = 0; k <= 3; k++) {
			g.drawImage(
					Obrazek.strzalki(k).getImage(),
					Globalne.punktRysowania.x + (Globalne.deltaRuchyu(k + 1).x * Globalne.rozmiarKafelka),
					Globalne.punktRysowania.y + (Globalne.deltaRuchyu(k + 1).y * Globalne.rozmiarKafelka),
					null
					);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!isRysowanie) {
			isRysowanie = true;
			// ryswoanie kafelkow
			kafelki.forEach((k) -> rysowanieKafelka(g, k));
			// rysowanie strzalek 
			rysowaStrzalek(g);
			isRysowanie = false;
		}
	}
	
	public void odswierzanieMapy() {
		repaint();
	}
	

}
