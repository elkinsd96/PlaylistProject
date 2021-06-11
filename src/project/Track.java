package project;

public class Track implements Comparable<Track> {
	private String track;
	private String artist;
	private String length;
	private String album;
	private String genre;
	
	public Track() {
	}

	public Track(String track, String artist, String length) {
		this.track = track;
		this.artist = artist;
		this.length = length;
	}
	
	public void setTrack(String track) {
		this.track = track;
	}
	
	public String getTrack() {
		return track;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getArtist() {
		return artist;
	}

	public void setLength(String length) {
		this.length = length;
	}
	
	public String getLength() {
		return length;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getAlbum() {
		return album;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public int compareTo(Track t) {
		return this.getArtist().compareTo(t.getArtist());
	}
}
