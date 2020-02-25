package com.atmire.codeexample.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;

import com.atmire.codeexample.models.Concert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConcertDAOTests {

    @Autowired
    private ConcertDAO concertDAO;

    private Concert createConcert(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        return concertDAO.createNewConcert("John", "Sidewalk theater", calendar.getTime());
    }

    /**
     * getConcertById
     */

	@Test
    public void getConcertById_givenConcertDoesNotExists_shouldThrowNullPointerException(){
        try{
            concertDAO.getConcertById(999);
            assertTrue(false, "NullPointerException not raised");
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
            assertTrue(false, "NullPointer exception raised");
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

            assertTrue(false, "NullPointerException not raised");
        }catch(NullPointerException exception){
            assertTrue(true);
        }
    }

    /**
     * updateConcert
     */

	@Test
    public void updateConcert_givenConcertDoesExists_shouldModifyTheItemInTheList(){
        Date expectedDate = new Date(99999);
        long concertId = createConcert().getId();
        Concert concertAfterUpdate = concertDAO.updateConcert(concertId, expectedDate);

        assertNotEquals(expectedDate.getTime(), concertAfterUpdate.getDate().getTime(), "The date has not been updated");
    }
    

}
