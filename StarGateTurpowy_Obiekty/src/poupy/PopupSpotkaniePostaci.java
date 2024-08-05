package poupy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PopupSpotkaniePostaci extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	public PopupSpotkaniePostaci() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(128, 128, 0));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel eIcona = new JLabel("");
			eIcona.setVerticalAlignment(SwingConstants.BOTTOM);
			eIcona.setHorizontalAlignment(SwingConstants.CENTER);
			eIcona.setIcon(new ImageIcon(PopupSpotkaniePostaci.class.getResource("/obrazki/postacieIcony/Icon1.png")));
			contentPanel.add(eIcona);
		}
		{
			JLabel eRozmowa = new JLabel("rosmowa z duszkiem \r\n");
			eRozmowa.setForeground(new Color(255, 255, 0));
			eRozmowa.setVerticalAlignment(SwingConstants.BOTTOM);
			eRozmowa.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(eRozmowa);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(128, 128, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Wyjdz");
				cancelButton.setHorizontalAlignment(SwingConstants.LEFT);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
