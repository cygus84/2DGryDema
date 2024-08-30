package poup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dane.Wykaz;

public class PopupPodgladuWeza extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNazwa;
	private JTextField tfDlugosc;
	private int podgladWezaId;
	
	public PopupPodgladuWeza() {
		ustaw(-1);		
	}
	
	public PopupPodgladuWeza(int podgladWezaId) {	
		ustaw(podgladWezaId);		
	}

	private void ustaw(int podgladWezaId) {
		this.podgladWezaId = podgladWezaId;
		getContentPane().setBackground(Color.GREEN);
		setTitle("Podglad Weza");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setOpaque(false);
		contentPanel.setLayout(new BorderLayout(5,5));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblPodglad = new JLabel("Podglad weza");
			lblPodglad.setForeground(Color.BLUE);
			lblPodglad.setHorizontalAlignment(SwingConstants.CENTER);
			lblPodglad.setFont(new Font("Tahoma", Font.BOLD, 26));
			contentPanel.add(lblPodglad, BorderLayout.NORTH);
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
				
			}
			{
				JButton bUsun = new JButton("Usun");
				bUsun.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						usunWeza();
					}
				});
				bUsun.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel.add(bUsun);
			}
			JButton bZamknij = new JButton("Zamknij");
			bZamknij.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					zamknij();
				}
			});
			bZamknij.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panel.add(bZamknij);
		}
		{
			JPanel panelFormularza = new JPanel();
			panelFormularza.setOpaque(false);
			panelFormularza.setForeground(Color.BLUE);
			panelFormularza.setBorder(null);
			contentPanel.add(panelFormularza, BorderLayout.CENTER);
			panelFormularza.setLayout(null);
			
			JLabel eNazwa = new JLabel("Nazwa");
			eNazwa.setForeground(Color.BLUE);
			eNazwa.setFont(new Font("Tahoma", Font.PLAIN, 14));
			eNazwa.setBounds(10, 11, 60, 30);
			panelFormularza.add(eNazwa);
			
			JLabel eDlugosc = new JLabel("Dlugosc");
			eDlugosc.setForeground(Color.BLUE);
			eDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 14));
			eDlugosc.setBounds(10, 142, 60, 30);
			panelFormularza.add(eDlugosc);
			
			tfNazwa = new JTextField();
			tfNazwa.setText(
					(podgladWezaId >= 0) ? Wykaz.podglad.get(podgladWezaId).getNazwa() : "Nazwa"
					);
			tfNazwa.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tfNazwa.setBounds(80, 11, 334, 121);
			panelFormularza.add(tfNazwa);
			tfNazwa.setColumns(10);
			
			tfDlugosc = new JTextField();
			tfDlugosc.setText(
					(podgladWezaId >= 0) ?  String.valueOf(Wykaz.podglad.get(podgladWezaId).getDlugosc()) : "1"
					);
			tfDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tfDlugosc.setBounds(80, 142, 100, 30);
			panelFormularza.add(tfDlugosc);
			tfDlugosc.setColumns(10);
		}
		
		setResizable(false);
		setUndecorated(true);	
		
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
		}
			
			private void zapiszZmiany() {
				Wykaz.aktulizacjaWezy(podgladWezaId, tfNazwa.getText(), Integer.valueOf(tfDlugosc.getText()));
			}

		private void zamknij() {
			dispose();
		}
		
		private void usunWeza() {
			Wykaz.usunWeza(podgladWezaId);
			zamknij();
		}
}
