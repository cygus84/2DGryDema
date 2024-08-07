package app;

import javax.swing.SwingUtilities;

import app.log.LoginDialog;
import app.magazyn.MagazynApp;

public class StartAplikacji {

	public static void main(String[] args) {
		System.out.println("Magazyn-02");
		// Uruchom aplikację, zaloguj się przy użyciu danych "admin" i "password".
		
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
