package model;

public class Zwierze {
	
	private String nazwa;
	private int wiek;
	
	public Zwierze() {
		ustaw("brak", wiek);
	}

	public Zwierze(String nazwa, int wiek) {
		ustaw(nazwa, wiek);
	}
	
	private void ustaw(String nowaNazwa, int nowyWiek) {
		this.nazwa = nowaNazwa;
		this.wiek = nowyWiek;
	}

	public String getNazwa() {
		return nazwa;
	}

	public int getWiek() {
		return wiek;
	}
}
