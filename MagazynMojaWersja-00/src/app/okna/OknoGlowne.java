package app.okna;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.produkt.Produkt;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class OknoGlowne<protecteted> extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel panelPrzyciskow = new JPanel();
	protected JList list = new JList();

	
	public OknoGlowne() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(OknoGlowne.class.getResource("/app/img/mss_building_services_limited_logo.jpg")));
		setTitle("MSS-Walkinstown");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel logo = new JLabel("");
		logo.setOpaque(true);
		logo.setBackground(new Color(0, 0, 0));
		logo.setIcon(new ImageIcon(OknoGlowne.class.getResource("/app/img/mss_building_services_limited_logo.jpg")));
		logo.setFont(new Font("Tahoma", Font.BOLD, 16));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(logo, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		scrollPane.setViewportView(list);
		contentPane.add(panelPrzyciskow, BorderLayout.SOUTH);
		
		JButton bSearch = new JButton("Search");
		bSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelPrzyciskow.add(bSearch);
		
		JButton bAdd = new JButton("Add product");
		bAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelPrzyciskow.add(bAdd);
		
		JButton bDelete = new JButton("Delete");
		bDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelPrzyciskow.add(bDelete);
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
