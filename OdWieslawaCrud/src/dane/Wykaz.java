package dane;

import java.util.ArrayList;

import modele.Obiekt;
import modelelist.ModelListyObiektow;

public class Wykaz {

	public static ArrayList<Obiekt> glowny = new ArrayList<Obiekt>();
	public static ModelListyObiektow podglad = new ModelListyObiektow();
	
	public static void wyczyscPodglad() {
		podglad.clear();
		glowny.forEach((o) -> podglad.addObiekt(o));
	}
	
	public static void wyszukajPoFragmencieNazwy(String fragmentNazwy) {
		int ilosc = podglad.size();
		if (ilosc > 0) {
			ilosc -= 1;
			for (int o = ilosc; o >= 0; o--) {
				if (!podglad.get(o).getNazwa().contains(fragmentNazwy)) {
					podglad.remove(o);
				}
			}
		}
	}
	
	public static void wyszukajPoIlosci(int wybor, int ile) {
		int ilosc = podglad.size();
		if (ilosc > 0) {
			ilosc -= 1;
			boolean zostaw = false;
			for (int o = ilosc; o >= 0; o--) {
				switch(wybor) {
					case 0: //Mniejsza niż
						zostaw = (podglad.get(o).getIlosc() < ile);
						break;
					case 1: //Równa
						zostaw = (podglad.get(o).getIlosc() == ile);
						break;
					case 2: //Większa niż
						zostaw = (podglad.get(o).getIlosc() > ile);
						break;
				}
				if (!zostaw) {
					podglad.remove(o);
				}
			}
		}
	}

	public static void aktualizacjaObiektu(int podgladObiektId, String nowaNazwa, int nowaIlosc) {
		Obiekt staryObiekt = podglad.get(podgladObiektId);
		Obiekt nowyObiekt = new Obiekt(nowaNazwa, nowaIlosc);
		podglad.set(podgladObiektId, nowyObiekt);
		glowny.stream()
			.filter((o) -> (o.getNazwa().equals(staryObiekt.getNazwa()) && o.getIlosc() == staryObiekt.getIlosc()))
			.forEach((o) -> o = nowyObiekt);
		podglad.odswiez();
	}
}
