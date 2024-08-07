package app.okna;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.magazyn.MagazynApp;
import app.produkt.Produkt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OknoDodania extends JPanel{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private MagazynApp magApp;

	
	public OknoDodania() {
		this.magApp = magApp;
		
		
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
		
		setBounds(100, 100, 450, 300);
		getRootPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		getRootPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getRootPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton eDodaj = new JButton("Dodaj");
				eDodaj.addActionListener(new ActionListener() {
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
				eDodaj.setActionCommand("OK");
				buttonPane.add(eDodaj);
				getRootPane().setDefaultButton(eDodaj);
			}
			{
				JButton cancelButton = new JButton("Anluluj");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						wyjdz();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	 private void dodajProdukt(String nazwa, int ilosc, String lokalizacja, String opis) {
	        for (Produkt produkt : magApp.produkty) {
	            if (produkt.getNazwa().equalsIgnoreCase(nazwa)) {
	                produkt.setIlosc(produkt.getIlosc() + ilosc);
	                magApp.updateTable();
	                return;
	            }
	        }
	        magApp.produkty.add(new Produkt(nazwa, ilosc, lokalizacja, opis));
	        magApp.updateTable();
	    }
	
	public void wyjdz() {
		magApp.dispose();
	}

}
