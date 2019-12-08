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

	/**
	 * Variable mainCanvas is to update the whole map
	 */
	private Canvas mainCanvas;
	
	/**
	 * Variable currentCanvas is to show the current part of map with proper magnification
	 */
	public Canvas currentCanvas;

	/**
	 * Variable mapImage is to store current image of the whole map with the cursor, since the map can be zoomed
	 * in and out, the currentCanvas always take part of the mapImage into drawing current image
	 */
	private Image mapImage;
	
	/**
	 * Variable originalMapImage is to store original image of the whole map without the cursor, so that when
	 * the cursor is moved, the current map image is updated properly
	 */
	private Image originalMapImage;

	public int offset;
	public int movesetCols;
	public int movesetRows;

	public Image items;
	public boolean axePut = false;
	public boolean boatPut = false;

	/**
	 * The method reads numbers from map file, and then write the data to mapMatrix.
	 * The value of numCols and numRows is also known from the map file.
	 * 
	 * mapFile - The file that contains information of each grid in the map 
	 */
	public void loadMapFile(String mapFile) {
		try {
			InputStream in = getClass().getResourceAsStream(mapFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			currentNumCols = numCols;
			currentNumRows = numRows;

			mapMatrix = new int[numRows][numCols];

			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					mapMatrix[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method inputs image from resource.
	 * 
	 * tilesetImage - The file that contains images of each tile 
	 * itemsImage - The file that contains images of axe and boat
	 */
	public void loadImagesFiles(String tilesetImage, String itemsImage) {
		try {
			tileset = new Image(TileMapViewer.class.getResourceAsStream(tilesetImage));
			items = new Image(TileMapViewer.class.getResourceAsStream(itemsImage));
			numTilesAcross = (int)tileset.getWidth() / tileSize;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method initializes two used canvases and takes the snapshot. 
	 */
	public void initialiseCanvas() {
		mainCanvas = new Canvas(640,640);
		currentCanvas = new Canvas(640, 640);
		tileType = new int[numRows][numCols];
		cursor = new MyCursor();

		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				if(mapMatrix[row][col] == 0) continue;

				int rc = mapMatrix[row][col];

				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;

				if (r == 0) {
					mainCanvas.getGraphicsContext2D().drawImage(
							tileset, c * tileSize, 0, tileSize, tileSize,
							col * tileSize, row * tileSize, tileSize, tileSize);
					currentCanvas.getGraphicsContext2D().drawImage(
							tileset, c * tileSize, 0, tileSize, tileSize,
							col * tileSize, row * tileSize, tileSize, tileSize);
					tileType[row][col] = 0;
				}
				else {
					mainCanvas.getGraphicsContext2D().drawImage(
							tileset, c * tileSize, tileSize, tileSize, tileSize,
							col * tileSize, row * tileSize, tileSize, tileSize);
					currentCanvas.getGraphicsContext2D().drawImage(
							tileset, c * tileSize, tileSize, tileSize, tileSize,
							col * tileSize, row * tileSize, tileSize, tileSize);
					tileType[row][col] = 1;
				}

			}
		}
		originalMapImage = mainCanvas.snapshot(null, null);
		drawCursorToMainCanvas();
		currentCanvas.getGraphicsContext2D().drawImage(
				cursor.cursorColor[cursor.current], 0, 0, tileSize, tileSize,
				 cursor.cursorCols * tileSize, cursor.cursorRows * tileSize,
				 tileSize, tileSize);
		mapImage = mainCanvas.snapshot(null, null);
	}
}
