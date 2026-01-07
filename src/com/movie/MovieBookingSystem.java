package com.movie;

import java.util.ArrayList;

public class MovieBookingSystem extends BookingSystem {

    /**
     * an ArrayList than contains movie shows.
     */
    private static ArrayList<MovieShow> shows = new ArrayList<MovieShow>();

    /**
     * This is the default constructor of the class.
     */
    public MovieBookingSystem() {
        shows.clear();
        final int num1 = 500;
        final int num2 = 400;
        final int num3 = 20;

        shows.add(new MovieShow("8:30 AM", num1));
        shows.add(new MovieShow("10:00 AM", num1));
        shows.add(new MovieShow("12:30 PM", num2));
        shows.add(new MovieShow("2:00 PM", num1));
        shows.add(new MovieShow("5:30 PM", num3));

        shows.forEach(s -> System.out.println(s.getMovieShowDetails()));
    }

    /**
     * This is a method that checks if there are available tickets for a
     * particular showTime.
     *
     * @param showTime the specific showTime for which movie will display
     */
    public void checkAvailability(final String showTime) {
        MovieShow s = getShow(showTime);
        if (s != null) {
            System.out.println(s.getMovieShowDetails());
        }
    }

    /**
     * This is a method that books the specified number of tickets for a
     * showTime.
     *
     * @param showTime the specific showTime for which tickets are being booked
     * @param tickets  the number of tickets to book
     */
    public void bookTicket(final String showTime, final int tickets) {
        MovieShow s = getShow(showTime);
        if (s != null && validTickets(tickets)) {
            if (s.getCurrentTickets() >= tickets) {
                s.bookTickets(tickets);
                System.out.println(tickets + " tickets successfully booked for "
                        + showTime);
            } else {
                System.out.println(
                        "Not enough tickets available for this showtime. "
                                + "Available: " + s.getCurrentTickets());
            }
        }
    }

    /**
     * This is a method that cancels the specified number of tickets for a
     * showTime.
     *
     * @param showTime the specific showTime for which tickets are being
     *                 canceled
     * @param tickets  the number of tickets to cancel
     */
    public void cancelReservation(final String showTime, final int tickets) {
        MovieShow s = getShow(showTime);
        if (s != null && validTickets(tickets)) {
            if (s.getBookedTickets() >= tickets) {
                s.cancelTickets(tickets);
                System.out.println(tickets
                        + " tickets successfully canceled for " + showTime);
            } else {
                System.out.println("Invalid operation "
                        + "(Attempt to cancel more tickets than booked).");
            }
        }
    }

    /**
     * This is a helper function that retrieves a MovieShow object given a
     * specific showTime.
     *
     * @param showTime the time used to identify and refer to a specific show
     *
     * @return the MovieShow object associated with the given time
     */
    public MovieShow getShow(final String showTime) {
        MovieShow selectedShow = null;
        String timeRegex = "^(1[0-2]|[1-9]):[0-5][0-9] (AM|PM)$";

        if (showTime == null || !showTime.matches(timeRegex)) {
            System.out.println("Invalid time format: '" + showTime
                    + "'. Please use 'H:MM AM/PM' (e.g., 8:30 AM, 12:30 PM).");
        } else {
            String targetTime = showTime.trim().toUpperCase();
            selectedShow = shows.stream()
                    .filter(s -> s.getShowTime().equalsIgnoreCase(targetTime))
                    .findFirst()
                    .orElse(null);

            if (selectedShow == null) {
                System.out.println("No show for the show time specified.");
            }
        }

        return selectedShow;
    }

    /**
     * This is a helper function that checks if number of tickets is valid or
     * not.
     *
     * @param tickets the time used to identify and refer to a specific show
     *
     * @return boolean whether it is valid or not
     */
    private boolean validTickets(final int tickets) {
        if (tickets <= 0) {
            System.out.println(
                    "Invalid ticket value. Must be greater than zero.");
            return false;
        }
        return true;
    }

    /**
     * This is the main method.
     *
     * @param args
     */
    public static void main(final String[] args) {
        MovieBookingSystem mbs = new MovieBookingSystem();
        final int num4 = 200;
        final int num5 = 300;
        final int num6 = 100;

        mbs.checkAvailability("8:30 O:clock");
        mbs.checkAvailability("08:30 AM");
        mbs.checkAvailability("12:30 PM");
        mbs.bookTicket("12:30 PM", num4);
        mbs.checkAvailability("12:30 PM");
        mbs.bookTicket("12:30 PM", num4);
        mbs.bookTicket("12:30 PM", num5);
        mbs.cancelReservation("12:30 PM", num5);
        mbs.checkAvailability("12:30 PM");
        mbs.checkAvailability("8:30 AM");
        mbs.bookTicket("8:30 AM", num6);
        mbs.checkAvailability("8:30 AM");
        mbs.cancelReservation("8:30 AM", num6);
        mbs.checkAvailability("8:30 AM");
    }
}
