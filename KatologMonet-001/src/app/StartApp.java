package app;

import java.awt.EventQueue;

import app.grafika.MainFrame;

public class StartApp {

	public static void main(String[] args) {
		System.out.println("Katalog monet-001");
		EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });

	}

}
