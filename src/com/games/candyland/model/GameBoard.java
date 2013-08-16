package com.games.candyland.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {
	
	private List<Space> spaces = new ArrayList<Space>();
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
	
	public static final Map<Integer, String> PICTURE_SPACE_POSITIONS = new HashMap<Integer, String>();
	
	static {
		PICTURE_SPACE_POSITIONS.put(8,"CandyHeart");
		PICTURE_SPACE_POSITIONS.put(16,"Peppermint Stick");
		PICTURE_SPACE_POSITIONS.put(29,"Ginger Bread");
		PICTURE_SPACE_POSITIONS.put(43,"Gum Drop");
		PICTURE_SPACE_POSITIONS.put(76,"Peanut Brittle");
		PICTURE_SPACE_POSITIONS.put(99,"Lollypop");
		PICTURE_SPACE_POSITIONS.put(107,"Ice Cream");
	}
	
	public GameBoard() {
		// Create Spaces
		int pos = 1;
		for (int i = 0; i < NUMBER_SPACES; i++) {
			String picture = PICTURE_SPACE_POSITIONS.get(pos);
			if (picture != null) {
				spaces.add(new PictureSpace(picture));
				pos++;
			}
			String color = COLOR_SEQUENCE[i % COLOR_SEQUENCE.length];
			spaces.add(new ColoredSpace(color));
			pos++;
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
		int i = 1;
		for (Space space : spaces) {
			System.out.println(i++ + " " + space.getName());
		}
		
		System.out.println("\nSetting up Card Deck:");
		for (Card card : cardDeck.getCards()) {
			System.out.println(card.getName()+" Card");
		}
		
		while (nextTurn());
		
		System.out.println("========== "+players.get(playerPointer).getName()+" won the game!");
	}
	
	// Player pulls a card from the top of the deck and moves on the board
	private boolean nextTurn() {
		// Player selects card from top of deck
		Card currCard = cardDeck.takeTopCard();
		System.out.println("  "+players.get(playerPointer).getName()+"'s turn ("+playerPositions[playerPointer]+") - has "+currCard.getName()+" card");
		
		// If the game has ended, return now
		if (currCard.movePlayerOnBoard(this)) return false;
		
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

	public void incrementPlayersPosition() {
		playerPositions[playerPointer]++;
	}

	public boolean hasPlayerReachedEnd() {
		if (playerPositions[playerPointer] >= spaces.size()) {
			return true;
		}
		return false;
	}
	
	public String currPlayerName() {
		return players.get(playerPointer).getName();		
	}
	
	public String currPlayerSpaceName() {
		return spaces.get(playerPositions[playerPointer]).getName();		
	}

	public int getPictureCardPosition(String picture) {
		for (Map.Entry<Integer, String> entry : PICTURE_SPACE_POSITIONS.entrySet()) {
			if (entry.getValue().equals(picture)) {
				return entry.getKey();
			}
		}
		return 0;
	}

	public int getPlayersPosition() {
		return playerPositions[playerPointer];
	}
	
	public void setPlayersPosition(int position) {
		playerPositions[playerPointer] = position;
	}
}
