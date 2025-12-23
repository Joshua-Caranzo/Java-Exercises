package com.library;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookRentalSystemTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        BookRentalSystem.clearLibrary();

        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testBookCreation_WhenBookIsNotNullAndNotYetRented_ShouldReturnNotNullandNotRented() {
        Book b = new FictionBook("The Lord of the Rings", "J.R.R. Tolkien",
                1954);
        assertNotNull(b, "The book provided by the source should not be null.");
        assertFalse(b.isRented(),
                "The initial book should not be marked as rented.");
    }

    @Test
    void testRent_WhenBookIsRented_ShouldReturnTrue() {
        Book b = new FictionBook("The Lord of the Rings", "J.R.R. Tolkien",
                1954);
        assertFalse(b.isRented(),
                "The initial book should not be marked as rented.");
        b.rent();
        assertTrue(b.isRented(), "The book is not yet marked as rented.");
    }

    @Test
    void testAddBook_WhenNonFictionAndFictionBookIsAdded_CompareSize_ShouldReturnTrue() {
        BookRentalSystem.addBook(new FictionBook("The Lord of the Rings",
                "J.R.R. Tolkien", 1954));

        BookRentalSystem.addBook(
                new NonFictionBook("The Tipping Point", "M. Gladwell", 2000));
        int size = BookRentalSystem.getLibrarySize();

        assertEquals(size, 2,
                () -> "The size of the library is not equal to 1");
    }

    @Test
    void testRentBook_WhenNonFictionAndFictionBookIsAddedAndOneisRented_ShouldReturnTrueForRentedAndFalseForNotRented() {
        BookRentalSystem.addBook(new FictionBook("The Lord of the Rings",
                "J.R.R. Tolkien", 1954));

        BookRentalSystem.addBook(new NonFictionBook("The Lord of the Rings",
                "M. Gladwell", 1954));

        BookRentalSystem.rentBook(0);

        ArrayList<Book> libraryTest = BookRentalSystem.getLibrary();

        assertEquals(libraryTest.get(0).isRented(), true,
                () -> "Book 1 should return true because it is rented");
        assertEquals(libraryTest.get(1).isRented(), false,
                () -> "Book 2 should return false because it is not rented");
    }

    @Test
    void testRentBook_WhenBookRentedIndexIsInvalid_ShouldReturnIndexOutofBounds() {
        BookRentalSystem.addBook(new FictionBook("The Lord of the Rings",
                "J.R.R. Tolkien", 1954));

        BookRentalSystem.addBook(new NonFictionBook("The Lord of the Rings",
                "M. Gladwell", 1954));

        String expectedExceptionMessage = "Index 3 out of bounds for length 2";

        IndexOutOfBoundsException actualException = assertThrows(
                IndexOutOfBoundsException.class, () -> {
                    BookRentalSystem.rentBook(3);
                },
                "Renting book index 3 should have thrown Index Array out of Bounds.");

        assertEquals(expectedExceptionMessage, actualException.getMessage(),
                () -> "Unexpected Exception Message");
    }

    @Test
    void testDisplayBooks_WhenNonFictionAndFictionBookIsAdded_CompareToExpectedOutput_ShouldReturnTrue() {
        BookRentalSystem.addBook(new FictionBook("The Lord of the Rings",
                "J.R.R. Tolkien", 1954));

        BookRentalSystem.addBook(
                new NonFictionBook("The Tipping Point", "M. Gladwell", 1954));

        BookRentalSystem.displayBooks();
        String expectedOutput = "The Lord of the Rings J.R.R. Tolkien 1954"
                + System.lineSeparator()
                + "The Tipping Point M. Gladwell 1954"
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }

    @Test
    void testDisplayRentedBooks_WhenNonFictionAndFictionBookIsAdded_ShouldReturnTrue() {
        BookRentalSystem.addBook(new FictionBook("The Lord of the Rings",
                "J.R.R. Tolkien", 1954));

        BookRentalSystem.addBook(
                new NonFictionBook("The Tipping Point", "M. Gladwell", 1954));

        BookRentalSystem.rentBook(0);

        BookRentalSystem.displayRentedBooks();
        String expectedOutput = "The Lord of the Rings J.R.R. Tolkien 1954"
                + System.lineSeparator();
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, () -> actualOutput);
    }

    @Test
    void testMainMethod() {
        BookRentalSystem.main(null);
        int result = 1;
        assertEquals(1, result, () -> "main returns void");
    }
}
