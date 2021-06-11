package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.io.FileNotFoundException;

public class Playlist extends Application {
	
	public static void main(String[] args) throws FileNotFoundException {
		Application.launch(args);
	}
	
	@Override 
	public void start(Stage primaryStage) throws FileNotFoundException {       
		Stage secondaryStage = new Stage();
		PlaylistPane playlistPane = new PlaylistPane();
		HBox hBox = new HBox(5);
		//Checks add, remove, sort, exit, and print buttons
		Button btAdd = new Button("Add track");
		Button btRemove = new Button("Remove track");
		Button btSort = new Button("Sort tracks");
		Button btExit = new Button("Exit");
		Button btPrint = new Button("Print");
		//Positions hBox to the center of the scene
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(10);
		//Adds buttons to hBox
		hBox.getChildren().addAll(btAdd, btRemove, btSort, btExit, btPrint);
		//Sets add, remove, sort, exit, and print actions to buttons using PlaylistPane
		btAdd.setOnAction(e -> PlaylistPane.addItems(secondaryStage));
		btRemove.setOnAction(e -> PlaylistPane.removeItems());
		btSort.setOnAction(e -> PlaylistPane.sortItems());
		btExit.setOnAction(e -> primaryStage.close());
		btPrint.setOnAction(e -> PlaylistPane.print()); 
		BorderPane pane = new BorderPane();
		pane.setCenter(playlistPane);
		pane.setBottom(hBox);
	      
		Scene scene = new Scene(pane, 750, 370);
		scene.getStylesheets().add("demo.css");
		primaryStage.setTitle("Playlist");
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
}