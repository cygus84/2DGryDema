package narzedzia;

public class Extraktor {
	
	public static boolean przycisku(String obiekt) {
		String[] opisy = obiekt.split("\\.");
		return opisy[0].contains("etykiety");
	}

}
