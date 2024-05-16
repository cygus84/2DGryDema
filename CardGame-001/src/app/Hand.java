package app;

import java.util.ArrayList;

import app.cards.Card;

public class Hand {

	protected ArrayList<Card> cards;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void clear() {
		cards.clear();
	}
	
	public void add(Card card) {
		cards.add(card);
	}
	
	public String showHand() {
		/* Show cards and their total points, but
		 * only show total points if  All cards are face up
		 */
		boolean allFaceUp = true;
		String str = "";
		for (Card c: cards) {
			str += c.toString() + "\n";
			if (!c.isFaceUp) {
				allFaceUp = false;
			}
		} // If all cards are face up, show total
		if (allFaceUp) {
			str += "Total points = " + getTotal() + "\n";
		}
		return str;
	}
	
	public void flipCards() {
		for (Card c: cards) {
			c.flipCard();
		}
	}
	
	public boolean give(Card card, Hand otherHand) {
		if(cards.contains(card)) {
			int i = cards.indexOf(card);
			cards.remove(i);
			otherHand.add(card);
			return true;
		} else {
			return false;
		}
	}
	// Return total point of a hand
	public int getTotal() {
		int totalPts = 0;
		boolean hasAce = false;
		// Get total points (any Aces by defalult will be worth 1
		for (int i = 0; i < cards.size(); i++) {
			totalPts += cards.get(i).getRank();
			// check to see if card is as Ace
			if(cards.get(i).printRank() == "Ace") {
				hasAce = true;		
			}
			// make Ace worth 11 if total point are less than or equal 11
			if(hasAce && totalPts <= 11) {
				totalPts += 10; // add 10 more to the Ace
			}
		}
		return totalPts;
	}
	
	public String toString() {
		String str = "";
		for(Card card: cards) {
			str += card.toString() + "\n";
		}
		return str;
	}
}
