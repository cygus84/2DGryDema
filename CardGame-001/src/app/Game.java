package app;

import app.cards.Card;
import app.cards.Rank;
import app.cards.Suit;

public class Game {

	private Card c1, c2;
	
	public Game() {
		System.out.println("Start gry!");
		
//		c1 = new Card(Rank.ACE, Suit.HEARTS);
//		c2 = new Card(Rank.JACK, Suit.HEARTS);
//		
//		// flip our cards
//		c1.flipCard();
//		c2.flipCard();
//		
//		// create a heand	
//		Hand h1 = new Hand();
//		Hand h2 = new Hand();
//		h1.add(c1);
//		h1.add(c2);
//		System.out.println("Hand 1 is: " + h1.showHand());
//		h1.give(c1, h2);
//		h1.clear();
//		System.out.println("Hand 1 is: " + h1.showHand() + "\n" + "Hand 2 is now: " + h2.showHand());
//		
		//Create a Deck
//		Deck d1 = new Deck();
//		d1.populate();
//		System.out.println("Deck has folling cards: " + d1.showHand());
//		d1.shuffle();
//		System.out.println("Deck(shuffle) now has folling cards: " + d1.showHand());
//		
		Deck d1 = new Deck();
		d1.populate();
		d1.shuffle();
		
		//Create  our playing hands
		Hand h1, h2, h3, dealer;
		// Deal cards to our hands
		h1 = new Hand();
		h2 = new Hand();
		h3 = new Hand();
		dealer = new Hand();
		Hand[] hands = {h1, h2, h3};
		d1.deal(hands, 2);
		// Deal 2 cards to dealer
		d1.deal(dealer, 2);
		// Show the players hands
		for (int i = 0; i < hands.length; i++) {
			hands[i].flipCards();
			System.out.println(hands[i].showHand());
		}
		
		// Flip the dealers cards
		dealer.cards.get(0).flipCard();
		// Show the dealers cards
		System.out.println("\n Delers Cards: \n" + dealer.showHand());
	}	
	
}
