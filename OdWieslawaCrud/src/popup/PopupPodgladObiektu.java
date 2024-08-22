package popup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dane.Wykaz;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PopupPodgladObiektu extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNazwa;
	private JTextField tfIlosc;
	private int podgladObiektId;

	public PopupPodgladObiektu() {
		ustaw(-1);
	}
	
	public PopupPodgladObiektu(int podgladObiektId) {
		ustaw(podgladObiektId);
	}
	
	private void ustaw(int podgladObiektId) {
		this.podgladObiektId = podgladObiektId;
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("Podgląd obiektu");
		setBounds(100, 100, 507, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setOpaque(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(5, 5));
		{
			JLabel lblNewLabel = new JLabel("Podgląd obiektu");
			lblNewLabel.setForeground(Color.ORANGE);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(25);
			panel.setBorder(null);
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				JButton bZamknij = new JButton("Zamknij");
				bZamknij.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zamknij();
					}
				});
				{
					JButton bZapisz = new JButton("Zapisz");
					bZapisz.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							zapiszZmiany();
						}
					});
					bZapisz.setFont(new Font("Tahoma", Font.PLAIN, 16));
					panel.add(bZapisz);
				}
				bZamknij.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel.add(bZamknij);
			}
		}
		{
			JPanel panelFormularza = new JPanel();
			panelFormularza.setOpaque(false);
			panelFormularza.setBorder(null);
			contentPanel.add(panelFormularza, BorderLayout.CENTER);
			panelFormularza.setLayout(null);
			
			JLabel eNazwy = new JLabel("Nazwa");
			eNazwy.setForeground(Color.ORANGE);
			eNazwy.setFont(new Font("Tahoma", Font.PLAIN, 16));
			eNazwy.setBounds(10, 11, 60, 30);
			panelFormularza.add(eNazwy);
			
			JLabel eIlosc = new JLabel("Ilość");
			eIlosc.setForeground(Color.ORANGE);
			eIlosc.setFont(new Font("Tahoma", Font.PLAIN, 16));
			eIlosc.setBounds(10, 142, 60, 30);
			panelFormularza.add(eIlosc);
			
			tfNazwa = new JTextField();
			tfNazwa.setText(
				(podgladObiektId >= 0) ? Wykaz.podglad.get(podgladObiektId).getNazwa() : "Nazwa"
			);
			tfNazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			tfNazwa.setBounds(80, 11, 334, 121);
			panelFormularza.add(tfNazwa);
			tfNazwa.setColumns(10);
			
			tfIlosc = new JTextField();
			tfIlosc.setText(
				(podgladObiektId >= 0) ? String.valueOf(Wykaz.podglad.get(podgladObiektId).getIlosc()) : "1"
			);
			tfIlosc.setFont(new Font("Tahoma", Font.PLAIN, 16));
			tfIlosc.setBounds(80, 142, 100, 30);
			panelFormularza.add(tfIlosc);
			tfIlosc.setColumns(10);
		}
		
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void zapiszZmiany() {
		Wykaz.aktualizacjaObiektu(podgladObiektId, tfNazwa.getText(), Integer.valueOf(tfIlosc.getText()));
	}

	private void zamknij() {
		dispose();
	}
}
