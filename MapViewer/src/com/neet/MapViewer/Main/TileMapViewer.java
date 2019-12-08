package com.neet.MapViewer.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class TileMapViewer implements ItemPosition {
	private int boatRow = -1;
	private int boatCol = -1;
	private int axeRow = -1;
	private int axeCol = -1;
	
	
	public int getBoatRow() {
		return boatRow;
	}
	public int getBoatCol() {
		return boatCol;
	}
	public int getAxeRow() {
		return axeRow;
	}
	public int getAxeCol() {
		return axeCol;
	}
	
	private final int BOAT = 0;
	private final int AXE = 1;

	private int tileSize = 16;
	public int numCols;
	public int numRows;
	
	public MyCursor cursor;
	public boolean cursorColor = false;

	private int currentNumCols;
	private int currentNumRows;

	private int magnification = 1;

	private int[][] mapMatrix;
	private int[][] tileType;
	
	private Image tileset;
	private int numTilesAcross;
}
