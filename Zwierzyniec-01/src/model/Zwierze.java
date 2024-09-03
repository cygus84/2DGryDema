package model;

public class Zwierze {
	
	private String nazwa;
	private int rodzaj;
	private int wiek;
	
	public Zwierze() {
		ustaw("brak", wiek, rodzaj);
	}

	public Zwierze(String nazwa, int wiek, int rodzaj) {
		ustaw(nazwa, wiek, rodzaj);
	}
	
	private void ustaw(String nowaNazwa, int nowyWiek, int nowyRodzaj) {
		this.nazwa = nowaNazwa;
		this.wiek = nowyWiek;
		this.rodzaj = nowyRodzaj;
	}

	public String getNazwa() {
		return nazwa;
	}

	public int getWiek() {
		return wiek;
	}
	
	public int getRodzaj() {
		return rodzaj;
	}
}
