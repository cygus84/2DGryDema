package app;

import javax.swing.SwingUtilities;

import app.login.LoginDialog;
import app.okna.OknoGlowne;

public class StartApp {

	public static void main(String[] args) {
		System.out.println("MagazynMojaWersja-00");

		//new OknoGlowne();// sprawdzone
		
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                LoginDialog loginDialog = new LoginDialog(null);
	                loginDialog.setVisible(true);

	                if (loginDialog.isSucceeded()) {
	                    new OknoGlowne().setVisible(true);
	                } else {
	                    System.exit(0);
	                }
	            }
	        });
		// dziala
		
//		new OknoGlowne();
		
	    }
	}


