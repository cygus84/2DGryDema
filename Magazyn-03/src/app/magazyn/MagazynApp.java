package app.magazyn;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import app.produkty.Produkt;

public class MagazynApp extends JFrame {
	 private ArrayList<Produkt> produkty;
	    private DefaultListModel<String> listModel;
	    private JList<String> produktList;

	    public MagazynApp() {
	        produkty = new ArrayList<>();
	        initializeUI();
	    }

	    private void initializeUI() {
	        setTitle("Magazyn");
	        setSize(800, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        setLayout(new BorderLayout());

	        // Model listy
	        listModel = new DefaultListModel<>();
	        produktList = new JList<>(listModel);
	        add(new JScrollPane(produktList), BorderLayout.CENTER);

	        // Panel główny, który będzie zawierał inne panele
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new GridLayout(1, 3));

	        // Panel dodawania produktów
	        JPanel dodajPanel = new JPanel();
	        dodajPanel.setBorder(BorderFactory.createTitledBorder("Dodaj Produkt"));
	        dodajPanel.setLayout(new GridLayout(5, 2));

	        dodajPanel.add(new JLabel("Nazwa produktu:"));
	        JTextField nazwaField = new JTextField();
	        dodajPanel.add(nazwaField);

	        dodajPanel.add(new JLabel("Ilosc:"));
	        JTextField iloscField = new JTextField();
	        dodajPanel.add(iloscField);

	        dodajPanel.add(new JLabel("Lokalizacja:"));
	        JTextField lokalizacjaField = new JTextField();
	        dodajPanel.add(lokalizacjaField);

	        dodajPanel.add(new JLabel("Opis:"));
	        JTextField opisField = new JTextField();
	        dodajPanel.add(opisField);

	        JButton dodajButton = new JButton("Dodaj");
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
	        dodajPanel.add(dodajButton);

	        // Panel usuwania produktów
	        JPanel usunPanel = new JPanel();
	        usunPanel.setBorder(BorderFactory.createTitledBorder("Usuń Produkt"));
	        usunPanel.setLayout(new GridLayout(3, 2));

	        usunPanel.add(new JLabel("Nazwa produktu:"));
	        JTextField usunNazwaField = new JTextField();
	        usunPanel.add(usunNazwaField);

	        usunPanel.add(new JLabel("Ilosc:"));
	        JTextField usunIloscField = new JTextField();
	        usunPanel.add(usunIloscField);

	        JButton usunButton = new JButton("Usuń");
	        usunButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String nazwa = usunNazwaField.getText();
	                int ilosc = Integer.parseInt(usunIloscField.getText());
	                usunProdukt(nazwa, ilosc);
	                usunNazwaField.setText("");
	                usunIloscField.setText("");
	            }
	        });
	        usunPanel.add(usunButton);

	        // Panel wyszukiwania produktów
	        JPanel szukajPanel = new JPanel();
	        szukajPanel.setBorder(BorderFactory.createTitledBorder("Szukaj Produktu"));
	        szukajPanel.setLayout(new GridLayout(2, 2));

	        szukajPanel.add(new JLabel("Szukaj:"));
	        JTextField szukajField = new JTextField();
	        szukajPanel.add(szukajField);

	        JButton szukajButton = new JButton("Szukaj");
	        szukajButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String kryterium = szukajField.getText();
	                szukajProdukt(kryterium);
	            }
	        });
	        szukajPanel.add(szukajButton);

	        // Dodanie paneli do panelu głównego
	        mainPanel.add(dodajPanel);
	        mainPanel.add(usunPanel);
	        mainPanel.add(szukajPanel);

	        // Panel zapisu i wczytywania danych
	        JPanel zapiszWczytajPanel = new JPanel();
	        zapiszWczytajPanel.setLayout(new FlowLayout());

	        JButton zapiszButton = new JButton("Zapisz");
	        zapiszButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                zapiszDane();
	            }
	        });
	        zapiszWczytajPanel.add(zapiszButton);

	        JButton wczytajButton = new JButton("Wczytaj");
	        wczytajButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                wczytajDane();
	            }
	        });
	        zapiszWczytajPanel.add(wczytajButton);

	        // Dodanie paneli do okna głównego
	        add(mainPanel, BorderLayout.CENTER);
	        add(zapiszWczytajPanel, BorderLayout.SOUTH);
	    }

	    private void dodajProdukt(String nazwa, int ilosc, String lokalizacja, String opis) {
	        for (Produkt produkt : produkty) {
	            if (produkt.getNazwa().equalsIgnoreCase(nazwa)) {
	                produkt.setIlosc(produkt.getIlosc() + ilosc);
	                updateList(produkty);
	                return;
	            }
	        }
	        produkty.add(new Produkt(nazwa, ilosc, lokalizacja, opis));
	        updateList(produkty);
	    }

	    private void usunProdukt(String nazwa, int ilosc) {
	        for (Produkt produkt : produkty) {
	            if (produkt.getNazwa().equalsIgnoreCase(nazwa)) {
	                if (produkt.getIlosc() > ilosc) {
	                    produkt.setIlosc(produkt.getIlosc() - ilosc);
	                } else {
	                    produkty.remove(produkt);
	                }
	                updateList(produkty);
	                return;
	            }
	        }
	        JOptionPane.showMessageDialog(this, "Produkt nie znaleziony w magazynie.");
	    }

	    private void updateList(ArrayList<Produkt> produkty) {
	        listModel.clear();
	        for (Produkt produkt : produkty) {
	            listModel.addElement(produkt.toString());
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
	            updateList(produkty);
	            JOptionPane.showMessageDialog(this, "Dane wczytane pomyślnie.");
	        } catch (IOException | ClassNotFoundException e) {
	            JOptionPane.showMessageDialog(this, "Błąd wczytywania danych: " + e.getMessage());
	        }
	    }

	    private void szukajProdukt(String kryterium) {
	        ArrayList<Produkt> wyniki = new ArrayList<>();
	        for (Produkt produkt : produkty) {
	            if (produkt.getNazwa().toLowerCase().contains(kryterium.toLowerCase()) ||
	                produkt.getLokalizacja().toLowerCase().contains(kryterium.toLowerCase()) ||
	                produkt.getOpis().toLowerCase().contains(kryterium.toLowerCase())) {
	                wyniki.add(produkt);
	            }
	        }
	        updateList(wyniki);
	    }
}
