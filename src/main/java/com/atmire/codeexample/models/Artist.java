package com.atmire.codeexample.models;

/**
 * The data representation of an artist who perform a concert
 */
public class Artist {

	private final long id;
	private String name;

	public Artist(long newId, String newName) {
		id = newId;
		name = newName;
    }

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}
}