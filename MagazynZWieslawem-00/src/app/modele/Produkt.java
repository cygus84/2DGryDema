package app.modele;

public class Produkt {
	
	private String nazwa;
	private String lokalizacja;
	private int ilosc;
	private String opis;
	
	
	public Produkt() {
		ustaw("nazwa", "lokalizacja", 1, "opis");
	}
	
	public Produkt(String nazwa, String lokalizacja, int ilosc, String opis) {
		ustaw(nazwa, lokalizacja, ilosc, opis);
	}
	
	private void ustaw(String nazwa, String lokalizacja, int ilosc, String opis) {
		this.nazwa = nazwa;
		this.lokalizacja = lokalizacja;
		this.ilosc = ilosc;
		this.opis = opis;
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getLokalizacja() {
		return lokalizacja;
	}

	public int getIlosc() {
		return ilosc;
	}

	public String getOpis() {
		return opis;
	}
	
	public void updateIlosc(int ile) {
		int tmp = ilosc + ile;
		if(tmp < 0) {
			ilosc = 0;
		} else {
			ilosc = tmp;
		}
	}

	@Override
	public String toString() {
		return "Produkt [nazwa=" + nazwa + ", lokalizacja=" + lokalizacja + ", ilosc=" + ilosc + ", opis=" + opis + "]";
	}

	
}
