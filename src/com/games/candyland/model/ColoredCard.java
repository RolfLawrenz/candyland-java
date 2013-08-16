package com.games.candyland.model;

public class ColoredCard implements Card {
	
	private String color;

	public ColoredCard(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
	
	public String getName() {
		return color;
	}
	
	@Override
	public boolean movePlayerOnBoard(GameBoard gameBoard) {
		do {
			gameBoard.incrementPlayersPosition();
			// Game ends when player reaches last space
			if (gameBoard.hasPlayerReachedEnd()) {
				return true;
			}
			System.out.println("    "+gameBoard.currPlayerName()+" moved to ("+ gameBoard.getPlayersPosition() + ") "+ gameBoard.currPlayerSpaceName());
		} while(!gameBoard.currPlayerSpaceName().equals(this.getColor()));
		return false;
	}

}
