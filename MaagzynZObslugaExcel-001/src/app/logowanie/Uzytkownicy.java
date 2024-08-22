package app.logowanie;

import java.util.HashMap;

public class Uzytkownicy {

	private HashMap<String, String> uzytkownicy;

    public Uzytkownicy() {
        uzytkownicy = new HashMap<>();
        // Dodajemy przykładowych użytkowników
        uzytkownicy.put("admin", "admin123");  // Admin
        uzytkownicy.put("magazynier", "mag123"); // Magazynier
    }

    public boolean sprawdzUzytkownika(String login, String haslo) {
        return uzytkownicy.containsKey(login) && uzytkownicy.get(login).equals(haslo);
    }

    public boolean czyAdmin(String login) {
        return login.equals("admin");
    }
}
