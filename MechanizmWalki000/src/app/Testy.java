package app;

import app.narzedzia.Generator;
import app.oddzialy.OddzialBojowy;

public class Testy {

	private OddzialBojowy graczCygus; // gracz A
	private OddzialBojowy graczSzamanGN;// gracz B
	private boolean walka;
	private int wygranaCygus;// gracz A
	private int wygranaSzamanGN;// gracz B
	private boolean pokazywanie;

	public Testy() {

		pokaz("Testy - start");
		pokazywanie = false;
		//pojedynek(1); // wariant 1
		//pojedynek(2); // waRINAT 2
		for(int l = 1; l <= 100; l++) {
			podsumowanie(">>>>>> Losoawnaie " + l + "<<<<<<");
			for(int w = 1; w <= 2; w++) {
				wygranaCygus = 0;
				wygranaSzamanGN = 0;
				for (int p = 1; p <= 100; p++) {
					pojedynek(w);
				}
				
				podsumowanie(String.format("Wariant %d, wygrane gracza A = %03d, wygrane gracza B = %03d",
						+  w, wygranaCygus, wygranaSzamanGN));
			}
		}
		pokaz("Testy - koniec");

	}

	private void pojedynek(int wariant) {
		tworzenieGraczy();
		pokaz("$$$Pojedynek - poczatek$$$");
		walka = true;
		while (walka) {
			switch (wariant) {
			case 1: // wariant1
				rundaWariant1();
				break;
			case 2: // wariant 2
				rundaWariant2();
				break;
			default:
				walka = false;
				break;
			}
		}
		pokaz("$$$Pojedynek - koniec$$$");
	}

	private void rundaWariant2() {
		pokaz("### Runda pojedynek (W2) - poczatek ###");
		// Atak gracza A
		int iloscA = graczCygus.getIlosc();
		int iloscB = graczSzamanGN.getIlosc();
		int atak = Generator.ataku(graczCygus.getWartoscAtaku());
		int obrona = Generator.obrony(graczSzamanGN.getWartoscObrony());
		int rany = Generator.rany(atak, obrona);
		pokaz("Gracz A: atak = " + atak + ", ilosc = " + iloscA);
		pokaz("Gracz B: obrona = " + obrona + ", ilosc = " + iloscB);
		pokaz("Zadane rany: " + rany);
		if (rany == 0) {
			pokaz("Zaden gracz nie odnosi ran!");
		} else {
			if (rany < 0) {
				rany = ((rany / 2) + 1) * -1;
			}

			pokaz("Gracz B otrzymuje rany: " + rany);
			graczSzamanGN.setOtrzymaneRany(rany);
			iloscB = graczSzamanGN.getIlosc();
			pokaz("Aktulana ilosc jednostek gracza B: " + iloscB);
			if (!graczSzamanGN.isZyje()) {
				pokaz(">>> Gracz B przegrywa! <<<");
				wygranaCygus += 1;
				walka = false;
			}

		}
		// Atak gracza B --- SzamanaGN
		if (walka) {
			atak = Generator.ataku(graczSzamanGN.getWartoscAtaku());
			obrona = Generator.ataku(graczCygus.getWartoscObrony());
			rany = Generator.rany(atak, obrona);
			pokaz("Gracz B: atak = " + atak + ", ilosc = " + iloscB);
			pokaz("Gracz A: obrona = " + obrona + ", ilosc = " + iloscA);
			pokaz("Zdane rany: " + rany);
			if (rany == 0) {
				pokaz("Zaden gracz nie odnosi ran!");
			} else {
				if (rany > 0) {
					rany *= -1;
					rany = (rany / 2) + 1;
				}
				pokaz("Gracz A otrzymuje rany: " + rany);
				graczCygus.setOtrzymaneRany(rany);
				iloscA = graczCygus.getIlosc();
				pokaz("Aktualna ilosc jednostek gracza A: " + iloscA);
				if (!graczCygus.isZyje()) {
					pokaz(">>> Gracz A przegrywa! <<<");
					wygranaSzamanGN += 1;
					walka = false;
				}
			}
		}
		pokaz("*** Runda pojedynku (W2) - koniec ***");

	}

