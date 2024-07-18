package app.okna;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class OknoGraWedkowanie extends JFrame implements Runnable{

	private static final String[][] RYBY = { { "Karp", "Leszcz", "Pstrąg" }, { "Szczupak", "Sandacz", "Sum" },
			{ "Łosoś", "Troć", "Węgorz" } };
	private static final int[][] PUNKTY_RYB = { { 10, 5, 8 }, { 15, 20, 25 }, { 30, 35, 40 } };
	private static final String[] AKWENY = { "Jezioro", "Rzeka", "Morze" };
	private static final String[] WEDKI = { "Zwykła wędka", "Wędka sportowa", "Profesjonalna wędka" };
	private static final int[] SKUTECZNOSC_WEDKI = { 50, 70, 90 }; // Szanse na złowienie ryby w procentach
	private static final int[] CENA_WEDKI = { 0, 50, 100 }; // Koszt wędki w punktach

	private final ArrayList<String> siatka = new ArrayList<>();
	private final Random random = new Random();
	private int punkty = 0;

	private JComboBox<String> wedkiComboBox;
	private JComboBox<String> akwensComboBox;
	private JTextArea outputArea;
	private JButton lowRybyButton;
	private JButton sprawdzSiatkeButton;
	private JButton zakonczenieGryButton;
	private JLabel punktyLabel;
	private Timer timer;
	private boolean[] rybyDostepne = { false, false, false };

	public OknoGraWedkowanie() {
		setTitle("Gra w Wędkowanie");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		
		initComponents();
		startFishTimer();
	}

	private void startFishTimer() {
		 timer = new Timer(3000, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                for (int i = 0; i < rybyDostepne.length; i++) {
	                    rybyDostepne[i] = random.nextBoolean();
	                }
	                outputArea.append("Aktualizacja akwenu:\n");
	                for (int i = 0; i < AKWENY.length; i++) {
	                    outputArea.append(AKWENY[i] + ": " + (rybyDostepne[i] ? "Ryby dostępne\n" : "Brak ryb\n"));
	                }
	            }
	        });
	        timer.start();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		// Panel na górze z wyborem wędki i akwenu oraz punktami
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Wybierz wędkę:"));
		wedkiComboBox = new JComboBox<>(WEDKI);
		topPanel.add(wedkiComboBox);

		topPanel.add(new JLabel("Wybierz akwen:"));
		akwensComboBox = new JComboBox<>(AKWENY);
		topPanel.add(akwensComboBox);

		punktyLabel = new JLabel("Punkty: " + punkty);
		topPanel.add(punktyLabel);

		add(topPanel, BorderLayout.NORTH);

		// Pole tekstowe na wyjście
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		add(new JScrollPane(outputArea), BorderLayout.CENTER);

		// Panel na dole z przyciskami
		JPanel bottomPanel = new JPanel();
		lowRybyButton = new JButton("Łów ryby");
		lowRybyButton.addActionListener(new LowRybyActionListener());
		bottomPanel.add(lowRybyButton);

		sprawdzSiatkeButton = new JButton("Sprawdź siatkę");
		sprawdzSiatkeButton.addActionListener(new SprawdzSiatkeActionListener());
		bottomPanel.add(sprawdzSiatkeButton);

		zakonczenieGryButton = new JButton("Zakończ grę");
		zakonczenieGryButton.addActionListener(new ZakonczenieGryActionListener());
		bottomPanel.add(zakonczenieGryButton);

		add(bottomPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		
		
		
		int wyborWedki = wedkiComboBox.getSelectedIndex();
		int wyborAkwenu = akwensComboBox.getSelectedIndex();
		if (!rybyDostepne[wyborAkwenu]) {
			outputArea.append("Brak ryb w wybranym akwenie.\n");
			return;
		}
		int skutecznosc = SKUTECZNOSC_WEDKI[wyborWedki];
		int szansa = random.nextInt(100);

		if (szansa < skutecznosc) {
			int rybaIndex = random.nextInt(RYBY[wyborAkwenu].length);
			String zlowionaRyba = RYBY[wyborAkwenu][rybaIndex];
			int zdobytePunkty = PUNKTY_RYB[wyborAkwenu][rybaIndex];
			siatka.add(zlowionaRyba);
			punkty += zdobytePunkty;
			punktyLabel.setText("Punkty: " + punkty);
			outputArea.append("Złowiłeś: " + zlowionaRyba + " przy użyciu " + WEDKI[wyborWedki] + " w "
					+ AKWENY[wyborAkwenu] + ". Zdobyte punkty: " + zdobytePunkty + "\n");
		} else {
			outputArea.append("Nie udało się złowić ryby.\n");
		}

		outputArea.append("Twoja siatka zawiera:\n");
		if (siatka.isEmpty()) {
			outputArea.append("Brak złowionych ryb.\n");
		} else {
			for (String ryba : siatka) {
				outputArea.append(ryba + "\n");
			}
		}
		
		
	

		outputArea.append("Koniec gry. Dziękujemy za grę!\n");
		lowRybyButton.setEnabled(false);
		sprawdzSiatkeButton.setEnabled(false);
		zakonczenieGryButton.setEnabled(false);
		timer.stop();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
}


