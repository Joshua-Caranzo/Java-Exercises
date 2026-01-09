package com.bankAccount;

public class SavingsAccount extends AbstractBankAccount {

    /**
     * Name of the account owner.
     */
    private final String ownerName;

    /**
     * Constructor that sets the ownerName.
     *
     * @param name to be reference for ownerName
     */
    public SavingsAccount(final String name) {
        ownerName = name;
    }

    /**
     * This functions retrieves owner name.
     *
     * @return name of the owner
     */
    public String getOwnerName() {
        return ownerName;
    }
}
