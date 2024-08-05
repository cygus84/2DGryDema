package watki;

import java.awt.Point;

import dane.Globalne;
import narzedzia.Obrazek;

public class WatekLadowanieDanychAplikacji extends WatekWzorzec {
	
	private String komunikat;
	private boolean isLadowanieDanych;
	
	public WatekLadowanieDanychAplikacji() {
		super();
	}

	@Override
	protected void akcja() {
		isLadowanieDanych = true;
		//opozniacz();// do testow tylko 
		komunikat = "Ladowanie obrazkow";
		Obrazek.ladowanieObrazkow();
		komunikat = "Ladowanie zmiennych globalnych";
		int x = (Globalne.rozmarEkranu.width - Globalne.rozmiarKafelka) / 2;
		int y = (Globalne.rozmarEkranu.height - Globalne.rozmiarKafelka) / 2;
		Globalne.punktRysowania = new Point(x, y);
		isLadowanieDanych = false;
	}

	public String getKomunikat() {
		return komunikat;
	}

	public boolean isLadowanieDanych() {
		return isLadowanieDanych;
	}
	
	private void opozniacz() {
		
		for(int i = 0; i < 5; i++) {
			komunikat = "Ladowanie danych - " + i;
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
