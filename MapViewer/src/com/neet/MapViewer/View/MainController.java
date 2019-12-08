package com.neet.MapViewer.View;

import com.neet.DiamondHunter.Main.Game;
import com.neet.MapViewer.Main.MapMain;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {

	@FXML
	private Label cursorPosition;
	
	@FXML
	private Label information;

	@FXML
	private Label axePosition;

	@FXML
	private Label boatPosition;


	@FXML
	private void handleKeyAction(KeyEvent event) {
		if (MapMain.tileMapViewer.cursorColor == false) {
			information.setText("X: Axe, C: Boat");
		}

		if(event.getCode() == KeyCode.K) {
			MapMain.tileMapViewer.zoomInImage();
	    }
	    else if (event.getCode() == KeyCode.J) {
	    	MapMain.tileMapViewer.zoomOutImage();
	    }
	    else if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
	    	MapMain.tileMapViewer.cursorUp();
	    	updateCursorPosition();
	    }
	    else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
	    	MapMain.tileMapViewer.cursorDown();
	    	updateCursorPosition();
	    }
	    else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
	    	MapMain.tileMapViewer.cursorLeft();
	    	updateCursorPosition();
	    }
	    else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
	    	MapMain.tileMapViewer.cursorRight();
	    	updateCursorPosition();
	    }
	    else if (event.getCode() == KeyCode.X) {
	    	MapMain.tileMapViewer.turningOnCurorColor();
	    }
	    else if (event.getCode() == KeyCode.C) {
	    	MapMain.tileMapViewer.turningOnCurorColor();
	    }
	    else if (event.getCode() == KeyCode.ENTER) {
			MapMain.primaryStage.hide();
			Game.main(null);
	    }
	}

	@FXML
	private void handleSetPosition(KeyEvent event) {
		if (event.getCode() == KeyCode.X) 
			MapMain.tileMapViewer.handleSetAxeRequest();
		else if (event.getCode() == KeyCode.C) 
			MapMain.tileMapViewer.handleSetBoatRequest();
	}

	@FXML
	private void exit() {
		Platform.setImplicitExit(true);
		MapMain.primaryStage.hide();
	}

	@FXML
	private void zoomInFromMenu() {
		MapMain.tileMapViewer.zoomInImage();
	}
	
	@FXML
	private void zoomOutFromMenu() {
		MapMain.tileMapViewer.zoomOutImage();
	}
	
	private void updateCursorPosition() {
		cursorPosition.setText("x: " + MapMain.tileMapViewer.cursor.cursorRows + ", y: " + MapMain.tileMapViewer.cursor.cursorCols);
	}
}
