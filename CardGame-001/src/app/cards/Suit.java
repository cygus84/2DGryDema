package app.cards;

public enum Suit {
	HEARTS("Hearts"),  
	SPADES("Spades"),
	DIAMONDS("Diaomnds"),
	CLUBS("Clubs");

	private final String suitText;
	
	// Constructor
	private Suit(String suitText){
		this.suitText = suitText;
	}
	// Public Method
	public String printSuit() {
		return suitText;
	}
}
