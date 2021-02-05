package blackjack;

import java.util.*;

public class Blackjack implements BlackjackEngine {

	/*
	 * Private fields
	 */

	private int numberOfDecks;
	private int account;
	private int bet;
	private ArrayList<Card> deck;
	private ArrayList<Card> playersCards;
	private ArrayList<Card> dealersCards;
	private Random rg;
	private ArrayList<CardSuit> suitList = new ArrayList<>();
	private ArrayList<CardValue> valueList = new ArrayList<>();
	private int gameStatus;

	/**
	 * Constructor you must provide. Initializes the player's account to 200 and the
	 * initial bet to 5. Feel free to initialize any other fields. Keep in mind that
	 * the constructor does not define the deck(s) of cards.
	 * 
	 * @param randomGenerator
	 * @param numberOfDecks
	 */
	public Blackjack(Random randomGenerator, int numberOfDecks) {
		rg = randomGenerator;
		this.numberOfDecks = numberOfDecks;
		account = 200;
		bet = 5;

		suitList = new ArrayList<>(Arrays.asList(CardSuit.SPADES, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.CLUBS));
		valueList = new ArrayList<>(Arrays.asList(CardValue.Ace, CardValue.Two, CardValue.Three, CardValue.Four,
				CardValue.Five, CardValue.Six, CardValue.Seven, CardValue.Eight, CardValue.Nine, CardValue.Ten,
				CardValue.Jack, CardValue.Queen, CardValue.King));
	}

	public int getNumberOfDecks() {
		return numberOfDecks;
	}

	public void createAndShuffleGameDeck() {
		deck = new ArrayList<>();
		for (int i = 0; i < numberOfDecks; i++) {
			for (CardSuit cs : suitList) {
				for (CardValue cv : valueList) {
					deck.add(new Card(cv, cs));
				}
			}
		}
		Collections.shuffle(deck, rg);
	}

	public Card[] getGameDeck() {
		Card[] output = new Card[this.deck.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = deck.get(i);
		}
		return output;
	}

	public void deal() {
		account -= bet;
		this.createAndShuffleGameDeck();
		this.playersCards = new ArrayList<>();
		this.dealersCards = new ArrayList<>();
		Card c = this.deck.remove(0);
		c.setFaceUp();
		this.playersCards.add(c);
		c = this.deck.remove(0);
		c.setFaceDown();
		this.dealersCards.add(c);
		c = this.deck.remove(0);
		c.setFaceUp();
		this.playersCards.add(c);
		c = this.deck.remove(0);
		c.setFaceUp();
		this.dealersCards.add(c);
		this.gameStatus = BlackjackEngine.GAME_IN_PROGRESS;
	}

	public Card[] getDealerCards() {
		Card[] output = new Card[this.dealersCards.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = this.dealersCards.get(i);
		}
		return output;
	}

	public int[] getDealerCardsTotal() {
		int[] totals;
		boolean containsAce = false;
		int sum = 0;
		for (Card c : this.dealersCards) {
			sum += c.getValue().getIntValue();
			if (c.getValue() == CardValue.Ace) {
				containsAce = true;
			}
		}
		if (containsAce && sum + 10 <= 21) {
			totals = new int[2];
			totals[0] = sum;
			totals[1] = sum + 10;
		} else if (sum <= 21) {
			totals = new int[1];
			totals[0] = sum;
		} else {
			totals = null;
		}
		return totals;
	}

	public int getDealerCardsEvaluation() {
		int result = 0;
		ArrayList<CardValue> arr = new ArrayList<>();
		for (Card c : this.dealersCards) {
			arr.add(c.getValue());
		}
		if (this.getDealerCardsTotal() == null)
			result = BlackjackEngine.BUST;
		else if (this.dealersCards.size() == 2 && arr.contains(CardValue.Ace) && (arr.contains(CardValue.Jack)
				|| arr.contains(CardValue.Queen) || arr.contains(CardValue.King) || arr.contains(CardValue.Ten)))
			result = BlackjackEngine.BLACKJACK;
		else {
			int val = this.getDealerCardsTotal()[this.getDealerCardsTotal().length - 1];
			if (val < 21)
				result = BlackjackEngine.LESS_THAN_21;
			else
				result = BlackjackEngine.HAS_21;
		}
		return result;
	}

