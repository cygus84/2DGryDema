package app;

import java.util.ArrayList;

import app.cards.Card;

public class Hand {

	private ArrayList<Card> cards;
	
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
		String str = "";
		for (Card c: cards) {
			str += c.toString() + "\n";
		}
		return str;
	}
	
	public boolean give(Card card, Hand otherHand) {
		if(!cards.contains(card)) {
			return false;
		} else {
			cards.remove(card);
			otherHand.add(card);
			return true;
		}
	}
}
