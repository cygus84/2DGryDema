package app.manger;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import app.modele.Produkt;
import app.rederery.RenderProduktu;

@SuppressWarnings("serial")
public class MenagerProduktow extends DefaultListModel<Produkt> {
	
	private JList<Produkt> lista;
	
	public MenagerProduktow() {
		super();
		ustaw(new JList<Produkt>());
	}
	
	public MenagerProduktow(JList<Produkt> lista) {
		super();
		ustaw(lista);
	}

	private void ustaw(JList<Produkt> lista) {
		// renderer
		lista.setModel(this);
		lista.setCellRenderer(new RenderProduktu());
		this.lista = lista;
	}

	public void aktulizacja() {
		lista.updateUI();
	}
}
