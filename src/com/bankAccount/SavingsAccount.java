package com.bankAccount;

public class SavingsAccount extends AbstractBankAccount {

    /**
     * @param ownerName
     */
    private final String ownerName;

    /**
     * @param name
     */
    public SavingsAccount(final String name) {
        ownerName = name;
        System.out.println("New Account Created");
        System.out.println("Account Name:" + ownerName);
    }

    /**
     * @return ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }
}
