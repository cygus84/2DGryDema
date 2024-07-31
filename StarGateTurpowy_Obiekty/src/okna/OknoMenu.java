package okna;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OknoMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public OknoMenu() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 456);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 0, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Gwiezdne Wrota Turowe");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 128, 64));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		
		JButton bNowaGra = new JButton("Nowa gra");
		bNowaGra.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(bNowaGra);
		
		JButton bWczytajGre = new JButton("Wczytaj gre");
		bWczytajGre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(bWczytajGre);
		
		JButton bWyjdz = new JButton("Wyjdz");
		bWyjdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wyjscieZGry();
			}
		});
		bWyjdz.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(bWyjdz);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void wyjscieZGry() {
		System.out.println("Wyjscie z gry");
		System.exit(0);
	}

}
