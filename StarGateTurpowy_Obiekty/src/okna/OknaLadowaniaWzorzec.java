package okna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class OknaLadowaniaWzorzec extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel eKomunikat;
	private JLabel eObrazek;
	protected boolean isLadowanieDanych; 
	protected String komunikat;

	public OknaLadowaniaWzorzec() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		eObrazek = new JLabel("");
		eObrazek.setIcon(new ImageIcon(OknaLadowaniaWzorzec.class.getResource("/obrazki/logo/logo.png")));
		eObrazek.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(eObrazek, BorderLayout.CENTER);
		
		eKomunikat = new JLabel();
		komunikat = "Ladowanie danych";
		eKomunikat.setText(komunikat);
		eKomunikat.setForeground(new Color(255, 255, 0));
		eKomunikat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(eKomunikat, BorderLayout.SOUTH);
		startWatkuLadujacegoDane();
		glownaPentla();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	protected void ustawGlowneTlo(Color kolor) {
		setBackground(kolor);
	}
	
	protected void ustawEtykieteLogo(JLabel nowyObrazek) {
		eObrazek = nowyObrazek;
	}
	
	protected void ustawEtykieteKomunikatu(JLabel nowyKmuniat) {
		eKomunikat = nowyKmuniat;
	}

	protected void startWatkuLadujacegoDane() {
		
	}
	
	protected void aktulizacjaDanychGlonejPentli() {
		
	}
	
	protected void akcjaPoZaladowaniuDanych() {
	
	}
	
	protected void zamkniecieOkna() {
		dispose();
	}
	
	protected void updateKomunikat() {
		eKomunikat.setText(komunikat);
	}
	
	private void glownaPentla() {
		isLadowanieDanych = true;
		new Thread() {
			public void run() {
				String kropki = "...";
				while(isLadowanieDanych) {
					kropki += ".";
					if(kropki.length() > 3) {
						kropki = ".";
					}
					eKomunikat.setText(komunikat + kropki);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					aktulizacjaDanychGlonejPentli();
				}
				akcjaPoZaladowaniuDanych();
				zamkniecieOkna();
			}
		}.start();
		
	}
}
