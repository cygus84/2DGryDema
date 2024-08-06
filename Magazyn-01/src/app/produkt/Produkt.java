package app.produkt;

import java.io.Serializable;

public class Produkt implements Serializable{
	private static final long serialVersionUID = 1L;
    private String nazwa;
    private int ilosc;
    private String lokalizacja;
    private String opis;
    

    public Produkt(String nazwa, int ilosc, String lokalizacja, String opis) {
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.lokalizacja = lokalizacja;
        this.opis = opis;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getIlosc() {
        return ilosc;
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public String getOpis() {
        return opis;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "Produkt: " + nazwa + ", Ilosc: " + ilosc + ", Lokalizacja: " + lokalizacja + ", Opis: " + opis;
    }
}
