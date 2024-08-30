package okna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import dane.Wykaz;
import panele.PanelListyWezy;
import poup.PopupPodgladuWeza;
import weze.Waz;

public class OknoGlowne extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSzukanaNazwa;
	private JTextField tfSzukanaDlugosc;
	private JComboBox<String> cbSzukanaDlugosc;
	private JTextField tfNazwa;
	private JTextField tfDlugosc;
	
	public OknoGlowne() {
		setBackground(Color.DARK_GRAY);
		setTitle("Aplikacja Moje Weze- 002");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 459);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel panelGorny = new JPanel();
		panelGorny.setBackground(Color.GRAY);
		contentPane.add(panelGorny, BorderLayout.NORTH);
		panelGorny.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Nazwa zawiera");
		panelGorny.add(lblNewLabel);
		lblNewLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfSzukanaNazwa = new JTextField();
		panelGorny.add(tfSzukanaNazwa);
		tfSzukanaNazwa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfSzukanaNazwa.setColumns(20);
		
		JButton bSzukajPoNazwie = new JButton("Szukaj");
		bSzukajPoNazwie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wyszukajPoNazwie();
			}
		});
		panelGorny.add(bSzukajPoNazwie);
		bSzukajPoNazwie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblIlo = new JLabel("Dlugosc");
		panelGorny.add(lblIlo);
		lblIlo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIlo.setBorder(new EmptyBorder(0, 25, 0, 5));
		
		cbSzukanaDlugosc = new JComboBox<String>();
		panelGorny.add(cbSzukanaDlugosc);
		cbSzukanaDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbSzukanaDlugosc.setModel(new DefaultComboBoxModel<String>(new String[] {"mniejsza niż", "równa", "większa niż"}));
		
		tfSzukanaDlugosc = new JTextField();
		panelGorny.add(tfSzukanaDlugosc);
		tfSzukanaDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfSzukanaDlugosc.setColumns(10);
		
		JButton bSzukajPoIlosci = new JButton("Szukaj");
		bSzukajPoIlosci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wyszukajPoDlugosci();
			}
		});
		panelGorny.add(bSzukajPoIlosci);
		bSzukajPoIlosci.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNazwa.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel.add(lblNazwa);
		
		tfNazwa = new JTextField();
		tfNazwa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNazwa.setColumns(40);
		panel.add(tfNazwa);
		
		JLabel lblIlo_1 = new JLabel("dlugosc");
		lblIlo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIlo_1.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel.add(lblIlo_1);
		
		tfDlugosc = new JTextField();
		tfDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDlugosc.setColumns(5);
		panel.add(tfDlugosc);
		
		JButton bDodaj = new JButton("Dodaj");
		bDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajNowegoWeza();
			}
		});
		bDodaj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(bDodaj);
		
		JButton bWyczysc = new JButton("Wyczyść");
		bWyczysc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wyczyscWyszukiwanie();
			}
		});
		bWyczysc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(bWyczysc);
		
		contentPane.add(new PanelListyWezy(listaMouseAdapter()), BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	protected void wyszukajPoDlugosci() {
		int wybor = cbSzukanaDlugosc.getSelectedIndex();
		String tekstIlosc = tfSzukanaDlugosc.getText();
		if (wybor >= 0 && tekstIlosc.matches("^[0-9-]{1,}$")) {
			int ile = Integer.valueOf(tekstIlosc);
			Wykaz.wyszukajPoDlugosci(wybor, ile);
			tfSzukanaDlugosc.setText("");
		}
	}
	
	
	protected void wyszukajPoNazwie() {
		String fragmentNazwy = tfSzukanaNazwa.getText();
		if (fragmentNazwy.length() >= 1) {
			Wykaz.wyszukajPoFragmecieNazwy(fragmentNazwy);
			tfSzukanaNazwa.setText("");
		}
	}
	
	
	private void wyczyscWyszukiwanie() {
		Wykaz.wyczyscPodglad();
	}
	
	private void dodajNowegoWeza() {
		String nazwa = tfNazwa.getText();
		String tekstDlugosc = tfDlugosc.getText();
		if (nazwa.length() >= 1 && tekstDlugosc.matches("^[0-9-]{1,}$")) {
			int dlugosc = Integer.valueOf(tekstDlugosc);
			Wykaz.glowny.add(0, new Waz(nazwa, dlugosc));
			wyczyscWyszukiwanie();
			tfNazwa.setText("");
			tfDlugosc.setText("");
		}
	}
	
	private MouseAdapter listaMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Wykaz.podglad.getZaznaczonyIndex() >= 0) {
					new PopupPodgladuWeza(Wykaz.podglad.getZaznaczonyIndex());
				}	
			}
		};
	}
}
