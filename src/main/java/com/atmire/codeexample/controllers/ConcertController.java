package com.atmire.codeexample.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.atmire.codeexample.dao.ConcertDAO;
import com.atmire.codeexample.models.Concert;
import com.atmire.codeexample.models.ConcertRequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ConcertController {

    private final String DATE_FORMAT = "dd-MM-yyyy hh:mm a";

    @Autowired
    private ConcertDAO concertDAO;

    private Date extractDateFromString(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT, Locale.FRANCE);
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));
        return formatter.parse(date);
    }

    /**
     * Return the list of all concerts created
     * 
     * @return a List of Concert Objects containing all the concerts 
     */
	@GetMapping("/concerts")
	public List<Concert> getConcertList() {
		return concertDAO.getConcertList();
    }

    /**
     * Return a Concert object identified by the given id 
     * 
     * @param body the body of the request containing;
     *      - "id" the ID of the concert to remove
     * 
     * @return a Concert object or a ResponseStatusException
     */
	@GetMapping("/concert")
	public Concert getConcert(@RequestBody ConcertRequestBody body) {
        try{
            return concertDAO.getConcertById(body.getId());
        }catch (NullPointerException exeption) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: The desired concert does not exist", exeption);
        }
    }
    
    /**
     * Create a new concert with both dynamic and static data for the example
     * 
     * @param body body the body of the request containing:
     *      - "artistName" the name of the artist who will perform the concert
     *      - "venueName" the name of the venue where the concert will be performed
     *      - "date" the date at which the concert will be performed (Use this format "dd-MM-yyyy hh:mm a")
     * 
     * @return The created Concert Object
     */
	@PostMapping("/concert/add")
	public Concert createConcert(@RequestBody ConcertRequestBody body) {
        try{
            return concertDAO.createNewConcert(body.getArtistName(), body.getVenueName(), extractDateFromString(body.getDate()));
        } catch (ParseException exeption) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error: The given date string does not follow the correct format: " + DATE_FORMAT, exeption);
        }
    }
    
    /**
     * Update the date of a concert
     * 
     * @param body the body of the request containing: 
     *          - "id" the ID of the concert to update
     *          - "date" the new date of the concert (Use this format "dd-MM-yyyy hh:mm a")
     * 
     * @return The updated Concert Object
     */
	@PostMapping("/concert/update")
	public Concert updateConcert(@RequestBody ConcertRequestBody body) {
        try{
            return concertDAO.updateConcert(body.getId(), extractDateFromString(body.getDate()));
        }catch (NullPointerException exeption) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: The desired concert does not exist", exeption);
        } catch (ParseException exeption) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error: The given date string does not follow the correct format: " + DATE_FORMAT, exeption);
        }
    }
    
    /**
     * Remove a concert from the list
     * 
     * @param body the body of the request containing;
     *      - "id" the ID of the concert to remove
     */
	@DeleteMapping("/concert")
	public void deleteConcert(@RequestBody ConcertRequestBody body) {
		concertDAO.removeConcert(body.getId());
	}
}