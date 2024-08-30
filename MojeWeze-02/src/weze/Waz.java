package weze;

public class Waz {
	
	private String nazwa;
	private int dlugosc;
	
	public Waz() {
		ustaw("Nazwa", dlugosc);
	}
	
	public Waz(String nazwa, int dlugosc) {
		ustaw(nazwa, dlugosc);
	}

	private void ustaw(String nazwa, int dlugosc) {
		this.nazwa = nazwa;
		this.dlugosc = dlugosc;
	}

	public String getNazwa() {
		return nazwa;
	}
	
	public int getDlugosc() {
		return dlugosc;
	}
	
	
}
