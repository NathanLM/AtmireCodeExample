package com.atmire.codeexample.models;

/**
 * The data representation of a venue where concerts take place
 */
public class Venue {

	private final long id;
	private String name;
    private String address;

	public Venue(long newId, String newName, String newAddress) {
		id = newId;
		name = newName;
        address = newAddress;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String newAddress) {
		address = newAddress;
	}
}