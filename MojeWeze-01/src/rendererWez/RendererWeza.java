package rendererWez;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import weze.Waz;

public class RendererWeza extends JPanel implements ListCellRenderer<Waz> {

	private static final long serialVersionUID = 1L;
	private JLabel eDlugosc;
	private JLabel eNazwa;
	
	public RendererWeza() {
		setBorder(new EmptyBorder(5, 5 , 5, 5));
		setLayout(new BorderLayout(5, 0));
		
		eDlugosc = new JLabel("XXX");
		eDlugosc.setHorizontalAlignment(SwingConstants.RIGHT);
		eDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(eDlugosc, BorderLayout.EAST);
		
		eNazwa = new JLabel("Nazwa");
		eNazwa.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(eNazwa, BorderLayout.CENTER);
		
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Waz> list, Waz waz, int index, boolean isSelected,
			boolean cellHasFocus) {
		if (isSelected) {
			setBackground(Color.DARK_GRAY);
		} else {
			setBackground(Color.WHITE);
		}
		eDlugosc.setText(String.valueOf(waz.getDlugosc()));
		eNazwa.setText(waz.getNazwa());
		return this;
	}

}
