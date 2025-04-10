package app.logowanie;

import javax.swing.*;

import app.MagazynGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginWindow extends JFrame {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JLabel messageLabel;

	public LoginWindow() {
		setTitle("Logowanie");
		setSize(300, 150);
		setLayout(new GridLayout(3, 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new JLabel("Użytkownik:"));
		usernameField = new JTextField();
		add(usernameField);

		add(new JLabel("Hasło:"));
		passwordField = new JPasswordField();
		add(passwordField);

		loginButton = new JButton("Zaloguj");
		add(loginButton);

		messageLabel = new JLabel("");
		add(messageLabel);

		loginButton.addActionListener(e -> login());

		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void login() {
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());

		if (validateLogin(username, password)) {
			JOptionPane.showMessageDialog(this, "Logowanie udane.");
			dispose(); // Zamknij okno logowania
			new MagazynGUI(); // Otwórz główne okno aplikacji
		} else {
			messageLabel.setText("Błędne dane logowania.");
		}
	}

	private boolean validateLogin(String username, String password) {
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:magazyn.db")) {
			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, username);
				stmt.setString(2, password);
				try (ResultSet rs = stmt.executeQuery()) {
					return rs.next(); // Jeśli użytkownik istnieje, zwróci true
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Błąd logowania: " + e.getMessage());
		}
		return false;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(LoginWindow::new); // Uruchom okno logowania
	}

}
