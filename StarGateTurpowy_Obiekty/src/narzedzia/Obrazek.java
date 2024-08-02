package narzedzia;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Obrazek {

	private static ArrayList<ImageIcon> obrazkiKafelkow = new ArrayList<ImageIcon>();
	private static ArrayList<ImageIcon> obrazkiStrzalek = new ArrayList<ImageIcon>();
	
	
	public static ImageIcon kafelka(int ktory) {
		return obrazkiKafelkow.get(ktory);
	}
	
	public static ImageIcon strzalki(int ktory) {
		return obrazkiStrzalek.get(ktory - 1); // pomniejszony o 1 Wazne
	}
	
	private static ImageIcon ladowanieObrazka(String nazwaObrazka) {
		return new ImageIcon(Obrazek.class.getResource(nazwaObrazka));
	}
	
	public static void ladowanieObrazkow() {
		// kafelki
		for(int k = 0; k <= 5; k++) {
			obrazkiKafelkow.add(
					ladowanieObrazka(String.format(
					"/obrazki/kafelki/Kafelek-%02d.png", k)));
		}
		// strzalki
		obrazkiStrzalek.add(ladowanieObrazka("/obrazki/strzalki/Strzalka-N.png"));
		obrazkiStrzalek.add(ladowanieObrazka("/obrazki/strzalki/Strzalka-E.png"));
		obrazkiStrzalek.add(ladowanieObrazka("/obrazki/strzalki/Strzalka-S.png"));
		obrazkiStrzalek.add(ladowanieObrazka("/obrazki/strzalki/Strzalka-W.png"));
		
	}
	
}
