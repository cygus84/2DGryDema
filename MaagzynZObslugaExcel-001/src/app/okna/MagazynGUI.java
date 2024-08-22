package app.okna;

import javax.swing.JFrame;

import app.logowanie.Uzytkownicy;

public class MagazynGUI {

	private JFrame frame;
    private Magazyn magazyn;
    private Uzytkownicy uzytkownicy;

    public MagazynGUI() {
        magazyn = new Magazyn();
        uzytkownicy = new Uzytkownicy(); // Zbiór użytkowników
        initUI(); // Zainicjowanie logowania
    }

    // Inicjalizacja okna logowania
    private void initUI() {
        frame = new JFrame("Logowanie do systemu magazynowego");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeLoginComponents(panel);

        frame.setVisible(true);
    }

    private void placeLoginComponents(JPanel panel) {
        panel.setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Login:");
        JTextField userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Hasło:");
        JPasswordField passwordText = new JPasswordField(20);

        JButton loginButton = new JButton("Zaloguj");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = userText.getText();
                String haslo = new String(passwordText.getPassword());

                // Sprawdzamy czy użytkownik istnieje i ma poprawne hasło
                if (uzytkownicy.sprawdzUzytkownika(login, haslo)) {
                    frame.dispose(); // Zamykamy okno logowania
                    otworzGlowneOkno(login); // Otwieramy okno główne aplikacji
                } else {
                    JOptionPane.showMessageDialog(frame, "Nieprawidłowy login lub hasło.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(loginButton);
    }

    // Otwieranie głównego okna aplikacji
    private void otworzGlowneOkno(String login) {
        JFrame glowneOkno = new JFrame("Magazyn - System Zarządzania");
        glowneOkno.setSize(600, 400);
        glowneOkno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        glowneOkno.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton dodajButton = new JButton("Dodaj Produkt");
        JButton wydajButton = new JButton("Wydaj Produkt");
        JButton usunButton = new JButton("Usuń Produkt");
        JButton raportButton = new JButton("Generuj Raport");

        // Sprawdzamy czy użytkownik ma prawa admina, jeśli nie - blokujemy usuwanie produktów
        if (!uzytkownicy.czyAdmin(login)) {
            usunButton.setEnabled(false);
        }

        // Dodanie akcji do przycisków
        dodajButton.addActionListener(e -> pokazFormularzDodawania());
        wydajButton.addActionListener(e -> pokazFormularzWydawania());
        usunButton.addActionListener(e -> pokazFormularzUsuwania());
        raportButton.addActionListener(e -> wygenerujRaport());

        // Dodanie przycisków do panelu
        panel.add(dodajButton);
        panel.add(wydajButton);
        panel.add(usunButton);
        panel.add(raportButton);

        glowneOkno.setVisible(true);
    }

    // Formularz do dodawania produktów
    private void pokazFormularzDodawania() {
        JFrame dodajFrame = new JFrame("Dodaj Produkt");
        dodajFrame.setSize(300, 200);
        dodajFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        dodajFrame.add(panel);
        panel.setLayout(new GridLayout(4, 2));

        JLabel nazwaLabel = new JLabel("Nazwa:");
        JTextField nazwaField = new JTextField(20);
        JLabel iloscLabel = new JLabel("Ilość:");
        JTextField iloscField = new JTextField(20);
        JLabel kategoriaLabel = new JLabel("Kategoria:");
        JTextField kategoriaField = new JTextField(20);

        JButton dodajButton = new JButton("Dodaj");
        dodajButton.addActionListener(e -> {
            String nazwa = nazwaField.getText();
            int ilosc = Integer.parseInt(iloscField.getText());
            String kategoria = kategoriaField.getText();

            try {
                magazyn.dodajProdukt(new Produkt(nazwa, ilosc, kategoria));
                JOptionPane.showMessageDialog(null, "Produkt dodany!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Błąd dodawania produktu!");
                ex.printStackTrace();
            }
        });

        panel.add(nazwaLabel);
        panel.add(nazwaField);
        panel.add(iloscLabel);
        panel.add(iloscField);
        panel.add(kategoriaLabel);
        panel.add(kategoriaField);
        panel.add(dodajButton);

        dodajFrame.setVisible(true);
    }

    // Formularz do wydawania produktów
    private void pokazFormularzWydawania() {
        JFrame wydajFrame = new JFrame("Wydaj Produkt");
        wydajFrame.setSize(300, 200);
        wydajFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        wydajFrame.add(panel);
        panel.setLayout(new GridLayout(3, 2));

        JLabel nazwaLabel = new JLabel("Nazwa:");
        JTextField nazwaField = new JTextField(20);
        JLabel iloscLabel = new JLabel("Ilość do wydania:");
        JTextField iloscField = new JTextField(20);

        JButton wydajButton = new JButton("Wydaj");
        wydajButton.addActionListener(e -> {
            String nazwa = nazwaField.getText();
            int ilosc = Integer.parseInt(iloscField.getText());

            try {
                magazyn.wydajProdukt(nazwa, ilosc);
                JOptionPane.showMessageDialog(null, "Produkt wydany!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Błąd wydawania produktu!");
                ex.printStackTrace();
            }
        });

        panel.add(nazwaLabel);
        panel.add(nazwaField);
        panel.add(iloscLabel);
        panel.add(iloscField);
        panel.add(wydajButton);

        wydajFrame.setVisible(true);
    }

    // Formularz do usuwania produktów
    private void pokazFormularzUsuwania() {
        JFrame usunFrame = new JFrame("Usuń Produkt");
        usunFrame.setSize(300, 150);
        usunFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        usunFrame.add(panel);
        panel.setLayout(new GridLayout(2, 2));

        JLabel nazwaLabel = new JLabel("Nazwa:");
        JTextField nazwaField = new JTextField(20);

        JButton usunButton = new JButton("Usuń");
        usunButton.addActionListener(e -> {
            String nazwa = nazwaField.getText();

            try {
                magazyn.usunProdukt(nazwa);
                JOptionPane.showMessageDialog(null, "Produkt usunięty!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Błąd usuwania produktu!");
                ex.printStackTrace();
            }
        });

        panel.add(nazwaLabel);
        panel.add(nazwaField);
        panel.add(usunButton);

        usunFrame.setVisible(true);
    }

    // Generowanie raportu
    private void wygenerujRaport() {
        try {
            magazyn.generujRaport();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd generowania raportu!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MagazynGUI::new);
    }
}
