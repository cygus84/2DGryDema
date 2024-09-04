package renderery;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import model.Zwierze;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class RendererZwierzecia extends JPanel implements ListCellRenderer<Zwierze>{
	
	private static final long serialVersionUID = 1L;
	private JLabel eNazwa;
	private JLabel eWiek;
	private JLabel eRodzaj;
	
	public RendererZwierzecia() {
		setBorder(new EmptyBorder(5, 0, 5, 0));
		setLayout(new BorderLayout(0, 0));
		
		eNazwa = new JLabel("Nazwa");
		eNazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eNazwa.setHorizontalAlignment(SwingConstants.LEFT);
		add(eNazwa, BorderLayout.WEST);
		
		eWiek = new JLabel("XXX");
		eWiek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eWiek.setHorizontalAlignment(SwingConstants.RIGHT);
		add(eWiek, BorderLayout.EAST);
		
		eRodzaj = new JLabel("XXX");
		eRodzaj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eRodzaj.setHorizontalAlignment(SwingConstants.CENTER);
		add(eRodzaj, BorderLayout.CENTER);
		
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Zwierze> list, Zwierze zwierze, int index,
			boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
			setBackground(Color.BLUE);
		} else {
			setBackground(Color.DARK_GRAY);
		}
		eNazwa.setText(zwierze.getNazwa());
		eWiek.setText(String.valueOf(zwierze.getWiek()));
		eRodzaj.setText(String.valueOf(zwierze.getRodzaj()));
		return this;
	}

}