	private void rundaWariant1() {
		pokaz("### Runda pojedynek (W1) - poczatek ###");
		// Atak gracza A
		int iloscA = graczCygus.getIlosc();
		int iloscB = graczSzamanGN.getIlosc();
		int atak = Generator.ataku(graczCygus.getWartoscAtaku());
		int obrona = Generator.obrony(graczSzamanGN.getWartoscObrony());
		int rany = Generator.rany(atak, obrona);
		pokaz("Gracz A: atak = " + atak + ", ilosc = " + iloscA);
		pokaz("Gracz B: obrona = " + obrona + ", ilosc = " + iloscB);
		pokaz("Zadane rany: " + rany);
		if (rany == 0) {
			pokaz("Zaden gracz nie odnosi ran!");
		} else {
			if (rany > 0) {
				pokaz("Gracz B otrzymuje rany: " + rany);
				graczSzamanGN.setOtrzymaneRany(rany);
				iloscB = graczSzamanGN.getIlosc();
				pokaz("Aktulana ilosc jednostek gracza B: " + iloscB);
				if (!graczSzamanGN.isZyje()) {
					pokaz(">>> Gracz B przegrywa! <<<");
					wygranaCygus += 1;
					walka = false;
				}
			} else {
				rany *= -1;
				pokaz("Gracz A otrzymuje rany: " + rany);
				graczCygus.setOtrzymaneRany(rany);
				iloscA = graczCygus.getIlosc();
				pokaz("Aktualna ilosc jednostek gracza A: " + iloscA);
				if (!graczCygus.isZyje()) {
					pokaz(">>> Gracz A przegrywa! <<<");
					wygranaSzamanGN += 1;
					walka = false;
				}
			}
		}
		// Atak gracza B --- SzamanaGN
		if (walka) {
			atak = Generator.ataku(graczSzamanGN.getWartoscAtaku());
			obrona = Generator.ataku(graczCygus.getWartoscObrony());
			rany = Generator.rany(atak, obrona);
			pokaz("Gracz B: atak = " + atak + ", ilosc = " + iloscB);
			pokaz("Gracz A: obrona = " + obrona + ", ilosc = " + iloscA);
			pokaz("Zdane rany: " + rany);
			if (rany == 0) {
				pokaz("Zaden gracz nie odnosi ran!");
			} else {
				if (rany > 0) {
					pokaz("Gracz A otrzymuje rany: " + rany);
					graczCygus.setOtrzymaneRany(rany);
					iloscA = graczCygus.getIlosc();
					pokaz("Aktualna ilosc jednostek gracza A: " + iloscA);
					if (!graczCygus.isZyje()) {
						pokaz(">>> Gracz A przegrywa! <<<");
						wygranaCygus += 1;
						walka = false;
					}
				} else {
					rany *= -1;
					pokaz("Gracz B otrzymuje rany: " + rany);
					graczSzamanGN.setOtrzymaneRany(rany);
					iloscB = graczSzamanGN.getIlosc();
					pokaz("Aktualna ilosc jednostek gracza B: " + iloscB);
					if (!graczSzamanGN.isZyje()) {
						pokaz(">>> Gracz B przegrywa! <<<");
						wygranaSzamanGN += 1;
						walka = false;
					}
				}
			}
		}
		pokaz("*** Runda pojedynku (W1) - koniec ***");
	}

	private void tworzenieGraczy() {
		pokaz("---------------------------------");
		pokaz("***Tworzenie graczy - Start***");
		graczCygus = new OddzialBojowy(10, 10, 10, 10, 10);
		graczSzamanGN = new OddzialBojowy(10, 10, 10, 10, 10);
		pokaz(graczCygus.toString());
		pokaz(graczSzamanGN.toString());
		pokaz("***Tworzenie graczy - Koniec***");

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
