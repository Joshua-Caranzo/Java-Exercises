package com.bankAccount;

public class Transaction {

    /**
     * Global instance of HelperClass.
     */
    private static final HelperClass HELPER_CLASS = new HelperClass();

    /**
     * Transaction type whether deposit or withdraw.
     */
    private String type;

    /**
     * Amount of the transaction.
     */
    private double amount;

    /**
     * Constructor that sets type and amount.
     *
     * @param t represents type
     * @param a represents amount
     */
    public Transaction(final String t, final double a) {
        type = t;
        amount = a;
    }

    /**
     * Retrieves the category or classification of this transaction.
     *
     * @return the type of the transaction.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the numerical value associated with this entry.
     *
     * @return amount of the transaction.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns a string representation of the object, including the amount and
     * type.
     *
     * @return a formatted string for display or logging.
     */
    public String toString() {
        return "Amount: PHP " + HELPER_CLASS.formatBalance(amount)
                + " || Type: " + type;
    }
}
