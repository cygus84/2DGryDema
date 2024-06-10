package app.start;

import javax.swing.SwingUtilities;

import app.okna.OknoGraWedkowanie;

public class StartGry {

	public static void main(String[] args) {
	     SwingUtilities.invokeLater(() -> {
	    	 OknoGraWedkowanie gra = new OknoGraWedkowanie();
	            gra.setVisible(true);
	        });
	    }

}
