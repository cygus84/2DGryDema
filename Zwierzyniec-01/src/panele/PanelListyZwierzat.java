package panele;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dane.Wykaz;
import model.Zwierze;
import modelList.ModelListZwierzat;

@SuppressWarnings("serial")
public class PanelListyZwierzat extends JScrollPane {
	
	public PanelListyZwierzat() {
		super();
		ustaw(null);
	}
	
	public PanelListyZwierzat(MouseAdapter mouseA) {
		super();
		ustaw(mouseA);
	}
	
	private void ustaw(MouseAdapter mouseA) {
		setBorder(null);
		JList<Zwierze> list = new JList<Zwierze>();
		list.addMouseListener(mouseA);
		Wykaz.podglad = new ModelListZwierzat(list);
		list.setBackground(Color.LIGHT_GRAY);
		list.setBorder(new EmptyBorder(5, 5, 5, 5));
		setViewportView(list);
	}
}
