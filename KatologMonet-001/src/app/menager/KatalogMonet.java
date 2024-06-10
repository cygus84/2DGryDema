package app.menager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.modele.Moneta;

public class KatalogMonet {
	 private List<Moneta> monety;

	    public KatalogMonet() {
	        monety = new ArrayList<>();
	    }

	    public void dodajMonete(Moneta moneta) {
	        monety.add(moneta);
	    }

	    public List<Moneta> getMonety() {
	        return monety;
	    }

	    public void zapiszDoPliku(String nazwaPliku) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nazwaPliku))) {
	            for (Moneta moneta : monety) {
	                writer.write(moneta.toString());
	                writer.newLine();
	            }
	            System.out.println("Dane zostały zapisane do pliku.");
	        } catch (IOException e) {
	            System.out.println("Wystąpił błąd podczas zapisywania danych do pliku.");
	            e.printStackTrace();
	        }
	    }

	    public void wczytajZPliku(String nazwaPliku) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(nazwaPliku))) {
	            String linia;
	            while ((linia = reader.readLine()) != null) {
	                String[] dane = linia.split(",");
	                Moneta moneta = new Moneta(dane[0], Integer.parseInt(dane[1]), dane[2], dane[3], Integer.parseInt(dane[4]));
	                dodajMonete(moneta);
	            }
	            System.out.println("Dane zostały wczytane z pliku.");
	        } catch (IOException e) {
	            System.out.println("Wystąpił błąd podczas wczytywania danych z pliku.");
	            e.printStackTrace();
	        }
	    }
}
