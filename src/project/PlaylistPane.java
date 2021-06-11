package project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlaylistPane extends Pane {
	// TableView that stores track objects
	private static TableView<Track> tableView = new TableView<Track>(); 
	//Playlist manager that manages all the track objects
	private static PlaylistManager p = new PlaylistManager();
	//Table columns to store a track and all the cells of String variables in the column  
	private static TableColumn<Track, String> lengthList = new TableColumn<>("Length");
	private static TableColumn<Track, String> trackList = new TableColumn<>("Track");
	private static TableColumn<Track, String> artistList = new TableColumn<>("Artist");
	private static TableColumn<Track, String> albumList = new TableColumn<>("Album");
	private static TableColumn<Track, String> genreList = new TableColumn<>("Genre");

	public PlaylistPane() throws FileNotFoundException {
		//Sets cell values for each column by creating new property values that use specified variables in an object
		artistList.setCellValueFactory(new PropertyValueFactory<>("artist"));
	    trackList.setCellValueFactory(new PropertyValueFactory<>("track"));
	    lengthList.setCellValueFactory(new PropertyValueFactory<>("length"));
	    albumList.setCellValueFactory(new PropertyValueFactory<>("album"));
	    genreList.setCellValueFactory(new PropertyValueFactory<>("genre"));
	    //Adds columns to tableView and sets it's height and width
	    tableView.getColumns().add(artistList);
	    tableView.getColumns().add(trackList);
	    tableView.getColumns().add(lengthList);
	    tableView.getColumns().add(albumList);
	    tableView.getColumns().add(genreList);
	    tableView.setMinWidth(588);
	    tableView.setPrefHeight(300);
	    Scanner inFile = new Scanner(new FileReader("playlist.txt"));
	  	String[] tokens = null;
	  	while(inFile.hasNext()) {
	  		String s = inFile.nextLine().trim();	
	  	    //Splits the value of s by dashes and then stores it into tokens
	  	    tokens = s.split("[\\|]");
	  	    //Sets String variables equal to specified indexes of tokens. Tokens will always have a length of 5
	  	    String artist = tokens[0].trim();
	  	    String track = tokens[1].trim();
	  	    String length = tokens[2].trim();
	  	    String album = tokens[3].trim();
	  	    String genre = tokens[4].trim();
	  	    //Creates new song object using String variables 
	  	    Song song = new Song(track, artist, length, album, genre);
	  	    //Adds song to playlist manager
	  	    p.addData(song);
	  	    //Adds song to tableView
	  	    tableView.getItems().add(song);
	  		}   
	    // Fill the pane with all the components.
	  	VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(25, 5, 5, 80));
        vbox.getChildren().add(tableView);
	    getChildren().add(vbox);
	    setVisible(true);
	   }
	      
	public static void addItems(Stage stage) {
		//Creating textfields 
		TextField trackField = new TextField("");
	    TextField artistField = new TextField("");
	    TextField lengthField = new TextField("");
	    TextField albumField = new TextField("");
	    TextField genreField = new TextField("");
	    //Creating labels
	    Label trackLabel = new Label("Track: ");
	    Label artistLabel = new Label("Artist: ");
	    Label lengthLabel = new Label("Length: ");
	    Label albumLabel = new Label("Album: ");
	    Label genreLabel = new Label("Genre: ");
	   
	    //Adding labels for nodes
	    HBox box = new HBox(5);
	    HBox box2 = new HBox(5);
	    box.setPadding(new Insets(50, 5 , 5, 50));
	    //Adds labels and textfields to box
	    box.getChildren().addAll(trackLabel, trackField, artistLabel, artistField, lengthLabel, lengthField, albumLabel, albumField, genreLabel, genreField);
	    //Creates add button
	    Button btAdd = new Button("Add track");
	    //Sets X and Y position of add button
	    btAdd.setLayoutX(250);
	    btAdd.setLayoutY(220);
	    //Adds add button to box
	    box2.getChildren().addAll(btAdd);
	    box2.setAlignment(Pos.BOTTOM_CENTER);
	    //Creates new BorderPane
	    BorderPane pane = new BorderPane();
		//Sets box to the center of pane
	    pane.setCenter(box);
		//Sets box2 to the bottom of pane
	    pane.setBottom(box2);
	    //Setting the stage
		Scene scene = new Scene(pane, 1070, 150, Color.BEIGE);
	    //Adds a style sheet to scene
		scene.getStylesheets().add("demo.css");
	    stage.setTitle("Add Track");
	    stage.setScene(scene);
	    stage.show();
	    //Sets action for add button
	    btAdd.setOnAction(e -> {
	    	//Populating strings with text fields
	    	String track = trackField.getText();
	    	String artist = artistField.getText();
	    	String length = lengthField.getText();
	    	String album = albumField.getText();
	    	String genre = genreField.getText();
	    	//Checks if track by artist is in playlist manager
	    	if(p.searchFor(track, artist) == false) {
	    		//Creates new song using string variables
	    		Song s = new Song(track, artist, length, album, genre);
	    		//Adds song to tableView
	    		tableView.getItems().add(s);
	    		//Adds song to playlist manager
	    		p.addData(s);
	    		stage.close();
	    	} else {
	    		//Displays alert message if track by artist is already in playlist manager
	    		Alert errorAlert = new Alert(AlertType.ERROR);
	    		errorAlert.setHeaderText("Track is already in playlist");
	    		errorAlert.showAndWait();
	    	}
	    });
	}
	      
	public static void removeItems() {
		//Creates a new track object which stores the value of a selected item in tableView
		Track item = tableView.getSelectionModel().getSelectedItem();
		//Catches a nullpointer error
		if(item == null) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("No selected item to remove");
			errorAlert.showAndWait();
	    } else {
	    	//Removes item from tableView
	    	tableView.getItems().remove(item);
	    	//Removes item from playlist manager
	    	p.removeData(item.getTrack(), item.getArtist());
	    }
	}
	      
	public static void sortItems() {
		//Sorts playlist manager
		p.sortData();
		//Empties tableView
		tableView.getItems().clear();
		//Repopulates tableView
		for(int i = 0; i < p.playlist.size(); i++) {
			Track t = p.playlist.get(i);
			tableView.getItems().add(t);
		}
	}
	     
	public static void print() {
		//Prints playlist manager to the console
		System.out.println(p.toString()); 
	}
}  