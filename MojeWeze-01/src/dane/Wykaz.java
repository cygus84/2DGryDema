package dane;

import java.util.ArrayList;

import modeleList.ModelListWezy;
import weze.Waz;

public class Wykaz {
	
	public static ArrayList<Waz> glowny = new ArrayList<Waz>();
	public static ModelListWezy podglad = new ModelListWezy();
	
	public static void wyczyscPodglad() {
		podglad.clear();
		glowny.forEach((w) -> podglad.addWaz(w));
	}
	
	public static void wyszukajPoFragmecieNazwy(String fragmentNazwy) {
		int ilosc = podglad.size();
		if (ilosc > 1) {
			ilosc -= 1;
			for (int w = ilosc; w >= 0; w--) {
				if (!podglad.get(w).getNazwa().contains(fragmentNazwy));
				podglad.remove(w);
			}
		}
	}
	
	public static void wyszukajPoDlugosci(int wybor, int szDlugosc) {
		int dlugosc = podglad.size();
		if( dlugosc > 0) {
			dlugosc -= 1;
			boolean zostaw = false;
			for (int w = dlugosc; w >= 0; w--) {
				switch(wybor) {
				case 0: // dlugosc mniejsza
					zostaw = (podglad.get(w).getDlugosc() < szDlugosc);
					break;
				case 1: // rowna
					zostaw = (podglad.get(w).getDlugosc() == szDlugosc);
					break;
				case 3: // wieksza 
					zostaw = (podglad.get(w).getDlugosc() > szDlugosc);
					break;
				}
				if (!zostaw) {
					podglad.remove(w);
				}
			}
		}
	}
	
	public static void aktulizacjaWezy(int podgladWezyId, String nowaNazwa, int nowaDlugosc) {
		Waz staryWaz = podglad.get(podgladWezyId);
		Waz nowyWaz = new Waz(nowaNazwa, nowaDlugosc);
		podglad.set(podgladWezyId, nowyWaz);
		glowny.stream()
				.filter((w) -> (w.getNazwa().equals(staryWaz.getNazwa()) && w.getDlugosc() == staryWaz.getDlugosc()))
				.forEach((w) -> w = nowyWaz);
				podglad.odswiez();
	}
	
	public static void usunWeza(int podgladWezyId) {
		Waz wazDoUsuniecia =  podglad.remove(podgladWezyId);
		glowny.remove(wazDoUsuniecia);
	}
}
