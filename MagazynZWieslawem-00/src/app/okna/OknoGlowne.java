package app.okna;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.dane.Magazyn;
import app.modele.Produkt;
import app.panele.PanelProduktow;
import app.poupy.PoupDodanieProduktu;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OknoGlowne extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelProduktow produkty;

	public OknoGlowne() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OknoGlowne.class.getResource("/app/obrazki/logo.jpg")));
		setTitle("Magazyn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1127, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JLabel eObrazek = new JLabel("");
		eObrazek.setOpaque(true);
		eObrazek.setBackground(Color.DARK_GRAY);
		eObrazek.setHorizontalAlignment(SwingConstants.CENTER);
		eObrazek.setIcon(new ImageIcon(OknoGlowne.class.getResource("/app/obrazki/logo.jpg")));
		contentPane.add(eObrazek, BorderLayout.NORTH);
		
		produkty = new PanelProduktow();
		contentPane.add(produkty, BorderLayout.CENTER);
		
		JPanel panelAkcji = new JPanel();
		contentPane.add(panelAkcji, BorderLayout.SOUTH);
		
		JButton bSzukaj = new JButton("Wyszukaj");
		bSzukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wyszukaj();
			}
		});
		bSzukaj.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAkcji.add(bSzukaj);
		
		JButton bDodaj = new JButton("Dodaj");
		bDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajProdukt();
			}
		});
		bDodaj.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAkcji.add(bDodaj);
		
		JButton bWyczysc = new JButton("Wyczysc");
		bWyczysc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wyczysc();
			}
		});
		bWyczysc.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAkcji.add(bWyczysc);
		setLocationRelativeTo(null);
		incjowanieDanych();
		setVisible(true);
	}
	
	private void dodajProdukt() {
		new PoupDodanieProduktu();
	}

	private void wyszukaj() {
		System.out.println("Wyszkanie rozpoczeto");
		Magazyn.pomocniczy.clear();
		int ilosc = Magazyn.glowny.size();
		for(int p = 0; p < ilosc; p++) {
			Magazyn.pomocniczy.addElement(Magazyn.glowny.get(p));
		}
		Magazyn.pomocniczy.aktulizacja();
		System.out.println("Wyszkanie zakonczono" + Magazyn.pomocniczy.size());
	}
	
	private void incjowanieDanych() {
		Magazyn.pomocniczy.addElement(new Produkt());
		Magazyn.pomocniczy.aktulizacja();
	}
	
	private void wyczysc() {
		
	}
}