	public Card[] getPlayerCards() {
		Card[] output = new Card[this.playersCards.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = this.playersCards.get(i);
		}
		return output;
	}

	public int[] getPlayerCardsTotal() {
		int[] totals;
		boolean containsAce = false;
		int sum = 0;
		for (Card c : this.playersCards) {
			sum += c.getValue().getIntValue();
			if (c.getValue() == CardValue.Ace) {
				containsAce = true;
			}
		}
		if (containsAce && sum + 10 <= 21) {
			totals = new int[2];
			totals[0] = sum;
			totals[1] = sum + 10;
		} else if (sum <= 21) {
			totals = new int[1];
			totals[0] = sum;
		} else {
			totals = null;
		}
		return totals;
	}

	public int getPlayerCardsEvaluation() {
		int result = 0;
		ArrayList<CardValue> arr = new ArrayList<>();
		for (Card c : this.playersCards) {
			arr.add(c.getValue());
		}
		if (this.getPlayerCardsTotal() == null)
			result = BlackjackEngine.BUST;
		else if (this.playersCards.size() == 2 && arr.contains(CardValue.Ace) && (arr.contains(CardValue.Jack)
				|| arr.contains(CardValue.Queen) || arr.contains(CardValue.King) || arr.contains(CardValue.Ten)))
			result = BlackjackEngine.BLACKJACK;
		else {
			int val = this.getPlayerCardsTotal()[this.getPlayerCardsTotal().length - 1];
			if (val < 21)
				result = BlackjackEngine.LESS_THAN_21;
			else
				result = BlackjackEngine.HAS_21;
		}
		return result;
	}

	public void playerHit() {
		this.playersCards.add(this.deck.remove(0));
		int val = this.getPlayerCardsEvaluation();
		if (val == BlackjackEngine.BUST)
			this.gameStatus = BlackjackEngine.DEALER_WON;
		else
			gameStatus = BlackjackEngine.GAME_IN_PROGRESS;
	}

	public void playerStand() {
		for (Card c : this.dealersCards)
			c.setFaceUp();
		while (this.getDealerCardsEvaluation() != BlackjackEngine.BUST && this.dealerLessThanSixteen()) {
			this.dealersCards.add(this.deck.remove(0));
		}
		if (this.getDealerCardsEvaluation() == BlackjackEngine.BUST) {
			this.gameStatus = BlackjackEngine.PLAYER_WON;
			account += 2 * bet;
		} else {
			if (this.getPlayerCardsEvaluation() == BlackjackEngine.BUST)
				this.gameStatus = BlackjackEngine.DEALER_WON;
			else {
				int player_result = this.getPlayerCardsTotal()[this.getPlayerCardsTotal().length - 1];
				int dealer_result = this.getDealerCardsTotal()[this.getDealerCardsTotal().length - 1];
				if (player_result > dealer_result) {
					this.gameStatus = BlackjackEngine.PLAYER_WON;
					account += 2 * bet;
				} else if (dealer_result > player_result) {
					this.gameStatus = BlackjackEngine.DEALER_WON;
				} else {
					this.gameStatus = BlackjackEngine.DRAW;
					account += bet;
				}
			}
		}

	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setBetAmount(int amount) {
		bet = amount;
	}

	public int getBetAmount() {
		return bet;
	}

	public void setAccountAmount(int amount) {
		account = amount;
	}

	public int getAccountAmount() {
		return account;
	}

	/* Feel Free to add any private methods you might need */

	private boolean dealerLessThanSixteen() {
		for (int i : this.getDealerCardsTotal()) {
			if (i >= 16)
				return false;
		}
		return true;

	}

}