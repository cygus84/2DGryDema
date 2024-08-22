package app.logowanie;

public class Uzytkownik {

	private String login;
    private String haslo;
    private String rola; // admin, magazynier

    public Uzytkownik(String login, String haslo, String rola) {
        this.login = login;
        this.haslo = haslo;
        this.rola = rola;
    }

    public boolean czyAdmin() {
        return rola.equals("admin");
    }

    public boolean czyMagazynier() {
        return rola.equals("magazynier");
    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

    
}
