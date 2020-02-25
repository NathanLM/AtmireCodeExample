package com.atmire.codeexample.models;

/**
 * The data representation of an artist who perform a concert
 */
public class ConcertRequestBody {

	private final long id;
	private String artistName;
	private String venueName;
	private String date;

	public ConcertRequestBody(long id, String artistName, String venueName, String date) {
		this.id = id;
		this.artistName = artistName;
		this.venueName = venueName;
		this.date = date;
    }

	public long getId() {
		return id;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}