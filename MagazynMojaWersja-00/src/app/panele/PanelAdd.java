package app.panele;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import app.okna.OknoGlowne;

public class PanelAdd extends JPanel {

	private static final long serialVersionUID = 1L;
	private OknoGlowne oGlowne;

	public PanelAdd() {
		
		 @Override
         public void actionPerformed(ActionEvent e) {
             String name = oGlowne.getName();
             int ilosc = Integer.parseInt(iloscField.getText());
             String lokalizacja = lokalizacjaField.getText();
             String opis = opisField.getText();
             dodajProdukt(nazwa, ilosc, lokalizacja, opis);
             nazwaField.setText("");
             iloscField.setText("");
             lokalizacjaField.setText("");
             opisField.setText("");
         }
	}

	
	
}
