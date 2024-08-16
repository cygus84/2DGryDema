package app.poupy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.dane.Magazyn;
import app.modele.Produkt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

public class PoupDodanieProduktu extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNazwa;
	private JTextField tfLokliazacja;
	private JTextField tfIlosc;
	private JTextField tfOpis;

	public PoupDodanieProduktu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PoupDodanieProduktu.class.getResource("/app/obrazki/logo.jpg")));
		setTitle("DodanieProduktu");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 2, 5, 5));
		{
			JLabel lnazwa = new JLabel("Nazwa");
			lnazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lnazwa);
		}
		{
			tfNazwa = new JTextField();
			tfNazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(tfNazwa);
			tfNazwa.setColumns(10);
		}
		{
			JLabel lLokazlacja = new JLabel("Lokalizajca");
			lLokazlacja.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lLokazlacja);
		}
		{
			tfLokliazacja = new JTextField();
			tfLokliazacja.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(tfLokliazacja);
			tfLokliazacja.setColumns(10);
		}
		{
			JLabel lIlosc = new JLabel("Ilosc");
			lIlosc.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lIlosc);
		}
		{
			tfIlosc = new JTextField();
			tfIlosc.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(tfIlosc);
			tfIlosc.setColumns(10);
		}
		{
			JLabel lOpis = new JLabel("Opis");
			lOpis.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lOpis);
		}
		{
			tfOpis = new JTextField();
			tfOpis.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(tfOpis);
			tfOpis.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton eZamnknij = new JButton("Zamknij");
				eZamnknij.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zamknij();
					}
				});
				eZamnknij.setActionCommand("OK");
				buttonPane.add(eZamnknij);
				getRootPane().setDefaultButton(eZamnknij);
			}
			{
				JButton bDodaj = new JButton("Dodaj");
				bDodaj.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dodajProdukt();
					}
				});
				bDodaj.setActionCommand("Cancel");
				buttonPane.add(bDodaj);
			}
		}
		
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void dodajProdukt() {
		Magazyn.glowny.addElement(new Produkt(tfNazwa.getText(), tfLokliazacja.getText(), Integer.valueOf(tfIlosc.getText()), tfOpis.getText()));
		zamknij();
	}
	private void zamknij() {
		dispose();
	}
}
