package com.bankAccount;

import java.util.List;

public interface BankAccount {

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount The amount to be deposited.
     */
    void deposit(double amount);

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount The amount to be withdrawn.
     */
    void withdraw(double amount);

    /**
     * Returns the current account balance.
     *
     * @return The current balance.
     */
    double getBalance();

    /**
     * Returns the current frozen status.
     *
     * @return frozen status
     */
    boolean isFrozen();

    /**
     * Sets frozen accounts status to true.
     */
    void freezeAccount();

    /**
     * Sets frozen accounts status to false.
     */
    void unfreezeAccount();

    /**
     * Retrieves the transaction history.
     *
     * @return transaction history
     */
    List<Transaction> getTransactionHistory();
}
