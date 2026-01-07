package com.movie;

public abstract class BookingSystem {

    /**
     * This is an abstract method that checks if there are available tickets for
     * a particular showTime.
     *
     * @param showTime the specific showTime for which movie will display
     */
    public abstract void checkAvailability(String showTime);

    /**
     * This is an abstract method that books the specified number of tickets for
     * a showTime.
     *
     * @param showTime the specific showTime for which tickets are being booked
     * @param tickets  the number of tickets to book
     */
    public abstract void bookTicket(String showTime, int tickets);

    /**
     * This is an abstract method that cancels the specified number of tickets
     * for a showTime.
     *
     * @param showTime the specific showTime for which tickets are being
     *                 canceled
     * @param tickets  the number of tickets to cancel
     */
    public abstract void cancelReservation(String showTime, int tickets);
}
