package poupy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PopupSpotkaniPotwora extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	public PopupSpotkaniPotwora() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel ePotwora = new JLabel("");
			ePotwora.setIcon(new ImageIcon(PopupSpotkaniPotwora.class.getResource("/obrazki/postacieIcony/Icon25.png")));
			contentPanel.add(ePotwora);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton eAtak = new JButton("Atak");
				eAtak.setActionCommand("OK");
				buttonPane.add(eAtak);
				getRootPane().setDefaultButton(eAtak);
			}
			{
				JButton eObrona = new JButton("Obrona");
				buttonPane.add(eObrona);
			}
			{
				JButton eUcieczka = new JButton("Ucieczka");
				eUcieczka.setActionCommand("Cancel");
				buttonPane.add(eUcieczka);
			}
		}
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
