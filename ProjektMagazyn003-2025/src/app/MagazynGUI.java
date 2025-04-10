package app;

//WYMAGANE: sqlite-jdbc (np. sqlite-jdbc-3.36.0.3.jar)
//POI do eksportu: poi-5.x + poi-ooxml-5.x + zależności (opcjonalnie do eksportu XLSX)

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class MagazynGUI extends JFrame {
	private DefaultTableModel model;
	private JTable table;
	private JTextField nazwaField, iloscField, kategoriaField;
	private Connection conn;

	public MagazynGUI() {
		setTitle("Magazyn - SQLite + Excel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 400);
		setLayout(new BorderLayout());

		model = new DefaultTableModel(new String[] { "Nazwa", "Ilość", "Kategoria" }, 0);
		table = new JTable(model);
		add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel panel = new JPanel(new GridLayout(2, 4));
		nazwaField = new JTextField();
		iloscField = new JTextField();
		kategoriaField = new JTextField();
		JButton dodajBtn = new JButton("Dodaj");

		panel.add(new JLabel("Nazwa:"));
		panel.add(nazwaField);
		panel.add(new JLabel("Ilość:"));
		panel.add(iloscField);
		panel.add(new JLabel("Kategoria:"));
		panel.add(kategoriaField);
		panel.add(dodajBtn);

		add(panel, BorderLayout.NORTH);

		JPanel dolnyPanel = new JPanel();
		JButton usunBtn = new JButton("Usuń");
		JButton aktualizujBtn = new JButton("Aktualizuj ilość");
		JButton eksportBtn = new JButton("Eksportuj do Excela");

		dolnyPanel.add(usunBtn);
		dolnyPanel.add(aktualizujBtn);
		dolnyPanel.add(eksportBtn);
		add(dolnyPanel, BorderLayout.SOUTH);

		dodajBtn.addActionListener(e -> dodajProdukt());
		usunBtn.addActionListener(e -> usunProdukt());
		aktualizujBtn.addActionListener(e -> aktualizujProdukt());
		eksportBtn.addActionListener(e -> eksportujDoExcela());

		polaczZBaza();
		odswiezTabele();
		setVisible(true);
	}

	private void polaczZBaza() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:magazyn.db");
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS produkty (nazwa TEXT PRIMARY KEY, ilosc INTEGER, kategoria TEXT)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void odswiezTabele() {
		try {
			model.setRowCount(0);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM produkty");
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("nazwa"), rs.getInt("ilosc"), rs.getString("kategoria") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void dodajProdukt() {
		try {
			String nazwa = nazwaField.getText();
			int ilosc = Integer.parseInt(iloscField.getText());
			String kategoria = kategoriaField.getText();

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO produkty (nazwa, ilosc, kategoria) VALUES (?, ?, ?) ON CONFLICT(nazwa) DO UPDATE SET ilosc = ilosc + excluded.ilosc");
			stmt.setString(1, nazwa);
			stmt.setInt(2, ilosc);
			stmt.setString(3, kategoria);
			stmt.executeUpdate();
			odswiezTabele();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Błąd dodawania: " + e.getMessage());
		}
	}

	private void usunProdukt() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			String nazwa = (String) model.getValueAt(row, 0);
			try {
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM produkty WHERE nazwa = ?");
				stmt.setString(1, nazwa);
				stmt.executeUpdate();
				odswiezTabele();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void aktualizujProdukt() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			String nazwa = (String) model.getValueAt(row, 0);
			String input = JOptionPane.showInputDialog("Nowa ilość:");
			try {
				int nowaIlosc = Integer.parseInt(input);
				PreparedStatement stmt = conn.prepareStatement("UPDATE produkty SET ilosc = ? WHERE nazwa = ?");
				stmt.setInt(1, nowaIlosc);
				stmt.setString(2, nazwa);
				stmt.executeUpdate();
				odswiezTabele();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Błąd: " + e.getMessage());
			}
		}
	}

	private void eksportujDoExcela() {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Magazyn");
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("Nazwa");
			header.createCell(1).setCellValue("Ilość");
			header.createCell(2).setCellValue("Kategoria");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM produkty");
			int rowNum = 1;
			while (rs.next()) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(rs.getString("nazwa"));
				row.createCell(1).setCellValue(rs.getInt("ilosc"));
				row.createCell(2).setCellValue(rs.getString("kategoria"));
			}

			try (FileOutputStream fileOut = new FileOutputStream("magazyn.xlsx")) {
				workbook.write(fileOut);
			}
			JOptionPane.showMessageDialog(this, "Eksportowano do magazyn.xlsx");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Błąd eksportu: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(MagazynGUI::new);
	}
}
