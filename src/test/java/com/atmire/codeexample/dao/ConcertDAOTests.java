package com.atmire.codeexample.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        return concertDAO.createNewConcert("John", "Sidewalk theater", new Date());
    }

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
            Concert actualConcert = concertDAO.getConcertById(0);
            assertEquals(expectedConcert, actualConcert, "Wrong concert retrieved");
        }catch(NullPointerException exception){
            assertTrue(false, "NullPointer exception raised");
        }
    }

}
