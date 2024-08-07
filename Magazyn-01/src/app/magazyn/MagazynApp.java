package app.magazyn;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import app.produkt.Produkt;
import java.awt.Font;

public class MagazynApp extends JFrame{
	private ArrayList<Produkt> produkty;
    private DefaultTableModel tableModel;

    public MagazynApp() {
    	getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
        produkty = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Magazyn");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());

        // Table model
        tableModel = new DefaultTableModel(new Object[]{"Nazwa", "Ilosc", "Lokalizacja", "Opis"}, 0);
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel dodawania i usuwania produktów
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        JLabel label = new JLabel("Nazwa produktu:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(label);
        JTextField nazwaField = new JTextField();
        panel.add(nazwaField);

        JLabel label_1 = new JLabel("Ilosc:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(label_1);
        JTextField iloscField = new JTextField();
        iloscField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(iloscField);

        JLabel label_2 = new JLabel("Lokalizacja:");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(label_2);
        JTextField lokalizacjaField = new JTextField();
        lokalizacjaField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(lokalizacjaField);

        JLabel label_3 = new JLabel("Opis:");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(label_3);
        JTextField opisField = new JTextField();
        opisField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(opisField);

        JButton dodajButton = new JButton("Dodaj produkt");
        dodajButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazwa = nazwaField.getText();
                int ilosc = Integer.parseInt(iloscField.getText());
                String lokalizacja = lokalizacjaField.getText();
                String opis = opisField.getText();
                dodajProdukt(nazwa, ilosc, lokalizacja, opis);
                nazwaField.setText("");
                iloscField.setText("");
                lokalizacjaField.setText("");
                opisField.setText("");
            }
        });
        panel.add(dodajButton);

        JButton usunButton = new JButton("Usun produkt");
        usunButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazwa = nazwaField.getText();
                int ilosc = Integer.parseInt(iloscField.getText());
                usunProdukt(nazwa, ilosc);
                nazwaField.setText("");
                iloscField.setText("");
                lokalizacjaField.setText("");
                opisField.setText("");
            }
        });
        panel.add(usunButton);

        // Przyciski zapisu i ładowania
        JButton zapiszButton = new JButton("Zapisz");
        zapiszButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapiszDane();
            }
        });
        panel.add(zapiszButton);

        JButton wczytajButton = new JButton("Wczytaj");
        wczytajButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        wczytajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wczytajDane();
            }
        });
        panel.add(wczytajButton);

        getContentPane().add(panel, BorderLayout.SOUTH);
    }

    private void dodajProdukt(String nazwa, int ilosc, String lokalizacja, String opis) {
        for (Produkt produkt : produkty) {
            if (produkt.getNazwa().equalsIgnoreCase(nazwa)) {
                produkt.setIlosc(produkt.getIlosc() + ilosc);
                updateTable();
                return;
            }
        }
        produkty.add(new Produkt(nazwa, ilosc, lokalizacja, opis));
        updateTable();
    }

    private void usunProdukt(String nazwa, int ilosc) {
        for (Produkt produkt : produkty) {
            if (produkt.getNazwa().equalsIgnoreCase(nazwa)) {
                if (produkt.getIlosc() > ilosc) {
                    produkt.setIlosc(produkt.getIlosc() - ilosc);
                } else {
                    produkty.remove(produkt);
                }
                updateTable();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Produkt nie znaleziony w magazynie.");
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Produkt produkt : produkty) {
            tableModel.addRow(new Object[]{produkt.getNazwa(), produkt.getIlosc(), produkt.getLokalizacja(), produkt.getOpis()});
        }
    }

    private void zapiszDane() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("magazyn.dat"))) {
            oos.writeObject(produkty);
            JOptionPane.showMessageDialog(this, "Dane zapisane pomyślnie.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Błąd zapisu danych: " + e.getMessage());
        }
    }

    private void wczytajDane() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("magazyn.dat"))) {
            produkty = (ArrayList<Produkt>) ois.readObject();
            updateTable();
            JOptionPane.showMessageDialog(this, "Dane wczytane pomyślnie.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Błąd wczytywania danych: " + e.getMessage());
        }
    }
}
