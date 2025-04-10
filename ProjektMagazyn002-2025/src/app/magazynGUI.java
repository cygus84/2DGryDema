package app;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import app.model.Produkt;

public class magazynGUI extends JFrame {
	   private DefaultTableModel model;
	    private JTable table;
	    private JTextField nazwaField, iloscField, kategoriaField;
	    private Map<String, Produkt> produkty = new HashMap<>();
	    private static final String PLIK = "magazyn.csv";

	    public magazynGUI() {
	        setTitle("Magazyn - GUI");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(650, 400);
	        setLayout(new BorderLayout());

	        model = new DefaultTableModel(new String[]{"Nazwa", "Ilość", "Kategoria"}, 0);
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
	        JButton zapiszBtn = new JButton("Zapisz do pliku");

	        dolnyPanel.add(usunBtn);
	        dolnyPanel.add(aktualizujBtn);
	        dolnyPanel.add(zapiszBtn);
	        add(dolnyPanel, BorderLayout.SOUTH);

	        dodajBtn.addActionListener(e -> {
	            try {
	                String nazwa = nazwaField.getText();
	                int ilosc = Integer.parseInt(iloscField.getText());
	                String kategoria = kategoriaField.getText();

	                if (produkty.containsKey(nazwa)) {
	                    Produkt p = produkty.get(nazwa);
	                    p.setIlosc(p.getIlosc() + ilosc);
	                } else {
	                    produkty.put(nazwa, new Produkt(nazwa, ilosc, kategoria));
	                }
	                odswiezTabele();
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(this, "Podaj poprawną liczbę dla ilości.");
	            }
	        });

	        usunBtn.addActionListener(e -> {
	            int row = table.getSelectedRow();
	            if (row >= 0) {
	                String nazwa = (String) model.getValueAt(row, 0);
	                produkty.remove(nazwa);
	                odswiezTabele();
	            }
	        });

	        aktualizujBtn.addActionListener(e -> {
	            int row = table.getSelectedRow();
	            if (row >= 0) {
	                String nazwa = (String) model.getValueAt(row, 0);
	                String input = JOptionPane.showInputDialog("Nowa ilość:");
	                try {
	                    int nowaIlosc = Integer.parseInt(input);
	                    produkty.get(nazwa).setIlosc(nowaIlosc);
	                    odswiezTabele();
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(this, "Błędna liczba.");
	                }
	            }
	        });

	        zapiszBtn.addActionListener(e -> {
	            zapiszDoPliku();
	        });

	        wczytajZPliku();
	        odswiezTabele();
	        setVisible(true);
	    }

	    private void odswiezTabele() {
	        model.setRowCount(0);
	        for (Produkt p : produkty.values()) {
	            model.addRow(new Object[]{p.getNazwa(), p.getIlosc(), p.getKategoria()});
	        }
	    }

	    private void zapiszDoPliku() {
	        try (PrintWriter pw = new PrintWriter(new FileWriter(PLIK))) {
	            for (Produkt p : produkty.values()) {
	                pw.println(p.toCSV());
	            }
	            JOptionPane.showMessageDialog(this, "Zapisano do pliku.");
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(this, "Błąd zapisu: " + e.getMessage());
	        }
	    }

	    private void wczytajZPliku() {
	        File plik = new File(PLIK);
	        if (!plik.exists()) return;
	        try (Scanner scanner = new Scanner(plik)) {
	            while (scanner.hasNextLine()) {
	                Produkt p = Produkt.fromCSV(scanner.nextLine());
	                produkty.put(p.getNazwa(), p);
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Błąd odczytu: " + e.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(magazynGUI::new);
	    }
}
