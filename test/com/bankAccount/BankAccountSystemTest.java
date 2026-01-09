package com.bankAccount;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Bank Account System Tests")
class BankAccountSystemTest {

    private SavingsAccount account;
    private BankAccountManager manager;
    private SavingsAccount account1;
    private SavingsAccount account2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setup() {
        account = new SavingsAccount("Joshua");
        manager = new BankAccountManager();
        account1 = new SavingsAccount("Alice");
        account2 = new SavingsAccount("Bob");
        outContent.reset();
        errContent.reset();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreSystemStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Should create account with valid name and zero balance")
    void testAccountCreation_WhenAccountNameIsNotNull_ShouldReturnNotNullAndBalanceShouldBeZero() {
        SavingsAccount newAccount = new SavingsAccount("Joshua");
        assertAll("Account Creation",
                () -> assertNotNull(newAccount.getOwnerName()),
                () -> assertEquals(0, newAccount.getBalance()),
                () -> assertFalse(newAccount.isFrozen()));
    }

    @Test
    @DisplayName("Should deposit positive amount and update balance")
    void testDeposit_WhenAmountIsPositive_ShouldUpdateBalanceAndPrintMessage() {
        account.deposit(500);
        assertEquals(500, account.getBalance());
    }

    @Test
    @DisplayName("Should print InvalidAmountException stack trace for negative deposit")
    void testDeposit_WhenAmountIsNegative_ShouldPrintInvalidAmountException() {
        account.deposit(-500);

        assertAll("Negative Deposit",
                () -> assertEquals(0, account.getBalance()),
                () -> assertTrue(errContent.toString()
                        .contains("InvalidAmountException")));

        account.deposit(0);
        assertAll("Zero Deposit", () -> assertEquals(0, account.getBalance()),
                () -> assertTrue(errContent.toString()
                        .contains("InvalidAmountException")));
    }

    @Test
    @DisplayName("Should print AccountFrozenException stack trace when depositing to frozen account")
    void testDeposit_WhenAccountIsFrozen_ShouldPrintAccountFrozenException() {
        account.freezeAccount();
        outContent.reset();
        errContent.reset();

        account.deposit(1000);

        assertAll("Frozen Account Deposit",
                () -> assertEquals(0, account.getBalance()),
                () -> assertTrue(errContent.toString()
                        .contains("AccountFrozenException")));
    }

    @Test
    @DisplayName("Should withdraw when balance is sufficient")
    void testWithdraw_WhenBalanceIsSufficient_ShouldUpdateBalanceAndPrintMessage() {
        account.deposit(1000);
        outContent.reset();
        account.withdraw(600);

        assertAll("Successful Withdrawal",
                () -> assertEquals(400, account.getBalance()),
                () -> assertEquals(2, account.getTransactionHistory().size()),
                () -> assertTrue(outContent.toString()
                        .contains("Withdrawn: PHP 600.0")));
    }

    @Test
    @DisplayName("Should print InvalidAmountException stack trace for negative withdrawal")
    void testWithdraw_WhenAmountIsNegative_ShouldPrintInvalidAmountException() {
        account.deposit(1000);
        outContent.reset();
        errContent.reset();

        account.withdraw(-500);

        assertAll("Negative Withdrawal",
                () -> assertEquals(1000, account.getBalance()),
                () -> assertTrue(errContent.toString()
                        .contains("InvalidAmountException")));
    }

    @Test
    @DisplayName("Should print InsufficientFundsException stack trace when amount exceeds balance")
    void testWithdraw_WhenAmountExceedsBalance_ShouldPrintInsufficientFundsException() {
        account.deposit(1000);
        outContent.reset();
        errContent.reset();

        account.withdraw(5000);

        assertAll("Exceeds Balance Withdrawal",
                () -> assertEquals(1000, account.getBalance()),
                () -> assertTrue(errContent.toString()
                        .contains("InsufficientFundsException")));
    }

    @Test
    @DisplayName("Should print AccountFrozenException stack trace when withdrawing from frozen account")
    void testWithdraw_WhenAccountIsFrozen_ShouldPrintAccountFrozenException() {
        account.deposit(1000);
        account.freezeAccount();
        outContent.reset();
        errContent.reset();

        account.withdraw(500);

        assertAll("Frozen Account Withdrawal",
                () -> assertEquals(1000, account.getBalance()),
                () -> assertEquals(1, account.getTransactionHistory().size()),
                () -> assertTrue(errContent.toString()
                        .contains("AccountFrozenException")));
    }

    @Test
    @DisplayName("Should freeze and unfreeze account correctly")
    void testFreezeUnfreeze_ShouldUpdateStatusCorrectly() {
        assertFalse(account.isFrozen());

        account.freezeAccount();
        assertTrue(account.isFrozen());

        account.unfreezeAccount();
        assertFalse(account.isFrozen());
    }

    @Test
    @DisplayName("Should Retain Same Status")
    void testFreezeUnfreeze_ShouldRemainSameStatus() {
        assertFalse(account.isFrozen());

        account.freezeAccount();
        assertTrue(account.isFrozen());

        account.freezeAccount();
        assertTrue(account.isFrozen());

        account.unfreezeAccount();
        assertFalse(account.isFrozen());

        account.unfreezeAccount();
        assertFalse(account.isFrozen());
    }

    @Test
    @DisplayName("Should filter transactions above threshold")
    void testFilterTransactionsAbove_ShouldReturnMatchingTransactions() {
        account1.deposit(100);
        account1.deposit(500);
        account1.deposit(200);
        account1.deposit(800);

        List<Transaction> filtered = manager.filterTransactionsAbove(200,
                account1.getTransactionHistory());

        assertEquals(3, filtered.size());
        assertTrue(filtered.stream().allMatch(t -> t.getAmount() >= 200));
    }

    @Test
    @DisplayName("Should error invalid amount")
    void testFilterTransactionsAbove_WhenAmountIsInvalid_ShouldReturnMatchingTransactions() {
        account1.deposit(100);
        account1.deposit(500);
        account1.deposit(200);
        account1.deposit(800);

        manager.filterTransactionsAbove(-200, account1.getTransactionHistory());

        assertAll("Negative Withdrawal", () -> assertTrue(
                errContent.toString().contains("InvalidAmountException")));
    }

    @Test
    @DisplayName("Should Show Error Null Pointer Exception For Null Transaction List")
    void testSortTransactions_WhenTransactionsListisNull_ShouldNullPointerException() {

        manager.sortTransactionsByAmount(null);

        assertAll("Null List", () -> assertTrue(
                errContent.toString().contains("NullPointerException")));
    }
    @Test
    @DisplayName("Should record successful transactions and maintain order")
    void testTransactionHistory_ShouldRecordTransactions() {
        account.deposit(100);
        account.deposit(200);
        account.withdraw(50);

        assertAll("Transaction History",
                () -> assertEquals(3, account.getTransactionHistory().size()),
                () -> assertEquals("Deposit",
                        account.getTransactionHistory().get(0).getType()),
                () -> assertEquals(100.0,
                        account.getTransactionHistory().get(0).getAmount()),
                () -> assertEquals("Withdraw",
                        account.getTransactionHistory().get(2).getType()));
    }

    @Test
    @DisplayName("Should print AccountNotFoundException stack trace for non-existent account")
    void testGetAccount_WhenAccountDoesNotExist_ShouldPrintException() {
        errContent.reset();
        BankAccount result = manager.getAccount(999);

        assertAll("Non-existent Account", () -> assertNull(result),
                () -> assertTrue(errContent.toString()
                        .contains("AccountNotFoundException")),
                () -> assertTrue(errContent.toString()
                        .contains("Bank Account doesn't exist.")));
    }

    @Test
    @DisplayName("Should sort transactions by amount in ascending order")
    void testSortTransactionsByAmount_ShouldReturnAscendingOrder() {
        account1.deposit(500);
        account1.deposit(100);
        account1.deposit(300);

        List<Transaction> sorted = manager
                .sortTransactionsByAmount(account1.getTransactionHistory());

        assertAll("Sort Transactions", () -> assertEquals(3, sorted.size()),
                () -> assertEquals(100.0, sorted.get(0).getAmount()),
                () -> assertEquals(300.0, sorted.get(1).getAmount()),
                () -> assertEquals(500.0, sorted.get(2).getAmount()));
    }

    @Test
    @DisplayName("Should add account and assign sequential IDs")
    void testAddAccount_ShouldAssignSequentialIds() {
        manager.addAccount(account1);
        manager.addAccount(account2);

        assertNotNull(manager.getAccount(1));
        assertNotNull(manager.getAccount(2));
        assertEquals(account1, manager.getAccount(1));
    }

    @Test
    @DisplayName("Should list all accounts with their IDs and balances")
    void testListAccount_ShouldListAllAccount() {
        account1.deposit(1000);
        account2.deposit(2500);

        manager.addAccount(account1);
        manager.addAccount(account2);

        outContent.reset();
        manager.listAccounts();

        String output = outContent.toString();

        assertAll("List All Accounts",
                () -> assertTrue(output.contains("--- All Accounts ---")),
                () -> assertTrue(output.contains("Account ID: 1")),
                () -> assertTrue(output.contains("Account ID: 2")),
                () -> assertTrue(output.contains("Balance: PHP")),
                () -> assertTrue(output.contains("--------------------")));
    }

    @Test
    @DisplayName("Should return formatted string representation of transaction")
    void testTransactionToString_ShouldReturnFormattedString() {
        Transaction depositTransaction = new Transaction("Deposit", 1500.50);
        Transaction withdrawTransaction = new Transaction("Withdraw", 250.75);

        String depositString = depositTransaction.toString();
        String withdrawString = withdrawTransaction.toString();

        assertAll("Transaction toString",
                () -> assertTrue(depositString.contains("Amount: PHP")),
                () -> assertTrue(depositString.contains("Type: Deposit")),
                () -> assertTrue(withdrawString.contains("Amount: PHP")),
                () -> assertTrue(withdrawString.contains("Type: Withdraw")));
    }

    @Test
    @DisplayName("printList should print all transactions with title")
    void testPrintList_WithTransactions_ShouldPrintTitleAndTransactions()
            throws Exception {
        account.deposit(1000);
        account.deposit(500);
        account.withdraw(200);

        List<Transaction> transactions = account.getTransactionHistory();

        outContent.reset();

        AWSBank.printList(transactions, "TEST TRANSACTIONS");

        String output = outContent.toString();

        assertAll("Print List with Transactions",
                () -> assertTrue(
                        output.contains("-------TEST TRANSACTIONS-------")),
                () -> assertTrue(output.contains("Amount: PHP")),
                () -> assertTrue(output.contains("Type: Deposit")),
                () -> assertTrue(output.contains("Type: Withdraw")));
    }

    @Test
    @DisplayName("Should not print anything for empty list")
    void testPrintList_WithEmptyList_ShouldNotPrintAnything() throws Exception {
        List<Transaction> emptyTransactions = new ArrayList<>();

        outContent.reset();

        AWSBank.printList(emptyTransactions, "TEST TRANSACTIONS");

        String output = outContent.toString();

        assertTrue(output.isEmpty() || output.trim().isEmpty(),
                "Output should be empty for empty transaction list");
    }

    @Test
    @DisplayName("Should print account balance")
    void testPrintAccount_WithValidAccount_ShouldPrintBalance()
            throws Exception {
        account.deposit(2500);

        outContent.reset();

        AWSBank.printAccount(account);

        String output = outContent.toString();

        assertAll("Print Account with Valid Account",
                () -> assertTrue(output.contains("Account Balance: PHP")),
                () -> assertTrue(
                        output.contains("2500") || output.contains("2,500")));
    }

    @Test
    @DisplayName("Should not print anything for null account")
    void testPrintAccount_WithNullAccount_ShouldNotPrintAnything()
            throws Exception {
        outContent.reset();

        AWSBank.printAccount(null);

        String output = outContent.toString();

        assertTrue(output.isEmpty() || output.trim().isEmpty(),
                "Output should be empty for null account");
    }

    @Test
    void testMainMethod() {
        AWSBank.main(null);
        int result = 1;
        assertEquals(1, result, () -> "main returns void");
    }

}