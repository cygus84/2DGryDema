package popupy;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dane.Wykaz;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.management.StringValueExp;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PoupPodgladuZwierzecia extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int podgladZwierzeciaId;
	private JTextField tfNazwa;
	private JTextField tfGrupa;
	private JTextField tfWiek;
	
	public PoupPodgladuZwierzecia() {
		super();
		ustaw(-1);	
	}

	public PoupPodgladuZwierzecia(int podgladZwierzeciaId) {
		super();
		ustaw(podgladZwierzeciaId);	
	}
	
	private void ustaw(int podgladZwierzeciaId) {
		this.podgladZwierzeciaId = podgladZwierzeciaId;
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Podglad zwierzecia");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(5, 5));
		
		JPanel panelForumlarza = new JPanel();
		contentPanel.add(panelForumlarza, BorderLayout.CENTER);
		panelForumlarza.setLayout(null);
		
		JLabel eNazwa = new JLabel("Nazwa");
		eNazwa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		eNazwa.setBounds(10, 27, 62, 14);
		panelForumlarza.add(eNazwa);
		
		tfNazwa = new JTextField();
		tfNazwa.setText(
				(podgladZwierzeciaId >= 0) ?  Wykaz.podglad.get(podgladZwierzeciaId).getNazwa(): "Nazwa");
		tfNazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfNazwa.setBounds(71, 11, 330, 52);
		panelForumlarza.add(tfNazwa);
		tfNazwa.setColumns(10);
		
		JLabel lblGrupa = new JLabel("Grupa");
		lblGrupa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGrupa.setBounds(7, 82, 62, 24);
		panelForumlarza.add(lblGrupa);
		
		JLabel lblWiek = new JLabel("Wiek");
		lblWiek.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWiek.setBounds(7, 131, 62, 14);
		panelForumlarza.add(lblWiek);
		
		tfGrupa = new JTextField();
		tfGrupa.setText((podgladZwierzeciaId >= 0) ?  String.valueOf(Wykaz.podglad.get(podgladZwierzeciaId).getRodzaj()) : "1");
		tfGrupa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfGrupa.setColumns(10);
		tfGrupa.setBounds(71, 74, 330, 26);
		panelForumlarza.add(tfGrupa);
		
		tfWiek = new JTextField();
		tfWiek.setText((podgladZwierzeciaId >= 0) ?  String.valueOf(Wykaz.podglad.get(podgladZwierzeciaId).getWiek()) : "1");
		tfWiek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfWiek.setColumns(10);
		tfWiek.setBounds(71, 122, 330, 26);
		panelForumlarza.add(tfWiek);
		
		JPanel panelPrzyciskow = new JPanel();
		contentPanel.add(panelPrzyciskow, BorderLayout.SOUTH);
		
		JButton bZapisz = new JButton("Zapisz");
		bZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zapiszZmiany();
			}
		});
		bZapisz.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelPrzyciskow.add(bZapisz);
		
		JButton bUsun = new JButton("Usun");
		bUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usunZwierze();
			}
		});
		bUsun.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelPrzyciskow.add(bUsun);
		
		JButton bWyjdz = new JButton("Wyjdz");
		bWyjdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zamknij();
			}
		});
		bWyjdz.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelPrzyciskow.add(bWyjdz);
		
		setResizable(false);
		setUndecorated(true);	
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void zapiszZmiany() {
		Wykaz.aktulizacjaZwierzat(podgladZwierzeciaId, tfNazwa.getText(), Integer.valueOf(tfGrupa.getText()), Integer.valueOf(tfWiek.getText()));
	}
	
	private void zamknij() {
		dispose();
	}
	
	private void usunZwierze() {
		Wykaz.usunZwierze(podgladZwierzeciaId);
		zamknij();
	}
}
