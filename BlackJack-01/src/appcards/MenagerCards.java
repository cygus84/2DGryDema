package appcards;

import java.util.ArrayList;

public class MenagerCards {
	
	private ArrayList<Card> deck;
	
	public MenagerCards() {
	}

	public MenagerCards(ArrayList<Card> deck) {
		this.deck = deck;
	}
	
	public void add(Card card) {
		deck.add(card);
	}
}
