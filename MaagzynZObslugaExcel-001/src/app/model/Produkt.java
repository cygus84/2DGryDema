package app.model;

public class Produkt {

	private String nazwa;
    private int ilosc;
    private String kategoria;
    
    public Produkt(String nazwa, int ilosc, String kategoria) {
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.kategoria = kategoria;
    }

	public String getNazwa() {
		return nazwa;
	}

	public int getIlosc() {
		return ilosc;
	}

	public String getKategoria() {
		return kategoria;
	}
    
    
}
