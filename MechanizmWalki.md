# Mechanizm walka - przykładowe dwa warianty

## Cel

Dwie grupy jednostek bojowych (oddziały bojowe) walczą do całkowitej eliminacji jednej z nich.

Do celów testowych użyć tylko jeden rodzaj jednostki bojowej.

## Przygotowanie

- Zdefiniowanie wzorca oddziału:

```
public class OddzialBojowy {

	private int ilosc;
	private int atak;
	private int obrona;
	private int sila;
	private int zycie;

	public OddzialBojowy(int ilosc, int atak, int obrona, 
		int sila, int zycie) {
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
		return "Duszek [ilość=" + ilosc + "atak=" + atak 
			+ ", obrona=" + obrona + ", sila=" + sila 
			+ ", zycie=" + zycie + "]";
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
		ilosc = (wynik < 0) ? 0 : wynik;
	}
}
```

- Przygotowanie generatora zapewniającego pseudolosowość parametrów walki oddziałów bojowych:

```
public class Generator {

	private static Random generator = new Random();
	
	public static int ataku(int atakGracza) {
		return (atakGracza / 2) + generator.nextInt(atakGracza);
	}
	
	public static int obrony(int obronaGracza) {
		return (obronaGracza / 2) + generator.nextInt(obronaGracza);
	}

	public static int ran(int atak, int obrona) {
		int wynik = atak - obrona;
		if (wynik != 0) {
			return wynik / 10;
		}
		return 0;
	}
}
```

## Testy

- Testowanie dwóch wariantów walki:

