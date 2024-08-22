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
