package com.movie;

public class MovieShow {
    /**
     * @param showTime for time of the show.
     */
    private String showTime;

    /**
     * @param totalTickets number of total tickets initially.
     */
    private int totalTickets;

    /**
     * @param currentTickets number of current tickets.
     */
    private int currentTickets;

    /**
     * @param time    for time of the show
     * @param tickets number of total tickets initially
     */
    public MovieShow(final String time, final int tickets) {
        this.showTime = time;
        this.totalTickets = tickets;
        this.currentTickets = tickets;
    }

    /**
     * This is a method that returns movie details.
     *
     * @return returns String value of the movie details
     */
    public String getMovieShowDetails() {
        return ("Movie Show Time: " + this.showTime + " || Movie Tickets left: "
                + this.currentTickets);
    }

    /**
     * This is a method that returns showTime of a movie.
     *
     * @return string value of showTime
     */
    public String getShowTime() {
        return showTime;
    }

    /**
     * This is a method that returns total number of tickets initially.
     *
     * @return integer value of totalTickets
     */
    public int getTotalTickets() {
        return totalTickets;
    }

    /**
     * This is a method that returns total number of tickets currently.
     *
     * @return integer value of currentTickets
     */
    public int getCurrentTickets() {
        return currentTickets;
    }

    /**
     * This is a method that returns total number of booked tickets.
     *
     * @return integer value of bookedTickets
     */
    public int getBookedTickets() {
        return totalTickets - currentTickets;
    }

    /**
     * This is a method that books a ticket.
     *
     * @param tickets are tickets to be booked
     */
    public void bookTickets(final int tickets) {
        currentTickets -= tickets;
    }

    /**
     * This is a method that cancels a ticket.
     *
     * @param tickets are tickets to be canceled
     */
    public void cancelTickets(final int tickets) {
        currentTickets += tickets;
    }
}
