package com.leap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LeapTest {

    @Test
    void testIsLeapYear_WhenYearIsDivisibleBy400_ShouldReturnTrue() {
        LeapYearChecker lc = new LeapYearChecker(2000);
        boolean result = lc.isLeapYear();
        assertEquals(true, result, () -> "2000 is a leap year");
    }

    @Test
    void testIsLeapYear_WhenYearIsDivisibleBy100ButNot400_ShouldReturnFalse() {
        LeapYearChecker lc = new LeapYearChecker(2100);
        boolean result = lc.isLeapYear();
        assertEquals(false, result, () -> "2100 is not a leap year");
    }

    @Test
    void testIsLeapYear_WhenYearIsDivisibleBy4ButNot100_ShouldReturnTrue() {
        LeapYearChecker lc = new LeapYearChecker(2004);
        boolean result = lc.isLeapYear();
        assertEquals(true, result, () -> "2004 is a leap year");
    }

    @Test
    void testYear_WhenYearIsNotDivisibleBy4_ShouldReturnFalse() {
        LeapYearChecker lc = new LeapYearChecker(2003);
        boolean result = lc.isLeapYear();
        assertEquals(false, result, () -> "2003 is not a leap year");
    }

    @Test
    void testIsLeapYear_RandomLeapYear_ShouldReturnTrue() {
        LeapYearChecker lc = new LeapYearChecker(2024);
        boolean result = lc.isLeapYear();
        assertEquals(true, result, () -> "2024 is a leap year");
    }

    @Test
    void testIsLeapYear_RandomNonLeapYear_ShouldReturnFalse() {
        LeapYearChecker lc = new LeapYearChecker(2022);
        boolean result = lc.isLeapYear();
        assertEquals(false, result, () -> "2022 is not a leap year");
    }

    @Test
    void testMainMethod() {
        Leap.main(null);
        int result = 1;
        assertEquals(1, result, () -> "main returns void");
    }
}
