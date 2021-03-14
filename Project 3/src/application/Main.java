package application;
	
import java.io.File;
import java.io.IOException;  

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
//import View.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import application.Controller;

 

/**
 * Driver class of the Employee Registry GUI.
 * @author Abdullah Salem, Gent Blaku
 */
public class Main extends Application {
	/**
	 * This start function initializes the stage for the company registry GUI.
	 * @param primaryStage object of the Stage class
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			
//			Controller controller = loader.getController();
//			controller.setMainStage(primaryStage);
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("View.fxml"));
			Scene scene = new Scene(root,1000,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Employee Registry");
			primaryStage.setScene(scene);
			primaryStage.show();
			//FileChooser fileChooser = new FileChooser();
			
			
		//	File selectedFile = fileChooser.showOpenDialog(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function launches the GUI.
	 * @param args 
	 */
	public static void main(String[] args) {
		launch(args);
	}
	

	
	
	

	}
	

