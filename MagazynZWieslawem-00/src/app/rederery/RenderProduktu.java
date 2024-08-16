package app.rederery;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.border.EmptyBorder;

import app.modele.Produkt;

import javax.swing.SwingConstants;

public class RenderProduktu extends JPanel  implements ListCellRenderer<Produkt>{

	private static final long serialVersionUID = 1L;
	private JLabel eLokalizajca;
	private JLabel eNazwa;
	private JLabel eIlosc;
	private JLabel eOpis;

	public RenderProduktu() {
		setBorder(new EmptyBorder(3, 0, 3, 0));
		setLayout(new BorderLayout(5, 5));
		
		eLokalizajca = new JLabel("Lokalizacja");
		eLokalizajca.setBorder(new EmptyBorder(0, 5, 0, 5));
		eLokalizajca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(eLokalizajca, BorderLayout.WEST);
		
		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new BorderLayout(0, 0));
		
		eNazwa = new JLabel("Nazwa");
		eNazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eNazwa.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel1.add(eNazwa, BorderLayout.WEST);
		
		eIlosc = new JLabel("XXX");
		eIlosc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		eIlosc.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel1.add(eIlosc, BorderLayout.EAST);
		
		eOpis = new JLabel("Opis");
		eOpis.setHorizontalAlignment(SwingConstants.LEFT);
		eOpis.setVerticalAlignment(SwingConstants.TOP);
		eOpis.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eOpis.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel1.add(eOpis, BorderLayout.CENTER);

	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Produkt> list, Produkt produkt, int index,
			boolean isSelected, boolean cellHasFocus) {
		if(isSelected) {
			setBackground(Color.YELLOW);
		} else {
			setBackground(Color.GRAY);
		}
		eLokalizajca.setText(produkt.getLokalizacja());
		eNazwa.setText(produkt.getNazwa());
		eOpis.setText("<html>" + produkt.getOpis() + "</html>");
		eIlosc.setText(String.valueOf(produkt.getIlosc()));
		return this;
	}

}
