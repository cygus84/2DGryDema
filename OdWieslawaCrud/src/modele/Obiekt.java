package modele;

public class Obiekt {

	private String nazwa;
	private int ilosc;
	
	public Obiekt() {
		ustaw("Obiekt", 1);
	}
	
	public Obiekt(String nazwa, int ilosc) {
		ustaw(nazwa, ilosc);
	}
	
	private void ustaw(String nazwa, int ilosc) {
		this.nazwa = nazwa;
		this.ilosc = ilosc;
	}

	public String getNazwa() {
		return nazwa;
	}

	public int getIlosc() {
		return ilosc;
	}
	
	public void updateIlosc(int ile) {
		ilosc += ile;
	}
}
