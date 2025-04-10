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

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	@Override
	public String toString() {
		return "Produkt: " + nazwa + ", Ilość: " + ilosc + ", Kategoria: " + kategoria;
	}

	public String toCSV() {
		return nazwa + "," + ilosc + "," + kategoria;
	}

	public static Produkt fromCSV(String line) {
		String[] parts = line.split(",");
		return new Produkt(parts[0], Integer.parseInt(parts[1]), parts[2]);
	}

}
