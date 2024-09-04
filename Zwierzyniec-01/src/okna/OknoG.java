package okna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dane.Wykaz;
import model.Zwierze;
import panele.PanelListyZwierzat;
import popupy.PoupPodgladuZwierzecia;

public class OknoG extends  JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSzukanaNazwa;
	private JTextField tfNazwa;
	private JTextField tfGrupa;
	private JTextField tfWiek;
	private JComboBox<Integer> rodzaj;

	public OknoG() {
		setTitle("Shcronisko-01");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 913, 459);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel panelGorny = new JPanel();
		panelGorny.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelGorny, BorderLayout.NORTH);
		panelGorny.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tfSzukanaNazwa = new JTextField();
		panelGorny.add(tfSzukanaNazwa);
		tfSzukanaNazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfSzukanaNazwa.setColumns(20);
		
		JButton bSzukajPoNazwie = new JButton("Szukaj");
		bSzukajPoNazwie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wyszukajPoNazwie();
			}
		});
		panelGorny.add(bSzukajPoNazwie);
		bSzukajPoNazwie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
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
		
		JLabel lblIlo_1 = new JLabel("Grupa");
		lblIlo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIlo_1.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel.add(lblIlo_1);
		
		tfGrupa = new JTextField();
		tfGrupa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfGrupa.setColumns(5);
		panel.add(tfGrupa);
		
		JLabel lblIlo_2 = new JLabel("Wiek");
		lblIlo_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIlo_2.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel.add(lblIlo_2);
		
		tfWiek = new JTextField();
		tfWiek.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfWiek.setColumns(5);
		panel.add(tfWiek);
		
		JButton bDodaj = new JButton("Dodaj");
		bDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajNoweZwierze();
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
		
		contentPane.add(new PanelListyZwierzat(listaMouseAdapter()), BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void dodajNoweZwierze() {
		String nazwa = tfNazwa.getText();
		String grupa = tfGrupa.getText();
		String wiek = tfWiek.getText();
		if (nazwa.length() >= 1 && grupa.matches("^[0-9-]{1,}$") && wiek.matches("^[0-9-]{1,}$")) {
			int nGrupa = Integer.valueOf(grupa);
			int nWiek =  Integer.valueOf(wiek);
			Wykaz.glowny.add(0, new Zwierze(nazwa, nGrupa, nWiek));
			wyczyscWyszukiwanie();
			tfNazwa.setText("");
			tfGrupa.setText("");
			tfWiek.setText("");
		}
		
	}
	
	protected void wyszukajPoNazwie() {
		String fragmentNazwy = tfSzukanaNazwa.getText();
		if (fragmentNazwy.length() >= 1) {
			Wykaz.wyszukajPoFragmecieNazwy(fragmentNazwy);
			tfSzukanaNazwa.setText("");
		}
	}
	
	private MouseAdapter listaMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Wykaz.podglad.getZanaczoneZwierze() >= 0) {
					new PoupPodgladuZwierzecia(Wykaz.podglad.getZanaczoneZwierze());
				}	
			}
		};
	  }
	
	private void wyczyscWyszukiwanie() {
		Wykaz.wyczyscPodglad();
	}
}
