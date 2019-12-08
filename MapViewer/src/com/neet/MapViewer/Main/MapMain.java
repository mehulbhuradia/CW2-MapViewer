package com.neet.MapViewer.Main;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MapMain extends Application {
    public static Stage primaryStage;
    public static TileMapViewer tileMapViewer;
    
    public BorderPane rootLayout;
    public TilePane tileOverview;

    public static boolean viewerLaunch = false;
    
	@Override
	public void start(Stage primaryStage) {
		MapMain.primaryStage = primaryStage;
        MapMain.primaryStage.setTitle("MapViewer");
        
        Platform.setImplicitExit(false);

        initRootLayout();
        
		showMapOverview();
		
		viewerLaunch = true;
		
		primaryStage.setOnCloseRequest(event -> { Platform.setImplicitExit(true); });
	}
	
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MapMain.class.getResource("/com/neet/MapViewer/View/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            setFadeAnimation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
