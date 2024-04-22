package app.narzedzia;

import java.util.Random;

public class Generator {
	
	private static Random generator = new Random();
	
	public static int ataku(int atakGracza) {
		return (atakGracza / 2) + generator.nextInt(atakGracza);
	}
	
	public static int obrony(int obronaGracza) {
		return (obronaGracza / 2) + generator.nextInt(obronaGracza);
	}
	
	public static int rany(int atak, int obrona) {
		int wynik = atak - obrona;
		if(wynik!= 0) {
			return wynik / 10;
		}
		return 0;
	}

}
