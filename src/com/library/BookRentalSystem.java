package com.library;

import java.util.ArrayList;

public final class BookRentalSystem {

    private BookRentalSystem() {
        super();
    }

    /**
     * @param args
     */
    private static ArrayList<Book> library = new ArrayList<Book>();

    /**
     * @addBook adds a book to the library;
     * @param book that is added to the library;
     */
    public static void addBook(final Book book) {
        library.add(book);
    }

    /**
     * @rentBook rents a book to the library;
     * @param index is the index of a book in a library;
     */
    public static void rentBook(final int index) {

        Book b = library.get(index);
        b.rent();
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final int year1 = 1954;
        final int year2 = 1960;
        final int year3 = 2000;
        final int year4 = 1997;

        addBook(new FictionBook("The Lord of the Rings", "J.R.R. Tolkien",
                year1));
        addBook(new FictionBook("To Kill a Mockingbird", "Harper Lee", year2));
        addBook(new NonFictionBook("The Tipping Point", "M. Gladwell", year3));
        addBook(new NonFictionBook("Guns, Germs, and Steel", "Jared Diamond ",
                year4));
        displayBooks();
        rentBook(0);
        rentBook(2);
        System.out.println("Books Rented:");
        displayRentedBooks();
    }

    /**
     * @clearLibrary
     */
    public static void clearLibrary() {
        library.clear();
    }

    /**
     * @getLibrarySize
     * @return size
     */
    public static int getLibrarySize() {
        int size = library.size();
        return size;
    }

    /**
     * @getLibrary
     * @return library
     */
    public static ArrayList<Book> getLibrary() {
        return library;
    }

    /**
     * @displayBooks
     */
    public static void displayBooks() {
        for (Book b : library) {
            System.out.println(b.getBookDetails());
        }
    }

    /**
     * @displayRentedBooks
     */
    public static void displayRentedBooks() {
        for (Book b : library) {
            if (b.isRented()) {
                System.out.println(b.getBookDetails());
            }
        }
    }
}
