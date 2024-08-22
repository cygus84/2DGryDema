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
	private JTextField tfdlugosc;
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
			JLabel ePodgaldObiektu = new JLabel("Podglad weza");
			ePodgaldObiektu.setForeground(Color.blue);
			ePodgaldObiektu.setHorizontalAlignment(SwingConstants.CENTER);
			ePodgaldObiektu.setFont(new Font("Tahoma", Font.BOLD, 22));
			contentPanel.add(ePodgaldObiektu, BorderLayout.SOUTH);
		}	
		{
			JPanel bPanel = new JPanel();
			bPanel.setOpaque(false);
			FlowLayout flowLayout = (FlowLayout) bPanel.getLayout();
			flowLayout.setVgap(2);
			flowLayout.setHgap(25);
			bPanel.setBorder(null);
			getContentPane().add(bPanel, BorderLayout.SOUTH);
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
				bZamknij.setFont(new Font("Tahoma", Font.PLAIN, 16));
				bPanel.add(bZamknij);
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
			
			JLabel edlugosc = new JLabel("Dlugosc");
			edlugosc.setForeground(Color.ORANGE);
			edlugosc.setFont(new Font("Tahoma", Font.PLAIN, 16));
			edlugosc.setBounds(10, 142, 60, 30);
			panelFormularza.add(edlugosc);
			
			tfdlugosc = new JTextField();
			tfdlugosc.setText(
					(podgladWezaId >= 0) ? String.valueOf(Wykaz.podglad.get(podgladWezaId).getClass() : "1"
			);
			tfdlugosc.setFont(new Font("Tahoma", Font.PLAIN, 16));
			tfdlugosc.add(tfdlugosc);
			tfdlugosc.setColumns(10);
		 }
		
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
		}
			
			private void zapiszZmiany() {
				Wykaz.aktulizacjaWezy(podgladWezaId, tfNazwa.getText(), Integer.valueOf(tfdlugosc.getText()));
			}

		private void zamknij() {
			dispose();
		}
}
