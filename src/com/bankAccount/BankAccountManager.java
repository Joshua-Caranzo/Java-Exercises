package com.bankAccount;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankAccountManager {
    /**
     * Global instance of HelperClass.
     */
    private static final HelperClass HELPER_CLASS = new HelperClass();

    /**
     * Collection of bank accounts indexed by ID.
     */
    private Map<Integer, BankAccount> accounts;

    /**
     * Counter for assigning unique account numbers.
     */
    private int nextAccountId;

    /**
     * This is the default constructor. Sets nextAccountId to 1 and accounts to
     * HashMap.
     */
    public BankAccountManager() {
        nextAccountId = 1;
        this.accounts = new HashMap<>();
    }

    /**
     * This method adds an element to accounts.
     *
     * @param account to be added
     */
    public void addAccount(final BankAccount account) {
        accounts.put(nextAccountId, account);
        System.out.println("Account Added Successfully!");
        System.out.println("Account Id: " + nextAccountId + " || Balance: PHP "
                + HELPER_CLASS.formatBalance(account.getBalance()));
        nextAccountId++;
    }

    /**
     * Retrieves an account by its unique ID.
     *
     * @param accountId The ID of the account to find.
     * @return The requested BankAccount.
     * @throws AccountNotFoundException If no account matches the ID.
     */
    public BankAccount getAccount(final int accountId) {
        BankAccount a = accounts.get(accountId);
        try {
            checkAccountIsNull(a);
        } catch (BankingException e) {
            e.printStackTrace();
        }
        return a;
    }

    private void checkAccountIsNull(final BankAccount a)
            throws AccountNotFoundException {
        if (a == null) {
            throw new AccountNotFoundException();
        }
    }

    /**
     * Lists all accounts.
     */
    public void listAccounts() {
        System.out.println("--- All Accounts ---");
        accounts.forEach((id, account) -> {
            System.out.println("Account ID: " + id + " || Balance: PHP "
                    + HELPER_CLASS.formatBalance(account.getBalance()));
        });
        System.out.println("--------------------");
    }

    /**
     * Filters the transaction list for entries at or above a specific amount.
     *
     * @param amount The minimum threshold to filter by.
     * @param txList The list of transactions to filter.
     * @return A new list containing only transactions matching the criteria.
     */
    public List<Transaction> filterTransactionsAbove(final double amount,
            final List<Transaction> txList) {
        try {
            isValidDeposit(amount);
            return txList.stream().filter(a -> a.getAmount() >= amount)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    /**
     * Sorts a list of transactions in ascending order by their amount.
     *
     * @param txList The list of transactions to sort.
     * @return A new list sorted by amount.
     */
    public List<Transaction> sortTransactionsByAmount(
            final List<Transaction> txList) {
        try {
            return txList.stream()
                    .sorted(Comparator.comparingDouble(Transaction::getAmount))
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private void isValidDeposit(final double amount)
            throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
    }
}
