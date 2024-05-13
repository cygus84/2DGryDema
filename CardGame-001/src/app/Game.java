package app;

import app.cards.Card;
import app.cards.Rank;
import app.cards.Suit;

public class Game {

	private Card c1, c2;
	
	public Game() {
		System.out.println("Start gry!");
		
		c1 = new Card(Rank.ACE, Suit.HEARTS);
		c2 = new Card(Rank.JACK, Suit.HEARTS);
		
//		// flip our cards
//		c1.flipCard();
//		c2.flipCard();
//		
		// create a heand	
		Hand h1 = new Hand();
		Hand h2 = new Hand();
		h1.add(c1);
		h1.add(c2);
		System.out.println("Hand 1 is: " + h1.showHand());
		h1.give(c1, h2);
		System.out.println("Hand 1 is: " + h1.showHand() + "\n" + "Hand 2 is now: " + h2.showHand());
	}	
	
}
