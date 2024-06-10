package app.modele;

public class Moneta {
	private String kraj;
    private int rok;
    private String nominal;
    private String material;
    private int ilosc;

    public Moneta(String kraj, int rok, String nominal, String material, int ilosc) {
        this.kraj = kraj;
        this.rok = rok;
        this.nominal = nominal;
        this.material = material;
        this.ilosc = ilosc;
    }

    public String getKraj() {
        return kraj;
    }

    public int getRok() {
        return rok;
    }

    public String getNominal() {
        return nominal;
    }

    public String getMaterial() {
        return material;
    }

    public int getIlosc() {
        return ilosc;
    }

    @Override
    public String toString() {
        return kraj + "," + rok + "," + nominal + "," + material + "," + ilosc;
    }
}
