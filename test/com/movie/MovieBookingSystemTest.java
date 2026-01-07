package com.movie;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.Book;
import com.library.BookRentalSystem;
import com.library.FictionBook;

class MovieBookingSystemTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    MovieBookingSystem mbs = new MovieBookingSystem();

    @BeforeEach
    void setup() {
        mbs = new MovieBookingSystem();
        
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }
    
    @Test
    void testCheckAvailability_WhenShowTimeIsValid_ShouldReturnTrue() {
        String showTime = "12:30 PM";
        mbs.checkAvailability(showTime);
        String expectedOutput = "Movie Show Time: 12:30 PM || Movie Tickets left: " + mbs.getShow(showTime).getCurrentTickets()
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }

    @Test
    void testCheckAvailability_WhenShowTimeIsInvalid_ShouldReturnTrue() {
        String showTime = "8:30 o'clock";
        mbs.checkAvailability(showTime);
        String expectedOutput = "Invalid time format: '8:30 o'clock'. Please use 'H:MM AM/PM' (e.g., 8:30 AM, 12:30 PM)."
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }
    
    @Test
    void testCheckAvailability_WhenShowTimeIsValidButNoShowAvailable_ShouldReturnTrue() {
        String showTime = "8:30 PM";
        mbs.checkAvailability(showTime);
        String expectedOutput = "No show for the show time specified."
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }
    
    @Test
    void testBookTicket_WhenShowTimeAndTicketsIsValid_ShouldReturnTrue() {
        String showTime = "12:30 PM";
        int tickets = 100;
        mbs.bookTicket(showTime, tickets);
        int expectedOutput = mbs.getShow(showTime).getBookedTickets();
        assertEquals(expectedOutput, 100, () -> "The number tickets booked should be " + tickets);
    }
    
    @Test
    void testBookTicket_WhenShowTimeIsValidAndTicketsIsInvalid_ShouldReturnTrue() {
        String showTime = "12:30 PM";
        int tickets = 1000;
        mbs.bookTicket(showTime, tickets);
        String expectedOutput = "Not enough tickets available for this showtime. "
                + "Available: " + mbs.getShow(showTime).getCurrentTickets()
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }
    
    @Test
    void testCancelTicket_WhenShowTimeAndTicketsIsValid_ShouldReturnTrue() {
        String showTime = "12:30 PM";
        mbs.bookTicket(showTime, 100);
        outContent.reset();

        int tickets = 100;
        mbs.cancelReservation(showTime, tickets);
        int expectedOutput = mbs.getShow(showTime).getTotalTickets();
        int actualOutput = mbs.getShow(showTime).getCurrentTickets();
        assertEquals(expectedOutput, actualOutput, () -> "The number tickets available should be " + actualOutput);
    }
    
    @Test
    void testCancelTicket_WhenShowTimeIsValidAndTicketsCancelledIsGreaterThanBookedTickets_ShouldReturnTrue() {
        String showTime = "12:30 PM";
        mbs.bookTicket(showTime, 100);
        outContent.reset();

        int tickets = 200;
        mbs.cancelReservation(showTime, tickets);
        String expectedOutput = "Invalid operation "
                + "(Attempt to cancel more tickets than booked)."
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }

    @Test
    void testCancelTicket_WhenShowTimeIsNull_ShouldReturnTrue() {
        String showTime = null;
        int tickets = 100;
        mbs.bookTicket(showTime, tickets);
        outContent.reset();

        mbs.cancelReservation(showTime, tickets);
        String expectedOutput = "Invalid time format: '" + showTime + "'. "
                + "Please use 'H:MM AM/PM' (e.g., 8:30 AM, 12:30 PM)."
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }
    
    @Test
    void testBookTicket_WhenNumberofTicketsIsInvalid_ShouldReturnTrue() {
        mbs.cancelReservation("12:30 PM", -10);
        String expectedOutput = "Invalid ticket value. Must be greater than zero."
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }

    @Test
    void testCancelReservation_WhenNumberofTicketsIsInvalid_ShouldReturnTrue() {
        String showTime = "12:30 PM";
        mbs.bookTicket(showTime, -2);

        String expectedOutput = "Invalid ticket value. Must be greater than zero."
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }
    
    @Test
    void testMainMethod() {
        MovieBookingSystem.main(null);
        int result = 1;
        assertEquals(1, result, () -> "main returns void");
    }
}
