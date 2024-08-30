package panele;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import dane.Wykaz;
import modeleList.ModelListWezy;
import weze.Waz;

@SuppressWarnings("serial")
public class PanelListyWezy extends JScrollPane {

	public PanelListyWezy() {
		super();
		ustaw(null);
	}
	
	public PanelListyWezy(MouseAdapter mouseAdapter) {
		super();
		ustaw(mouseAdapter);
	}
	
	private void ustaw(MouseAdapter mouseAdapter) {
		setBorder(null);
		JList<Waz> list = new JList<Waz>();
		list.addMouseListener(mouseAdapter);
		Wykaz.podglad = new ModelListWezy(list);
		list.setBackground(Color.GRAY);
		list.setBorder(new EmptyBorder(5, 5, 5, 5));
		setViewportView(list);
	}
	
}
