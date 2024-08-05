package panele;

import java.awt.event.MouseAdapter;

import javax.swing.JPanel;
import etykiety.EtykietaPrzycisk;

public class PanelDolny extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelDolny() {
		ustaw(null);
	}
	
	public PanelDolny(MouseAdapter myszka) {	
		ustaw(myszka);
	}

	private void ustaw(MouseAdapter myszka) {
		setOpaque(false);
		add(new EtykietaPrzycisk("Zapisz", "Aplikacja", myszka));
		
	}

}
