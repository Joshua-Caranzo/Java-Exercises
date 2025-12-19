package com.library;

public class Book {
    /**
     * @param title for fiction book title
     */

    private String title;
    /**
     * @param author for fiction book author
     */
    private String author;

    /**
     * @param yearPublished for fiction book year published
     */
    private int yearPublished;

    /**
     * @param isRented for fiction book is rented
     */
    private boolean isRented;

    /**
     * @param bookTitle         for fiction book title
     * @param bookAuthor        for fiction book author
     * @param bookYearPublished for fiction book Year Published
     */
    public Book(final String bookTitle, final String bookAuthor,
            final int bookYearPublished) {
        this.title = bookTitle;
        this.author = bookAuthor;
        this.yearPublished = bookYearPublished;
        isRented = false;
    }

    /**
     * @isRented gets rented field for the book
     * @return isRented
     */
    public boolean isRented() {
        return isRented;
    }

    /**
     * @getBookDetails function for getting the book details of a book
     * @return string for book details
     */
    public String getBookDetails() {
        return (this.title + " " + this.author + " " + this.yearPublished);
    }

    /**
     * @rent flags rent boolean to true.
     */
    public void rent() {
        isRented = true;
    }
}
