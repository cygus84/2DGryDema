package okna;

import watki.WatekLadowanieDanychAplikacji;

@SuppressWarnings("serial")
public class OknoLadowaniaDanychAplikacj extends OknaLadowaniaWzorzec {
	
	private WatekLadowanieDanychAplikacji watekDanychAplikacji; 
	
	public OknoLadowaniaDanychAplikacj() {
		super();
		
	}

	@Override
	protected void startWatkuLadujacegoDane() {
		watekDanychAplikacji = new WatekLadowanieDanychAplikacji();
	}

	@Override
	protected void aktulizacjaDanychGlonejPentli() {
		komunikat = watekDanychAplikacji.getKomunikat();
		isLadowanieDanych = watekDanychAplikacji.isLadowanieDanych();
	}

	@Override
	protected void akcjaPoZaladowaniuDanych() {
		new OknoMenu();
	}	
	
	
}
