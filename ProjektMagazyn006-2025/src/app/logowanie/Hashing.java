package app.logowanie;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
	public static String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Błąd algorytmu haszowania", e);
		}
	}
}
