package app.game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import javax.swing.*;

import appcards.Card;
import appcards.MenagerCards;

public class BlackJack {

	private Card card;
	private ArrayList<Card> deck;
	private Random random = new Random();// tasowanie decka
	// DELAR
	private Card hiddenCard; // delar
	private ArrayList<Card> delarHand;
	private int delarSum;
	private int delerAceCount;
	// PLAYER
	private ArrayList<Card> playerHand;
	private int playerSum;
	private int playerAceCount;
	// window
	private int cardWidth = 110; // ratio 1/1.4
	private int cardHeight = 154;

	private int boardWidth = 600;
	private int boardHeight = boardWidth;
	private JFrame frame = new JFrame("BlackJack-01");
	private JPanel gamePanel = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			try {
				// draw hidden card
				Image hiddenCardImg = new ImageIcon(
						Toolkit.getDefaultToolkit().getImage(BlackJack.class.getResource("/cards/BACK.png")))
						.getImage();
				if(!stayButton.isEnabled()) {
					hiddenCardImg = new ImageIcon(BlackJack.class.getResource(card.getImagePatch())).getImage();
				}
				g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);
				
				// draw dealer hand
				for (int i = 0; i < delarHand.size(); i++) {
					Card card = delarHand.get(i);
					Image cardImg = new ImageIcon(BlackJack.class.getResource(card.getImagePatch())).getImage();
					g.drawImage( cardImg, cardWidth + 25 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
				}
				
				//draw player
				for (int i = 0; i < playerHand.size(); i++) {
					Card card = playerHand.get(i);
					Image cardImg = new ImageIcon(BlackJack.class.getResource(card.getImagePatch())).getImage();
					g.drawImage( cardImg,20 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null);
				}
				
				if (!stayButton.isEnabled()) {
					delarSum = reduceDealerAce();
					playerSum = reducePlayerAce();
					System.out.println("STAY:");
					System.out.println(delarSum);
					System.out.println(playerSum);
					// Rysowanie na ekranie
					String message = "";
					if (playerSum > 21) {
						message = "You Lose!";
					}else if (delarSum > 21) {
						message = "You Win!";
					}else if(playerSum == delarSum) { // both you and delear <= 21
						message = "Tie";
					}else if(playerSum > delarSum) {
						message = "You Win!";
					}else if(playerSum < delarSum) {
						message = "You Lose!";			
					}
					// rysowanie napisow 
					g.setFont(new Font("Arial", Font.PLAIN,30));
					g.setColor(Color.WHITE);
					g.drawString(message, 220, 250);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	private JPanel buttonPanel = new JPanel();
	private JButton hitButton = new JButton("Hit");
	private JButton stayButton = new JButton("Stay");

	public BlackJack() {
		startGame();
		// ustawnia ramki
		frame.setVisible(true);
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// dodanie panela gry
		gamePanel.setLayout(new BorderLayout());
		gamePanel.setBackground(new Color(53, 101, 77));
		frame.getContentPane().add(gamePanel);
		// doadnie panela przyciskow
		hitButton.setFocusable(false);
		buttonPanel.add(hitButton);
		stayButton.setFocusable(false);
		buttonPanel.add(stayButton);
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		// obsluga przyciskow
		hitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Card card = deck.remove(deck.size() - 1);
				playerSum += card.getValue();
				playerAceCount += card.isAce()? 1 : 0;
				playerHand.add(card);
				if (reducePlayerAce() > 21) {
					hitButton.setEnabled(false);
				}
				gamePanel.repaint();		
			}
		});
		
		stayButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hitButton.setEnabled(false);
				stayButton.setEnabled(false);
				
				while (delarSum < 17) {
					Card card = deck.remove(deck.size() - 1);
					delarSum += card.getValue();
					delerAceCount += card.isAce()? 1 : 0;
					delarHand.add(card);
					gamePanel.repaint();
				}
			}
		});
		
		gamePanel.repaint();
	}

	public void startGame() {
		// deck
		buildDeck();
		shuffleDeck();

		// delar
		delarHand = new ArrayList<Card>();
		delarSum = 0;
		delerAceCount = 0;
		hiddenCard = deck.remove(deck.size() - 1); // remove card last index
		delarSum += hiddenCard.getValue();
		delerAceCount += hiddenCard.isAce() ? 1 : 0;

		Card card = deck.remove(deck.size() - 1);
		delarSum += card.getValue();
		delerAceCount += hiddenCard.isAce() ? 1 : 0;
		delarHand.add(card);

		System.out.println("DEALER");
		System.out.println(hiddenCard);
		System.out.println(delarHand);
		System.out.println(delarSum);
		System.out.println(delerAceCount);

		// player
		playerHand = new ArrayList<Card>();
		playerSum = 0;
		playerAceCount = 0;

		for (int i = 0; i < 2; i++) {
			card = deck.remove(deck.size() - 1);
			playerSum += card.getValue();
			playerAceCount += card.isAce() ? 1 : 0;
			playerHand.add(card);
		}

		System.out.println("PLEAYER");
		System.out.println(playerHand);
		System.out.println(playerSum);
		System.out.println(playerAceCount);

	}

	private void shuffleDeck() {
		for (int i = 0; i < deck.size(); i++) {
			int j = random.nextInt(deck.size());
			Card currentCard = deck.get(i);
			Card randomCard = deck.get(j);
			deck.set(i, randomCard);
			deck.set(j, currentCard);
		}

		System.out.println("AFTER SHUFFLE");
		System.out.println(deck);
	}

	private void buildDeck() {
		deck = new ArrayList<Card>();
		String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		String[] types = { "C", "D", "H", "S" };

		for (int i = 0; i < types.length; i++) {
			for (int j = 0; j < values.length; j++) {
				card = new Card(values[j], types[i]);
				deck.add(card);
			}
		}

		System.out.println("BUILD DECK");
		System.out.println(deck);
	}
	
	public int reducePlayerAce() {
		while (playerSum > 21 && playerAceCount > 0) {
			playerSum -= 10;
			playerAceCount -= 1;
		}
		return playerSum;
	}
	
	public int reduceDealerAce() {
		while (delarSum > 21 && delerAceCount > 0) {
			delarSum -= 10;
			delerAceCount -= 1;
		}
		return delarSum;
	}
}
