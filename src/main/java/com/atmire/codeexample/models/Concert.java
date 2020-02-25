package com.atmire.codeexample.models;

import java.util.Date;

/**
 * The data representation of a concert
 */
public class Concert {

	private final long id;
	private final Artist artist;
    private final Venue venue;
    private Date date;

	public Concert(long newId, Artist newArtist, Venue newVenue, Date newDate) {
		id = newId;
		artist = newArtist;
        venue = newVenue;
		date = newDate;
    }

	public long getId() {
		return id;
	}

	public Artist getArtist() {
		return artist;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setDate(Date newDate) {
		date = newDate;
	}

	public Date getDate() {
		return date;
	}
}