package com.bankAccount;

public interface BankAccount {

    /**
     * @param amount
     */
    void deposit(double amount);

    /**
     * @param amount
     */
    void withdraw(double amount);

    /**
     * @return double balance
     */
    double getBalance();

    /**
     * @return boolean isFrozen
     */
    boolean isFrozen();
}
