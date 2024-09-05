package model;

public class Zwierze {
	
	private String nazwa;
	private int rodzaj;
	private int wiek;
	
	public Zwierze() {
		ustaw("brak", wiek, rodzaj);
	}

	public Zwierze(String nazwa, int rodzaj, int wiek) {
		ustaw(nazwa, rodzaj, wiek);
	}
	
	private void ustaw(String nazwa, int rodzaj, int wiek) {
		this.nazwa = nazwa;
		this.rodzaj = rodzaj;
		this.wiek = wiek;
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
