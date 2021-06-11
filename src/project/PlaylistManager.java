package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class PlaylistManager implements DataStructureWrapper<Track>{
	//Identification number for each track
	private int trackID = 0;
	//Stores trackID and a track object
	LinkedHashMap<Integer, Track> playlist = new LinkedHashMap<Integer, Track>();
	
	//Adds a track object to playlist manager and increments trackID
	@Override
	public void addData(Track data) {
		playlist.put(trackID, data);
		trackID++;
	}
		
	//Removes a track object from playlist manager
	@Override
	public void removeData(String track, String artist) {
		//ArrayList that stores track objects
		ArrayList<Track> titles = new ArrayList<>();
		for(int i = 0; i < playlist.size(); i++) {
			//Creates a track object that stores another track object in playlist manager
			Track t = playlist.get(i);
			//Checks if track is not the track object to be removed from playlist manager
			if(!t.getArtist().equals(artist) && !t.getTrack().equals(track)) {
				//adds track to titles
				titles.add(t);
			}
		}
		//Clears playlist manager
		playlist.clear();
		//Resets trackID
		trackID = 0;
		//Repopulates playlist manager
		for(int i = 0; i <titles.size(); i++) { 
			playlist.put(trackID, titles.get(i));
			trackID++;
		}
	}
	//Sorts the track objects in playlist manager 
	@Override
	public void sortData() {
		//Creates ArrayList that stores all the values in playlist manager
		ArrayList<Track> titles = new ArrayList<>(playlist.values());
		//Empties playlist manager
		playlist.clear();
		//Sorts ArrayList titles
		Collections.sort(titles);
		//Resets trackID
		trackID = 0;
		//Repopulates playlist manager in sorted order
		for(int i = 0; i <titles.size(); i++) { 
			playlist.put(trackID, titles.get(i));
			trackID++;
		}
	}

	//Searchs for a track object in playlist manager
	@Override
	public boolean searchFor(String track, String artist) {
		for(int i = 0; i < playlist.size(); i++) {
			Track t = playlist.get(i);
			//Checks for the specified track object 
			if(t.getTrack().equals(track) && t.getArtist().equals(artist)) { 
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String s =  "";
		for(int i = 0; i < playlist.size(); i++) { 
			Track t = playlist.get(i);
			String artist = t.getArtist();
			String track = t.getTrack();
			String length = t.getLength();
			String album = t.getAlbum();
			String genre = t.getGenre();
			s += i + "." + "\nArtist: " + artist + "\nTrack: " + track + "\nLength: " + length
					+ "\nAlbum: " + album + "\nGenre: " + genre + "\n\n";
		}
		return s;
	}
}