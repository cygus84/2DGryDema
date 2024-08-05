package okna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import dane.Globalne;
import etykiety.EtykietaPrzycisk;
import narzedzia.Extraktor;
import panele.PanelDolny;
import panele.PanelGorny;
import panele.PanelMapy;

public class OknoGry extends JFrame {

	private static final long serialVersionUID = 1L;
	private PanelMapy panelMapy;
	
	public OknoGry() {
		addKeyListener(graKeyAdapter());
		addMouseListener(graMouseAdapter());
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(Globalne.rozmarEkranu);
		panelMapy = new PanelMapy();
		panelMapy.setBorder(null);
		panelMapy.setLayout(new BorderLayout(0, 0));
		panelMapy.add(new PanelGorny(graMouseAdapter()), BorderLayout.NORTH);
		panelMapy.add(new PanelDolny(graMouseAdapter()), BorderLayout.SOUTH);
		setContentPane(panelMapy);
		setVisible(true);
	}
	
	private void wyjscieZGry() {
		System.out.println("Wyjscie z gry");
		System.exit(0);
	}
	
	private void akcjeAplikacji(String akcja) {
		switch(akcja) {
			case "Nowa gra":
				break;
			case "Zakoncz gre":
				wyjscieZGry();
				break;
			case "Zapisz":
				break;
		}
	}
	
	private void obslugaAkcjiPrzycisku(MouseEvent e) {
		String rodzaj = (String) ((EtykietaPrzycisk) e.getSource()).getClientProperty("rodzaj");
		String akcja = (String) ((EtykietaPrzycisk) e.getSource()).getText();
		switch(rodzaj) {
			case "Aplikacja":
				akcjeAplikacji(akcja);
				break;
		}
	}

	private MouseAdapter graMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Extraktor.przycisku(e.getComponent().toString())) {
					obslugaAkcjiPrzycisku(e);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {	
				if(Extraktor.przycisku(e.getComponent().toString())) {
					//System.out.println("Jest etykieta!");
					((EtykietaPrzycisk) e.getSource()).setBackground(new Color(255, 128, 0)); 
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				//System.out.println("Zostalo najechane: " + e.getComponent().getName()); // do celow testowych
				if(Extraktor.przycisku(e.getComponent().toString())) {
					//System.out.println("Jest etykieta!");
					((EtykietaPrzycisk) e.getSource()).setBackground(Color.GRAY); 
				}
			}
		};		
	}
	
	private KeyAdapter graKeyAdapter() {
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ESCAPE) {
					wyjscieZGry();
				}
			}
		};
	}

}
