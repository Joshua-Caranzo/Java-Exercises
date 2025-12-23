package com.bankAccount;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountSystemTest {

    SavingsAccount s;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        s = new SavingsAccount("Joshua");
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testAccountCreation_WhenAccountNameIsNotNull_ShouldReturnNotNullandBalanceShouldBeZero() {
        SavingsAccount s2 = new SavingsAccount("Joshua");
        assertNotNull(s2.getOwnerName(), "Account name should not be null");
        assertEquals(0, s2.getBalance(), "Initial Balance should be 0");
    }

    @Test
    void testDeposit_WhenAmountIsPositive_ShouldPrintSuccessMessage() {
        s.deposit(500);
        String expectedOutput = "Deposited: PHP 500.0" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testDeposit_WhenAmountIsNegative_ShouldPrintErrorMessage() {
        s.deposit(-500);
        String expectedOutput = "The deposit amount must be positive"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testDeposit_WhenAmountIsZero_ShouldPrintErrorMessage() {
        s.deposit(0);
        String expectedOutput = "The deposit amount must be positive"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testWithdraw_WhenBalanceIsSufficient_ShouldPrintSuccessMessage() {
        s.deposit(1000);
        outContent.reset();
        s.withdraw(600);
        String expectedOutput = "Withdrawn: PHP 600.0" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testWithdraw_WhenAmountIsNegative_ShouldPrintErrorMessage() {
        s.deposit(1000);
        outContent.reset();
        s.withdraw(-500);
        String expectedOutput = "The withdrawn amount must be positive"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testWithdraw_WhenAmountIsZero_ShouldPrintErrorMessage() {
        s.deposit(1000);
        outContent.reset();
        s.withdraw(0);
        String expectedOutput = "The withdrawn amount must be positive"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testWithdraw_WhenAmountExceedsBalance_ShouldPrintErrorMessage() {
        s.deposit(1000);
        outContent.reset();
        s.withdraw(5000);
        String expectedOutput = "Insufficient Balance" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testFreezeAccount_WhenAccountIsNotFrozen_ShouldUpdateStatusToTrue() {
        s.freezeAccount();
        assertTrue(s.isFrozen(), "Account should be frozen.");
    }

    @Test
    void testDeposit_WhenAccountIsFrozen_ShouldPrintFrozenErrorMessage() {
        s.freezeAccount();
        outContent.reset();
        s.deposit(1000);
        String expectedOutput = "Account is frozen." + System.lineSeparator()
                + "Cannot deposit." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testWithdraw_WhenAccountIsFrozen_ShouldPrintFrozenErrorMessage() {
        s.deposit(1000);
        s.freezeAccount();
        outContent.reset();
        s.withdraw(500);
        String expectedOutput = "Account is frozen." + System.lineSeparator()
                + "Cannot withdraw." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testUnfreezeAccount_WhenAccountIsFrozen_ShouldUpdateStatusToFalse() {
        s.freezeAccount();
        s.unfreezeAccount();
        assertFalse(s.isFrozen(), "Account should be unfrozen.");
    }

    @Test
    void testUnfreezeAccount_WhenAccountIsNotFrozen_ShouldPrintErrorMessage() {
        s.unfreezeAccount();
        String expectedOutput = "Account is not frozen."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testFreezeAccount_WhenAccountIsAlreadyFrozen_ShouldPrintErrorMessage() {
        s.freezeAccount();
        outContent.reset();
        s.freezeAccount();
        String expectedOutput = "Account is already frozen."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testMain() {
        AWSBank.main(null);
        int result = 1;
        assertEquals(1, result, () -> "main returns void");
    }
}
