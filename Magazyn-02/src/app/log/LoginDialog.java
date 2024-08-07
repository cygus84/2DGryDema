package app.log;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;

public class LoginDialog extends JDialog{

	private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean succeeded;

    public LoginDialog(Frame parent) {
        super(parent, "Login", true);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel label = new JLabel("Username:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(label);
        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(usernameField);
        JLabel label_1 = new JLabel("Password:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(label_1);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authenticate(usernameField.getText(), new String(passwordField.getPassword()))) {
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    usernameField.setText("");
                    passwordField.setText("");
                    succeeded = false;
                }
            }
        });
        panel.add(loginButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    private boolean authenticate(String username, String password) {
        // Tu można dodać rzeczywiste sprawdzenie w bazie danych
        // Dla prostoty użyjemy twardo zakodowanego użytkownika
        return "admin".equals(username) && "password".equals(password);
    }
}
