package modeleList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import rendererWez.RendererWeza;
import weze.Waz;

@SuppressWarnings("serial")
public class ModelListWezy extends DefaultListModel<Waz> {
	
	private JList<Waz> lista;
	
	public ModelListWezy() {
		super();
		ustaw(new JList<Waz>());
	}
	
	public ModelListWezy(JList<Waz> lista) {
		super();
		ustaw(lista);
	}

	private void ustaw(JList<Waz> lista) {
		lista.setModel(this);
		// renderer
		lista.setCellRenderer(new RendererWeza());
		this.lista = lista;
	}
	
	private void dodajWeza(Waz waz) {
		add(0, waz);
		odswiez();
	}
	
	public void addWaz(String nazwa, int dlugosc) {
		dodajWeza(new Waz(nazwa, dlugosc));
	}
	
	public void addWaz(Waz waz) {
		dodajWeza(waz);
	}

	public void odswiez() {
		lista.updateUI();
	}
	
	public int getZaznaczonyIndex() {
		return lista.getSelectedIndex();
	}
	
	private void usun(int indeks) {
		lista.remove(indeks);
		odswiez();
	}
}
