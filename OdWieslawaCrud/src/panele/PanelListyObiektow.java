package panele;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import dane.Wykaz;
import modele.Obiekt;
import modelelist.ModelListyObiektow;
import java.awt.Color;
import java.awt.event.MouseAdapter;

@SuppressWarnings("serial")
public class PanelListyObiektow extends JScrollPane {

	public PanelListyObiektow() {
		super();
		ustaw(null);
	}
	
	public PanelListyObiektow(MouseAdapter mouseAdapter) {
		super();
		ustaw(mouseAdapter);
	}
	
	private void ustaw(MouseAdapter mouseAdapter) {
		setBorder(null);
		JList<Obiekt> list = new JList<Obiekt>();
		list.addMouseListener(mouseAdapter);
		Wykaz.podglad = new ModelListyObiektow(list);
		list.setBackground(Color.GRAY);
		list.setBorder(new EmptyBorder(5, 5, 5, 5));
		setViewportView(list);
	}
}
