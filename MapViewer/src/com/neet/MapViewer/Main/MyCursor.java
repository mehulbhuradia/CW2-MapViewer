package com.neet.MapViewer.Main;

import javafx.scene.image.Image;

public class MyCursor {
	/**
	 * The variable loads the cursor images. 
	 */
	
	public Image[] cursorColor;

	/**
	 * The variable decides which image to use.
	 * Black, red or blue cursor. 
	 */
	public int current = 2;
	public int cursorCols;
	public int cursorRows;
	
	public MyCursor() {
		cursorColor = new Image[3];
		cursorColor[0] = new Image(MyCursor.class.getResourceAsStream("/Sprites/selector_blue.png"));
		cursorColor[1] = new Image(MyCursor.class.getResourceAsStream("/Sprites/selector_red.png"));
		cursorColor[2] = new Image(MyCursor.class.getResourceAsStream("/Sprites/selector_black.png"));
		cursorCols = 17;
		cursorRows = 17;
		
	}
	
}
