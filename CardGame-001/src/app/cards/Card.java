package app.cards;

public class Card {

	private Suit suit;
	private Rank rank;
	public boolean isFaceUp;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		isFaceUp = false;
	}
	
	public String getSuit() {
		return suit.printSuit();
	}
	
	public int getRank() {
		return rank.getRank();
	}
	
	public String printRank() {
		// get rank as a String (primarly for aces)
		return rank.printRank();
	}
	
	public void flipCard() {
		isFaceUp = !isFaceUp;
	}
	
	public String toString() {
		String str = "";
		if(isFaceUp) {
		str += rank.printRank() + " of " + suit.printSuit();
		} else {
			str = "Face Down (nothing to see her!)";
		}
		return str;
	}
}
