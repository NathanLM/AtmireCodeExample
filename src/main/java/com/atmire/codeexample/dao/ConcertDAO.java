package com.atmire.codeexample.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

import com.atmire.codeexample.models.Artist;
import com.atmire.codeexample.models.Concert;
import com.atmire.codeexample.models.Venue;

@Repository
public class ConcertDAO{

    // Not a graceful solution but it's here to mock a database
    private ArrayList<Concert> concertList;

    public ConcertDAO(){
        concertList = new ArrayList<Concert>();
    }

    /**
     * Create a new concert and add it to the list
     * 
     * @param newArtistName the name of the artist who will perform the concert
     * @param newVenueName the name of the venue where the concert will be performed
     * @param newDate the date at which the concert will be performed
     * 
     * @return the added Concert Object
     */
    public Concert createNewConcert(String newArtistName, String newVenueName, Date newDate){
        Concert newConcert = createConcertInstance(newArtistName, newVenueName, newDate);
        concertList.add(newConcert);
        return newConcert;
    }

    /**
     * Create a new Concert with both dynamic and static data
     * 
     * @param newArtistName the name of the artist who will perform the concert
     * @param newVenueName the name of the venue where the concert will be performed
     * @param newDate the date at which the concert will be performed
     * 
     * @return the created Concert Object
     */
    private Concert createConcertInstance(String newArtistName, String newVenueName, Date newDate){
        long id = concertList.size(); // Usually covered by the database.
        Artist artist = new Artist(id, newArtistName);
        Venue venue = new Venue(id, newVenueName, "Gaston Geenslaan 14, 3001 Leuven, Belgium");
        return new Concert(id, artist, venue, newDate);
    }

    /**
     * Update the date of a concert
     * 
     * @param concertId the ID of the concert to update
     * @param newDate the new date of the concert
     */
    public void updateConcert(long concertId, Date newDate){
        //TODO : To implement
    }

    /**
     * Remove a concert from the list
     * 
     * @param concertId the ID of the concert to remove
     */
    public void removeConcert(long concertId){
        //TODO : To implement
    }

    /**
     * Return the list of all concerts created
     * 
     * @return an ArrayList containing all the concerts 
     */
    public ArrayList<Concert> getConcertList(){
        return concertList;
    }

    /**
     * Return a concert corresponding to the ID from the list or throw an exception if none exist 
     * 
     * @param concertId the ID of the concert to retrieve
     * 
     * @return a Concert object
     */
    public Concert getConcertById(long concertId){
        //TODO: To implement
        return null;
    }
}
