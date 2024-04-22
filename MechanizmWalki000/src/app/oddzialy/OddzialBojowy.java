package app.oddzialy;

public class OddzialBojowy {
	
	private int ilosc;
	private int atak;
	private int obrona;
	private int sila;
	private int zycie;
	public OddzialBojowy(int ilosc, int atak, int obrona, int sila, int zycie) {
		this.ilosc = ilosc;
		this.atak = atak;
		this.obrona = obrona;
		this.sila = sila;
		this.zycie = zycie;
	}
	public int getIlosc() {
		return ilosc;
	}
	public int getAtak() {
		return atak;
	}
	public int getObrona() {
		return obrona;
	}
	public int getSila() {
		return sila;
	}
	@Override
	public String toString() {
		return "OddzialBojowy [ilosc=" + ilosc + ", atak=" + atak + ", obrona=" + obrona + ", sila=" + sila + ", zycie="
				+ zycie + "]";
	}
	
	public int getWartoscAtaku() {
		return ilosc * atak * sila;
	}
	
	public int getWartoscObrony() {
		return ilosc * obrona * sila;
	}
	
	public boolean isZyje() {
		return ilosc > 0;
	}
	
	public void setOtrzymaneRany(int rany) {
		int wynik = ilosc - ((rany / zycie) + ((rany % zycie > 0) ? 1 : 0));
		ilosc = (wynik < 0) ? 0: wynik;
	}

}
