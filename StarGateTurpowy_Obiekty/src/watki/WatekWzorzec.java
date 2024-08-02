package watki;

public class WatekWzorzec extends Thread {
	
	public WatekWzorzec() {
		uruchom();
	}
	
	protected void uruchom() {
		this.start();
	}
	
	protected void akcja() {
		
	}
	
	public void run() {
		akcja();
	}

}
