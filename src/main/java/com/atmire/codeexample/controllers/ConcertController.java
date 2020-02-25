package com.atmire.codeexample.controllers;

import java.util.Date;
import java.util.List;

import com.atmire.codeexample.dao.ConcertDAO;
import com.atmire.codeexample.models.Concert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ConcertController {

    @Autowired
    private ConcertDAO concertDAO;

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
     * @param id the Id of the concert to retrieve
     * 
     * @return a Concert object or a ResponseStatusException
     */
	@GetMapping("/concert")
	public Concert getConcert(@RequestParam long id) {
        try{

        }catch (NullPointerException exeption) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: The desired concert does not exist", exeption);
        }
		return concertDAO.getConcertById(id);
    }
    
    /**
     * Create a new concert with both dynamic and static data for the example
     * 
     * @param artistName the name of the artist who will perform the concert
     * @param venueName the name of the venue where the concert will be performed
     * @param date the date at which the concert will be performed
     * 
     * @return The created Concert Object
     */
	@PostMapping("/concert/add")
	public Concert createConcert(@RequestParam String artistName, @RequestParam String venueName, @RequestParam Date date) {
		return null;
    }
    
    /**
     * Update the date of a concert
     * 
     * @param id the ID of the concert to update
     * @param newDate the new date of the concert
     * 
     * @return The updated Concert Object
     */
	@PostMapping("/concert/update")
	public Concert updateConcert(@RequestParam long id, @RequestParam Date newDate) {
		return null;
    }
    
    /**
     * Remove a concert from the list
     * 
     * @param concertId the ID of the concert to remove
     */
	@DeleteMapping("/concert")
	public Concert deleteConcert(@RequestParam(value = "id") long id) {
		return null;
	}
}