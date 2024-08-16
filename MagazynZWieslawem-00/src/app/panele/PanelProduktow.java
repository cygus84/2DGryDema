package app.panele;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import app.dane.Magazyn;
import app.manger.MenagerProduktow;
import app.modele.Produkt;

@SuppressWarnings("serial")
public class PanelProduktow extends JScrollPane {

	public PanelProduktow() {
		super();
		setBorder(null);
		
		JList<Produkt> list = new JList<Produkt>();
		Magazyn.pomocniczy = new MenagerProduktow(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setViewportView(list);
	}
}
