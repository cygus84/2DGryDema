package app.grafika;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import app.menager.KatalogMonet;
import app.modele.Moneta;

public class MainFrame extends JFrame {

	private KatalogMonet katalog;
	private JTextField krajField;
	private JTextField rokField;
	private JTextField nominalField;
	private JTextField materialField;
	private JTextField iloscField;
	private JTable table;
	private DefaultTableModel tableModel;

	public MainFrame() {
		katalog = new KatalogMonet();
		initUI();
	}

	private void initUI() {
		setTitle("Katalog Monet");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel krajLabel = new JLabel("Kraj:");
		krajField = new JTextField(20);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(krajLabel, gbc);
		gbc.gridx = 1;
		panel.add(krajField, gbc);

		JLabel rokLabel = new JLabel("Rok:");
		rokField = new JTextField(20);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(rokLabel, gbc);
		gbc.gridx = 1;
		panel.add(rokField, gbc);

		JLabel nominalLabel = new JLabel("Nominal:");
		nominalField = new JTextField(20);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(nominalLabel, gbc);
		gbc.gridx = 1;
		panel.add(nominalField, gbc);

		JLabel materialLabel = new JLabel("Materiał:");
		materialField = new JTextField(20);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(materialLabel, gbc);
		gbc.gridx = 1;
		panel.add(materialField, gbc);

		JLabel iloscLabel = new JLabel("Ilość:");
		iloscField = new JTextField(200);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(iloscLabel, gbc);
		gbc.gridx = 1;
		panel.add(iloscField, gbc);

		JButton dodajButton = new JButton("Dodaj monetę");
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(dodajButton, gbc);

		String[] columnNames = { "Kraj", "Rok", "Nominal", "Materiał", "Ilość" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(scrollPane, gbc);

		JButton zapiszButton = new JButton("Zapisz do pliku");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		panel.add(zapiszButton, gbc);

		JButton wczytajButton = new JButton("Wczytaj z pliku");
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel.add(wczytajButton, gbc);

		dodajButton.addActionListener(e -> {
			String kraj = krajField.getText();
			int rok = Integer.parseInt(rokField.getText());
			String nominal = nominalField.getText();
			String material = materialField.getText();
			int ilosc = Integer.parseInt(iloscField.getText());

			Moneta moneta = new Moneta(kraj, rok, nominal, material, ilosc);
			katalog.dodajMonete(moneta);
			tableModel.addRow(new Object[] { kraj, rok, nominal, material, ilosc });

			// Clear fields
			krajField.setText("");
			rokField.setText("");
			nominalField.setText("");
			materialField.setText("");
			iloscField.setText("");
		});

		zapiszButton.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Wybierz plik do zapisu");
			int userSelection = fileChooser.showSaveDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				katalog.zapiszDoPliku(file.getAbsolutePath());
			}
		});

		wczytajButton.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Wybierz plik do wczytania");
			int userSelection = fileChooser.showOpenDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				katalog.wczytajZPliku(file.getAbsolutePath());
				tableModel.setRowCount(0);
				for (Moneta moneta : katalog.getMonety()) {
					tableModel.addRow(new Object[] { moneta.getKraj(), moneta.getRok(), moneta.getNominal(),
							moneta.getMaterial(), moneta.getIlosc() });
				}
			}
		});
	}
}
