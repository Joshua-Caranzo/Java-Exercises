package com.bankAccount;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBankAccount implements BankAccount {
    /**
     * Global instance of HelperClass.
     */
    private static final HelperClass HELPER_CLASS = new HelperClass();

    /**
     * List that contains transactionHistory.
     */
    private List<Transaction> transactionHistory;

    /**
     * Current account balance.
     */
    private double balance;

    /**
     * Account frozen status.
     */
    private boolean isFrozen;

    /**
     * This is the default constructor. Sets balance to 0 and isFrozen to false
     * and transactionHistory as ArrayList.
     */
    public AbstractBankAccount() {
        balance = 0;
        isFrozen = false;
        transactionHistory = new ArrayList<Transaction>();
    }

    /**
     * * This method is for depositing a certain amount.
     *
     * @param amount to be deposited
     */
    public void deposit(final double amount) {
        try {
            if (isValidDeposit(amount)) {
                balance += amount;
                Transaction t = new Transaction("Deposit", amount);
                transactionHistory.add(t);
                System.out.println(
                        "Deposited: PHP " + HELPER_CLASS.formatBalance(amount));
            }
        } catch (BankingException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is for withdrawing a certain amount.
     *
     * @param amount to be withdrawn
     */
    public void withdraw(final double amount) {
        try {
            if (isValidWithdraw(amount)) {
                balance -= amount;
                Transaction t = new Transaction("Withdraw", amount);
                transactionHistory.add(t);
                System.out.println(
                        "Withdrawn: PHP " + HELPER_CLASS.formatBalance(amount));
            }
        } catch (BankingException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method retrieves the current balance.
     *
     * @return bank amount balance
     */
    public double getBalance() {
        System.out
                .println("Balance: PHP " + HELPER_CLASS.formatBalance(balance));
        return balance;
    }

    /**
     * This method checks frozen status.
     *
     * @return frozen status
     */
    public boolean isFrozen() {
        if (isFrozen) {
            System.out.println("Account is frozen");
        } else {
            System.out.println("Account is not frozen.");
        }
        return isFrozen;
    }

    /**
     * This method sets isFrozen to true.
     */
    public void freezeAccount() {
        if (!isFrozen) {
            isFrozen = true;
            System.out.println("Account has been frozen.");
        } else {
            System.out.println("Account is already frozen.");
        }
    }

    /**
     * This method sets isFrozen to false.
     */
    public void unfreezeAccount() {
        if (isFrozen) {
            isFrozen = false;
            System.out.println("Account has been unfrozen.");
        } else {
            System.out.println("Account is not frozen.");
        }
    }

    /**
     * This method retrieves transactionHistory.
     *
     * @return transaction history
     */
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    private boolean isValidDeposit(final double amount)
            throws BankingException {
        if (amount > 0) {
            if (!isFrozen) {
                return true;
            } else {
                throw new AccountFrozenException();
            }
        } else {
            throw new InvalidAmountException(amount);
        }
    }

    private boolean isValidWithdraw(final double amount)
            throws BankingException {
        if (amount > 0) {
            if (!isFrozen) {
                if (amount < balance) {
                    return true;
                } else {
                    throw new InsufficientFundsException();
                }
            } else {
                throw new AccountFrozenException();
            }
        } else {
            throw new InvalidAmountException(amount);
        }
    }
}
