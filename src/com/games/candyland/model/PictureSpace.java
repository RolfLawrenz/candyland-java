package com.games.candyland.model;

public class PictureSpace implements Space {

	private String picture;

	public PictureSpace(String picture) {
		this.picture = picture;
	}

	public String getPicture() {
		return picture;
	}
	
	public String getName() {
		return picture;
	}
}
