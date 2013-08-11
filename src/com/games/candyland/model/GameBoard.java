package com.games.candyland.model;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
	
	private List<ColoredSpace> spaces = new ArrayList<ColoredSpace>();
	private CardDeck cardDeck = new CardDeck();
	private List<Player> players = new ArrayList<Player>();
	// Points to player whose turn it is next
	private int playerPointer = 0;
	// Players position on the board
	private Integer[] playerPositions;
	
	private static final int NUMBER_SPACES = 129;
	public static String[] COLOR_SEQUENCE = { 
		"Purple",
		"Yellow",
		"Blue",
		"Orange",
		"Green",
		"Red"
	};
	
	public GameBoard() {
		// Create Spaces
		for (int i = 0; i < NUMBER_SPACES; i++) {
			String color = COLOR_SEQUENCE[i % COLOR_SEQUENCE.length];
			spaces.add(new ColoredSpace(color));
		}
		
		// Shuffle the card deck
		cardDeck.shuffle();
		
		// Add players
		players.add(new Player("Rolf"));
		players.add(new Player("Ben"));
		players.add(new Player("Fred"));
		
		// Put players on board at starting position
		resetPlayerPositions();
	}

	public void play() {
		System.out.println("Lets play CandyLand");
		
		System.out.println("\nSetting up spaces:");
		for (ColoredSpace space : spaces) {
			System.out.println(space.getColor());
		}
		
		System.out.println("\nSetting up Card Deck:");
		for (Card card : cardDeck.getCards()) {
			System.out.println(card.getColor()+" Card");
		}
		
		while (nextTurn());
		
		System.out.println("========== "+players.get(playerPointer).getName()+" won the game!");
	}
	
	// Player pulls a card from the top of the deck and moves on the board
	private boolean nextTurn() {
		// Player selects card from top of deck
		Card currCard = cardDeck.takeTopCard();
		System.out.println("  "+players.get(playerPointer).getName()+"'s turn ("+playerPositions[playerPointer]+") - has "+currCard.getColor()+" card");
		
		// If the game has ended, return now
		if (movePlayerOnBoard(currCard)) return false;
		
		// Next players turn
		playerPointer = (playerPointer + 1) % players.size();

		// Game has not ended yet
		return true;
	}
	
	private void resetPlayerPositions() {
		playerPositions = new Integer[players.size()];
		for (int i = 0; i < players.size(); i++) {
			playerPositions[i] = -1;
		}
	}

	private boolean movePlayerOnBoard(Card currCard) {
		do {
			playerPositions[playerPointer]++;
			// Game ends when player reaches last space
			if (playerPositions[playerPointer] >= spaces.size()) {
				return true;
			}
			System.out.println("    "+players.get(playerPointer).getName()+" moved to "+spaces.get(playerPositions[playerPointer]).getColor());
		} while(!spaces.get(playerPositions[playerPointer]).getColor().equals(currCard.getColor()));
		return false;
	}
	
	
}
