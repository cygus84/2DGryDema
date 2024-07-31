package okna;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class OknoLadowania extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public OknoLadowania() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JLabel eObrazek = new JLabel("");
		eObrazek.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(eObrazek, BorderLayout.CENTER);
		
		JLabel eKomunikat = new JLabel("Komunikat:");
		eKomunikat.setForeground(new Color(255, 255, 0));
		eKomunikat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(eKomunikat, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
