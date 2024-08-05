package panele;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.SwingConstants;

import etykiety.EtykietaPrzycisk;

import java.awt.Color;

public class PanelGorny extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelGorny() {
		ustaw(null);
	}
	
	public PanelGorny(MouseAdapter myszka) {
		ustaw(myszka);
	}
	
	private void ustaw(MouseAdapter myszka){
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("Star Gate Turowa");
		lblNewLabel.setForeground(new Color(255, 128, 64));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		add(lblNewLabel, BorderLayout.CENTER);
		add(new EtykietaPrzycisk("Nowa gra", "Aplikacja", myszka), BorderLayout.WEST);
		add(new EtykietaPrzycisk("Zakoncz gre", "Aplikacja", myszka), BorderLayout.EAST);	
	}

}
