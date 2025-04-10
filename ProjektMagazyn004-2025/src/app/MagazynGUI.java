package app;
// WYMAGANE: sqlite-jdbc (np. sqlite-jdbc-3.36.0.3.jar)

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MagazynGUI extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JTextField nazwaField, iloscField, kategoriaField, szukajField;
    private Connection conn;
    private JLabel statystykiLabel;

    public MagazynGUI() {
        setTitle("Magazyn - SQLite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Nazwa", "Ilość", "Kategoria"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(3, 4));
        nazwaField = new JTextField();
        iloscField = new JTextField();
        kategoriaField = new JTextField();
        szukajField = new JTextField();
        JButton dodajBtn = new JButton("Dodaj");
        JButton szukajBtn = new JButton("Szukaj");

        panel.add(new JLabel("Nazwa:"));
        panel.add(nazwaField);
        panel.add(new JLabel("Ilość:"));
        panel.add(iloscField);
        panel.add(new JLabel("Kategoria:"));
        panel.add(kategoriaField);
        panel.add(dodajBtn);
        panel.add(new JLabel());
        panel.add(new JLabel("Szukaj (nazwa):"));
        panel.add(szukajField);
        panel.add(szukajBtn);

        add(panel, BorderLayout.NORTH);

        JPanel dolnyPanel = new JPanel();
        JButton usunBtn = new JButton("Usuń");
        JButton aktualizujBtn = new JButton("Aktualizuj ilość");
        statystykiLabel = new JLabel("Łączna ilość: 0");

        dolnyPanel.add(usunBtn);
        dolnyPanel.add(aktualizujBtn);
        dolnyPanel.add(statystykiLabel);
        add(dolnyPanel, BorderLayout.SOUTH);

        dodajBtn.addActionListener(e -> dodajProdukt());
        usunBtn.addActionListener(e -> usunProdukt());
        aktualizujBtn.addActionListener(e -> aktualizujProdukt());
        szukajBtn.addActionListener(e -> szukajProdukt());

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
            int suma = 0;
            while (rs.next()) {
                int ilosc = rs.getInt("ilosc");
                suma += ilosc;
                model.addRow(new Object[]{rs.getString("nazwa"), ilosc, rs.getString("kategoria")});
            }
            statystykiLabel.setText("Łączna ilość: " + suma);
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
                "INSERT INTO produkty (nazwa, ilosc, kategoria) VALUES (?, ?, ?) ON CONFLICT(nazwa) DO UPDATE SET ilosc = ilosc + excluded.ilosc"
            );
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

    private void szukajProdukt() {
        String szukany = szukajField.getText().trim();
        try {
            model.setRowCount(0);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM produkty WHERE nazwa LIKE ?");
            stmt.setString(1, "%" + szukany + "%");
            ResultSet rs = stmt.executeQuery();
            int suma = 0;
            while (rs.next()) {
                int ilosc = rs.getInt("ilosc");
                suma += ilosc;
                model.addRow(new Object[]{rs.getString("nazwa"), ilosc, rs.getString("kategoria")});
            }
            statystykiLabel.setText("Łączna ilość (wyniki): " + suma);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd wyszukiwania: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MagazynGUI::new);
    }
}
