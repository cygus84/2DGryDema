package modelList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import model.Zwierze;
import renderery.RendererZwierzecia;

public class ModelListZwierzat extends DefaultListModel<Zwierze> {

	private JList<Zwierze> lista;
	
	public ModelListZwierzat() {
		super();
		ustaw(new JList<Zwierze>());
	}

	public ModelListZwierzat(JList<Zwierze> lista) {
		super();
		ustaw(lista);
	}
	
	private void ustaw(JList<Zwierze> lista) {
		lista.setModel(this);
		// renderer
		lista.setCellRenderer(new RendererZwierzecia());
		this.lista = lista;
	}
	
	public void dodajZwierze(Zwierze ziwerz) {
		add(0, ziwerz);
		odswierz();
	}

	public void addZwierze(String nazwa, int wiek, int rodzaj) {
		dodajZwierze(new Zwierze(nazwa, wiek, rodzaj));
	}
	
	public void addZwierze(Zwierze zwierze) {
		dodajZwierze(zwierze);
	}
	
	public void odswierz() {
		lista.updateUI();
	}
	
	public int getZanaczoneZwierze() {
		return lista.getSelectedIndex();
	}

}