```
public class Testy {

	private OddzialBojowy graczA;
	private OddzialBojowy graczB;
	private boolean walka;
	private int wygraneA;
	private int wygraneB;
	private boolean pokazywanie;
	
	public Testy() {
		pokaz("Testy - start");
		pokazywanie = false;
		//pojedynek(1); // Wariant 1
		//pojedynek(2); // Wariant 2
		for (int l = 1; l <= 100; l++) {
			podsumowanie("<<<<<<<<<<Losowanie " + l + ">>>>>>>>>>>>");
			for (int w = 1; w <= 2; w++) {
				wygraneA = 0;
				wygraneB = 0;
				for (int p = 1; p <= 100; p++) {
					pojedynek(w);
				}
				podsumowanie(
					String.format(
						"Wariant %d, wygrane gracza A = %03d, wygrane gracza B = %03d",
						w, wygraneA, wygraneB));
			}
		}
		pokaz("Testy - koniec");
	}

	private void tworzenieGraczy() {
		pokaz("---------------------------------------------");
		pokaz("---Tworzenie graczy - Start---");
		graczA = new OddzialBojowy(10, 10, 10, 10, 10);
		graczB = new OddzialBojowy(10, 10, 10, 10, 10);
		pokaz(graczA.toString());
		pokaz(graczB.toString());
		pokaz("---Tworzenie graczy - Koniec---");
	}
	
	private void rundaWariant1() {
		pokaz("### Runda pojedynku (W1) - początek ###");
		// Atak gracza A:
		int iloscA = graczA.getIlosc();
		int iloscB = graczB.getIlosc();
		int atak = Generator.ataku(graczA.getWartoscAtaku());
		int obrona = Generator.obrony(graczB.getWartoscObrony());
		int rany = Generator.ran(atak, obrona);
		pokaz("Gracz A: atak = " + atak + ", ilość = " + iloscA);
		pokaz("Gracz B: obrona = " + obrona + ", ilość = " + iloscB);
		pokaz("Zadane rany: " + rany);
		if (rany == 0) {
			pokaz("Żaden gracz nie odnosi ran!");
		} else {
			if (rany > 0) {
				pokaz("Gracz B otrzymuje rany: " + rany);
				graczB.setOtrzymaneRany(rany);
				iloscB = graczB.getIlosc();
				pokaz("Aktualna ilość jednostek gracza B: " + iloscB);
				if (!graczB.isZyje()) {
					pokaz(">>> Gracz B przegrywa! <<<<");
					wygraneA += 1;
					walka = false;
				}
			} else {
				rany *= -1;
				pokaz("Gracz A otrzymuje rany: " + rany);
				graczA.setOtrzymaneRany(rany);
				iloscA = graczA.getIlosc();
				pokaz("Aktualna ilość jednostek gracza A: " + iloscA);
				if (!graczA.isZyje()) {
					pokaz(">>> Gracz A przegrywa! <<<<");
					wygraneB += 1;
					walka = false;
				}
			}
		}
		// Atak gracza B:
		if (walka) {
			atak = Generator.ataku(graczB.getWartoscAtaku());
			obrona = Generator.obrony(graczA.getWartoscObrony());
			rany = Generator.ran(atak, obrona);
			pokaz("Gracz B: atak = " + atak + ", ilość = " + iloscB);
			pokaz("Gracz A: obrona = " + obrona + ", ilość = " + iloscA);
			pokaz("Zadane rany: " + rany);
			if (rany == 0) {
				pokaz("Żaden gracz nie odnosi ran!");
			} else {
				if (rany > 0) {
					pokaz("Gracz A otrzymuje rany: " + rany);
					graczA.setOtrzymaneRany(rany);
					iloscA = graczA.getIlosc();
					pokaz("Aktualna ilość jednostek gracza A: " + iloscA);
					if (!graczA.isZyje()) {
						pokaz(">>> Gracz A przegrywa! <<<<");
						wygraneA += 1;
						walka = false;
					}
				} else {
					rany *= -1;
					pokaz("Gracz B otrzymuje rany: " + rany);
					graczB.setOtrzymaneRany(rany);
					iloscB = graczB.getIlosc();
					pokaz("Aktualna ilość jednostek gracza B: " + iloscB);
					if (!graczB.isZyje()) {
						pokaz(">>> Gracz B przegrywa! <<<<");
						wygraneB += 1;
						walka = false;
					}
				}
			}
		}
		pokaz("### Runda pojedynku (W1) - koniec ###");
	}
	
	private void rundaWariant2() {
		pokaz("### Runda pojedynku (W2) - początek ###");
		// Atak gracza A:
		int iloscA = graczA.getIlosc();
		int iloscB = graczB.getIlosc();
		int atak = Generator.ataku(graczA.getWartoscAtaku());
		int obrona = Generator.obrony(graczB.getWartoscObrony());
		int rany = Generator.ran(atak, obrona);
		pokaz("Gracz A: atak = " + atak + ", ilość = " + iloscA);
		pokaz("Gracz B: obrona = " + obrona + ", ilość = " + iloscB);
		pokaz("Zadane rany: " + rany);
		if (rany == 0) {
			pokaz("Żaden gracz nie odnosi ran!");
		} else {
			if (rany < 0) {
				rany = ((rany / 2) + 1) * -1;
			}
			pokaz("Gracz B otrzymuje rany: " + rany);
			graczB.setOtrzymaneRany(rany);
			iloscB = graczB.getIlosc();
			pokaz("Aktualna ilość jednostek gracza B: " + iloscB);
			if (!graczB.isZyje()) {
				pokaz(">>> Gracz B przegrywa! <<<<");
				wygraneA += 1;
				walka = false;
			}
		}
		// Atak gracza B:
		if (walka) {
			atak = Generator.ataku(graczB.getWartoscAtaku());
			obrona = Generator.obrony(graczA.getWartoscObrony());
			rany = Generator.ran(atak, obrona);
			pokaz("Gracz B: atak = " + atak + ", ilość = " + iloscB);
			pokaz("Gracz A: obrona = " + obrona + ", ilość = " + iloscA);
			pokaz("Zadane rany: " + rany);
			if (rany == 0) {
				pokaz("Żaden gracz nie odnosi ran!");
			} else {
				if (rany < 0) {
					rany *= -1;
					rany = (rany / 2) + 1;
				}
				pokaz("Gracz A otrzymuje rany: " + rany);
				graczA.setOtrzymaneRany(rany);
				iloscA = graczA.getIlosc();
				pokaz("Aktualna ilość jednostek gracza A: " + iloscA);
				if (!graczA.isZyje()) {
					pokaz(">>> Gracz A przegrywa! <<<<");
					wygraneB += 1;
					walka = false;
				}
			}
		}
		pokaz("### Runda pojedynku (W2) - koniec ###");
	}
	
	private void pojedynek(int wariant) {
		tworzenieGraczy();
		pokaz("---Pojedynek - początek---");
		walka = true;
		while(walka) {
			switch(wariant) {
				case 1: // Wariant 1
					rundaWariant1();
					break;
				case 2: // Wariant 1
					rundaWariant2();
					break;
				default:
					walka = false;
					break;
			}
			
		}
		pokaz("---Pojedynek - koniec---");
	}
	
	private void pokaz(String string) {
		if (pokazywanie) {
			System.out.println("<Testy> " + string);
		}
	}
	
	private void podsumowanie(String string) {
		System.out.println("<Testy> " + string);
	}
}
```

