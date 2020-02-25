package com.atmire.codeexample.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;

import com.atmire.codeexample.models.Concert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConcertDAOTests {

    @Autowired
    private ConcertDAO concertDAO;

    @BeforeEach
    public void clearConcertList(){
        concertDAO.clearConcertList();
    }

    private Concert createConcert(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        return concertDAO.createNewConcert("John", "Sidewalk theater", calendar.getTime());
    }
    /**
     * createNewConcert
     */

	@Test
    public void createNewConcert_givenCorrectData_shouldAddItToTheList(){        
        Concert newConcert = createConcert();

        assertEquals(1, concertDAO.getConcertList().size(), "The concert was either not added to the list or added too many times");
        assertEquals(newConcert, concertDAO.getConcertList().get(0), "The concert was not added to the list");
    }

	@Test
    public void createNewConcert_givenTheSameDataMultipleTimes_shouldAddItToTheListWithDifferentIds(){
        int numberOfConcertsToAdd = 6;
        
        for(int index = 0 ; index < numberOfConcertsToAdd ; index++){
            createConcert();
        }

        assertEquals(numberOfConcertsToAdd, concertDAO.getConcertList().size(), "The concerts were not added to the list a correct number of times");
        assertNotEquals(concertDAO.getConcertList().get(0).getId(), concertDAO.getConcertList().get(2).getId(), "The concerts were not added with different IDs");
    }

    /**
     * getConcertById
     */

	@Test
    public void getConcertById_givenConcertDoesNotExists_shouldThrowNullPointerException(){
        try{
            concertDAO.getConcertById(999);
            assertTrue(false, "NullPointerException was not raised as expected");
        }catch(NullPointerException exception){
            assertTrue(true);
        }
    }

	@Test
    public void getConcertById_givenConcertDoesExists_shouldReturnConcert(){
        Concert expectedConcert = createConcert();
        
        try{
            long concertId = expectedConcert.getId();
            Concert actualConcert = concertDAO.getConcertById(concertId);
            assertEquals(expectedConcert, actualConcert, "Wrong concert retrieved");
        }catch(NullPointerException exception){
            assertTrue(false, "NullPointerException was exception raised as expected");
        }
    }

    /**
     * RemoveConcert
     */

	@Test
    public void removeConcert_givenConcertDoesExists_shouldRemoveItFromTheList(){        
        try{
            long concertId = createConcert().getId();
            concertDAO.removeConcert(concertId);
            concertDAO.getConcertById(concertId);

            assertTrue(false, "NullPointerException was not raised as expected");
        }catch(NullPointerException exception){
            assertTrue(true);
        }
    }

    /**
     * updateConcert
     */

	@Test
    public void updateConcert_givenConcertDoesExists_shouldModifyTheItemInTheList(){
        Concert concertBeforeUpdate = createConcert();
        Concert concertAfterUpdate = concertDAO.updateConcert(concertBeforeUpdate.getId(), new Date(99999));

        assertNotEquals(concertBeforeUpdate.getDate().getTime(), concertAfterUpdate.getDate().getTime(), "The date has not been updated");
    }

	@Test
    public void updateConcert_givenConcertDoesNotExists_shouldThrowNullPointerException(){
        try{
            concertDAO.updateConcert(99999, new Date());
            assertTrue(false, "NullPointerException was not raised as expected");
        }catch(NullPointerException exception){
            assertTrue(true);
        }
    }
    

}
