package com.games.candyland.model;

public class PictureCard implements Card {
	
	private String picture;

	public PictureCard(String picture, int position) {
		this.picture = picture;
	}

	public String getPicture() {
		return picture;
	}
	
	public String getName() {
		return picture;
	}

	@Override
	public boolean movePlayerOnBoard(GameBoard gameBoard) {
		int position = gameBoard.getPictureCardPosition(picture);
		gameBoard.setPlayersPosition(position-1);
		System.out.println("    **"+gameBoard.currPlayerName()+" moved to "+"("+ gameBoard.getPlayersPosition() + ") "+ gameBoard.currPlayerSpaceName());
		return false;
	}

}
