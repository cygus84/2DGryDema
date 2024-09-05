package dane;

import java.util.ArrayList;

import model.Zwierze;
import modelList.ModelListZwierzat;

public class Wykaz {
	
	public static ArrayList<Zwierze> glowny = new ArrayList<Zwierze>();
	public static ModelListZwierzat podglad = new ModelListZwierzat();
	public static String[] rodzaje = { 
			"Kot", "Pies"
	};
	
	
	public static void wyczyscPodglad() {
		podglad.clear();
		glowny.forEach((w) -> podglad.addZwierze(w));
	}
	
	public static void wyszukajPoFragmecieNazwy(String frgmentNazwy) {
		int ilosc= podglad.size();
		if(ilosc > 1) {
			ilosc-= 1;
			for (int z = ilosc; z >= 0; z--) {
				if (!podglad.get(z).getNazwa().contains(frgmentNazwy)) {
					podglad.remove(z);
				}			
			}
			podglad.odswierz();
		}
	}
	
	public static void aktulizacjaZwierzat(int podgladZwierzeciaId, String nowaNazwa, int nowyRodzaj, int nowyWiek) {
		Zwierze stareZwierze = podglad.get(podgladZwierzeciaId);
		Zwierze noweZwierze = new Zwierze(nowaNazwa, nowyRodzaj, nowyWiek);
		podglad.set(podgladZwierzeciaId, noweZwierze);
		glowny.stream()
		.filter((z) -> (z.getNazwa().equals(stareZwierze.getNazwa()) && z.getWiek() == stareZwierze.getWiek() && z.getRodzaj() == stareZwierze.getRodzaj()))
		.forEach((z) -> z = noweZwierze);
		podglad.odswierz();
	}
	
	public static void usunZwierze(int podlgladZwierzeId) {
		Zwierze zwierzeDoUsuniecia = podglad.remove(podlgladZwierzeId);
				glowny.remove(podlgladZwierzeId);
	}
}
