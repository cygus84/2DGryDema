package app.magazyn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import app.model.Produkt;

public class Magazyn {
	private static Map<String, Produkt> produkty = new HashMap<>();
    private static final String PLIK = "magazyn.csv";
    private static Scanner scanner = new Scanner(System.in);

    public static void StartMain() {
        wczytajZPliku();
        boolean running = true;
        while (running) {
            System.out.println("\n=== MAGAZYN ===");
            System.out.println("1. Dodaj produkt");
            System.out.println("2. Usuń produkt");
            System.out.println("3. Aktualizuj ilość");
            System.out.println("4. Wyświetl produkty");
            System.out.println("5. Wyszukaj produkt");
            System.out.println("6. Generuj raport");
            System.out.println("7. Zapisz i zakończ");
            System.out.print("Wybierz opcję: ");
            String wybor = scanner.nextLine();

            switch (wybor) {
                case "1":
                    dodajProdukt();
                    break;
                case "2":
                    usunProdukt();
                    break;
                case "3":
                    aktualizujIlosc();
                    break;
                case "4":
                    wyswietlProdukty();
                    break;
                case "5":
                    wyszukajProdukt();
                    break;
                case "6":
                    generujRaport();
                    break;
                case "7":
                    zapiszDoPliku();
                    running = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
            }
        }
    }
    
    private static void dodajProdukt() {
        System.out.print("Podaj nazwę produktu: ");
        String nazwa = scanner.nextLine();
        System.out.print("Podaj ilość: ");
        int ilosc = Integer.parseInt(scanner.nextLine());
        System.out.print("Podaj kategorię: ");
        String kategoria = scanner.nextLine();

        if (produkty.containsKey(nazwa)) {
            Produkt p = produkty.get(nazwa);
            p.setIlosc(p.getIlosc() + ilosc);
            System.out.println("Produkt istnieje. Zwiększono ilość.");
        } else {
            produkty.put(nazwa, new Produkt(nazwa, ilosc, kategoria));
            System.out.println("Dodano nowy produkt.");
        }
    }

    private static void usunProdukt() {
        System.out.print("Podaj nazwę produktu do usunięcia: ");
        String nazwa = scanner.nextLine();
        if (produkty.remove(nazwa) != null) {
            System.out.println("Usunięto produkt.");
        } else {
            System.out.println("Nie znaleziono produktu.");
        }
    }

    private static void aktualizujIlosc() {
        System.out.print("Podaj nazwę produktu: ");
        String nazwa = scanner.nextLine();
        Produkt p = produkty.get(nazwa);
        if (p != null) {
            System.out.print("Podaj nową ilość: ");
            int nowaIlosc = Integer.parseInt(scanner.nextLine());
            p.setIlosc(nowaIlosc);
            System.out.println("Zaktualizowano ilość.");
        } else {
            System.out.println("Nie znaleziono produktu.");
        }
    }

    private static void wyswietlProdukty() {
        if (produkty.isEmpty()) {
            System.out.println("Brak produktów.");
        } else {
            for (Produkt p : produkty.values()) {
                System.out.println(p);
            }
        }
    }

    private static void wyszukajProdukt() {
        System.out.print("Wpisz nazwę produktu: ");
        String nazwa = scanner.nextLine();
        Produkt p = produkty.get(nazwa);
        if (p != null) {
            System.out.println("Znaleziono: " + p);
        } else {
            System.out.println("Nie znaleziono produktu.");
        }
    }

    private static void generujRaport() {
        int sumaIlosci = 0;
        Map<String, Integer> kategorie = new HashMap<>();
        for (Produkt p : produkty.values()) {
            sumaIlosci += p.getIlosc();
            kategorie.put(p.getKategoria(), kategorie.getOrDefault(p.getKategoria(), 0) + p.getIlosc());
        }
        System.out.println("\n=== RAPORT ===");
        System.out.println("Liczba różnych produktów: " + produkty.size());
        System.out.println("Suma wszystkich sztuk: " + sumaIlosci);
        for (var entry : kategorie.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void zapiszDoPliku() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PLIK))) {
            for (Produkt p : produkty.values()) {
                pw.println(p.toCSV());
            }
            System.out.println("Zapisano dane do pliku.");
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }

    private static void wczytajZPliku() {
        File plik = new File(PLIK);
        if (!plik.exists()) return;
        try (Scanner s = new Scanner(plik)) {
            while (s.hasNextLine()) {
                Produkt p = Produkt.fromCSV(s.nextLine());
                produkty.put(p.getNazwa(), p);
            }
            System.out.println("Wczytano dane z pliku.");
        } catch (Exception e) {
            System.out.println("Błąd podczas wczytywania danych: " + e.getMessage());
        }
    }
}
