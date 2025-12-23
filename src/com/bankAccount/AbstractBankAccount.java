package com.bankAccount;

public abstract class AbstractBankAccount implements BankAccount {

    /**
     * @param balance
     */
    private double balance;

    /**
     * @param balance
     */
    private boolean isFrozen;

    /**
     * Sets balance to 0 and isFrozen to false.
     */
    public AbstractBankAccount() {
        balance = 0;
        isFrozen = false;
    }

    /**
     * @param amount
     */
    public void deposit(final double amount) {
        if (isValidDeposit(amount)) {
            balance += amount;
            System.out.println("Deposited: PHP " + amount);
        }
    }

    /**
     * @param amount
     */
    public void withdraw(final double amount) {
        if (isValidWithdraw(amount)) {
            balance -= amount;
            System.out.println("Withdrawn: PHP " + amount);
        }
    }

    /**
     * @return balance
     */
    public double getBalance() {
        System.out.println("Balance: PHP " + balance);
        return balance;
    }

    /**
     * @return isFrozen
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
     *
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
    *
    */
    public void unfreezeAccount() {
        if (isFrozen) {
            isFrozen = false;
            System.out.println("Account has been unfrozen.");
        } else {
            System.out.println("Account is not frozen.");
        }
    }

    private boolean isValidDeposit(final double amount) {
        if (amount > 0) {
            if (!isFrozen) {
                return true;
            }
            System.out.println("Account is frozen.");
            System.out.println("Cannot deposit.");
            return false;
        }
        System.out.println("The deposit amount must be positive");
        return false;
    }

    private boolean isValidWithdraw(final double amount) {
        if (amount > 0) {
            if (!isFrozen) {
                if (amount < balance) {
                    return true;
                }
                System.out.println("Insufficient Balance");
                return false;
            }
            System.out.println("Account is frozen.");
            System.out.println("Cannot withdraw.");
            return false;
        }
        System.out.println("The withdrawn amount must be positive");
        return false;
    }
}
