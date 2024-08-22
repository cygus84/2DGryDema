package modelelist;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import modele.Obiekt;
import renderery.RendererObiektu;

@SuppressWarnings("serial")
public class ModelListyObiektow extends DefaultListModel<Obiekt> {

	private JList<Obiekt> lista;
	
	public ModelListyObiektow() {
		super();
		ustaw(new JList<Obiekt>());
	}
	
	public ModelListyObiektow(JList<Obiekt> lista) {
		super();
		ustaw(lista);
	}
	
	private void ustaw(JList<Obiekt> lista) {
		lista.setModel(this);
		lista.setCellRenderer(new RendererObiektu());
		this.lista = lista;
	}
	
	private void dodajObiekt(Obiekt obiekt) {
		add(0, obiekt);
		odswiez();
	}
	
	public void addObiekt(String nazwa, int ilosc) {
		dodajObiekt(new Obiekt(nazwa, ilosc));
	}
	
	public void addObiekt(Obiekt obiekt) {
		dodajObiekt(obiekt);
	}
	
	public void odswiez() {
		lista.updateUI();
	}
	
	public int getSelectedIndeks() {
		return lista.getSelectedIndex();
	}
}
