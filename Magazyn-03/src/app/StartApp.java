package app;

import javax.swing.SwingUtilities;

import app.login.LoginDialog;
import app.magazyn.MagazynApp;

public class StartApp {

	public static void main(String[] args) {
		System.out.println("Start magazyn-03");
		// zmiana tablicy na Jliste oraz dodanie szukania
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                LoginDialog loginDialog = new LoginDialog(null);
	                loginDialog.setVisible(true);

	                if (loginDialog.isSucceeded()) {
	                    new MagazynApp().setVisible(true);
	                } else {
	                    System.exit(0);
	                }
	            }
	        });
	    }

	}
