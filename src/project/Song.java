//
//Daniel Elkins
//May 6, 2021
//Final Exam
//

package project;

public class Song extends Track {
	
	public Song(String track, String artist, String length, String album, String genre) {
		//Sets the values of String variables inherited from Track
		setTrack(track);
		setArtist(artist);
		setLength(length);
		setAlbum(album);
		setGenre(genre);
	}	
}
