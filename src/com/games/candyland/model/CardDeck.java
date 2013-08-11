package com.games.candyland.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class CardDeck {
	
	private Stack<Card> cards;

	private static final Map<String, Integer> CARD_GROUPS;
	static {
		CARD_GROUPS = new HashMap<String, Integer>();
		CARD_GROUPS.put(GameBoard.COLOR_SEQUENCE[0], 4);
		CARD_GROUPS.put(GameBoard.COLOR_SEQUENCE[1], 6);
		CARD_GROUPS.put(GameBoard.COLOR_SEQUENCE[2], 6);
		CARD_GROUPS.put(GameBoard.COLOR_SEQUENCE[3], 4);
		CARD_GROUPS.put(GameBoard.COLOR_SEQUENCE[4], 4);
		CARD_GROUPS.put(GameBoard.COLOR_SEQUENCE[5], 6);
	}
	
	public CardDeck() {
		addCardsToDeck();
	}
	
	private void addCardsToDeck() {
		System.out.println("**** Add cards to Deck");
		cards = new Stack<Card>();
		// Add cards to deck based on color and number
		for (Map.Entry<String, Integer> cardGroupEntry : CARD_GROUPS.entrySet()) {
			String color = cardGroupEntry.getKey();
			int numCardsInGroup = cardGroupEntry.getValue();
			for (int i = 0; i < numCardsInGroup; i++) {
				cards.push(new Card(color));
			}
		}
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Card takeTopCard() {
		if (cards.empty()) {
			addCardsToDeck();
			shuffle();
		}
		
		return cards.pop();
	}
	
}
