package com.games.candyland.model;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
	
	private List<ColoredSpace> spaces = new ArrayList<ColoredSpace>();
	private static final int NUMBER_SPACES = 129;
	private String[] COLOR_SEQUENCE = { 
		"Purple",
		"Yellow",
		"Blue",
		"Orange",
		"Green",
		"Red"
	};
	
	public GameBoard() {
		for (int i = 0; i < NUMBER_SPACES; i++) {
			String color = COLOR_SEQUENCE[i % COLOR_SEQUENCE.length];
			spaces.add(new ColoredSpace(color));
		}
	}

	public void play() {
		System.out.println("Lets play CandyLand");
		
		System.out.println("Spaces are:");
		for (ColoredSpace space : spaces) {
			System.out.println(space.getColor());
		}
	}
}
