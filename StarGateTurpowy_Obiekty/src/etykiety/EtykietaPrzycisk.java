package etykiety;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class EtykietaPrzycisk extends JLabel {
	
	public EtykietaPrzycisk() {
		super();
		ustwa("akacja","nic", null);
	}
	
	public EtykietaPrzycisk(String akcja, String rodzaj, MouseAdapter moseAdapter) {
		super();
		ustwa(akcja, rodzaj, moseAdapter);
	}
	
	private void ustwa(String akcja, String rodzaj, MouseAdapter moseAdapter) {
		putClientProperty("rodzaj", rodzaj);
		addMouseListener(moseAdapter);
		setBorder(new EmptyBorder(5, 10, 5, 10));
		setOpaque(true);
		setBackground(new Color(255, 128, 0));
		setForeground(new Color(0, 0, 0));
		setText(akcja);
	}
	
}
