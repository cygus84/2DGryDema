package renderery;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import modele.Obiekt;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RendererObiektu extends JPanel implements ListCellRenderer<Obiekt> {

	private static final long serialVersionUID = 1L;
	private JLabel eIlosc;
	private JLabel eNazwa;

	public RendererObiektu() {
		setBorder(new EmptyBorder(2, 5, 2, 5));
		setLayout(new BorderLayout(5, 0));
		
		eIlosc = new JLabel("XXX");
		eIlosc.setHorizontalAlignment(SwingConstants.RIGHT);
		eIlosc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(eIlosc, BorderLayout.EAST);
		
		eNazwa = new JLabel("Nazwa");
		eNazwa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(eNazwa, BorderLayout.CENTER);

	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Obiekt> list, Obiekt obiekt, int index,
			boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
			setBackground(Color.YELLOW);
		} else {
			setBackground(Color.GRAY);
		}
		eNazwa.setText(obiekt.getNazwa());
		eIlosc.setText(String.valueOf(obiekt.getIlosc()));
		return this;
	}

}
