package com.bankAccount;

public class BankingException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Banking Exception which passes the message to the parent.
     *
     * @param message represents exception message
     */
    public BankingException(final String message) {
        super(message);
    }
}

class InsufficientFundsException extends BankingException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for InsufficientFundsException which passes the message to
     * the parent.
     */
    InsufficientFundsException() {
        super("Withdrawal failed. Your balance "
                + "is not enough to withdraw this amount.");
    }
}

class InvalidAmountException extends BankingException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for InvalidAmountException which passes the message to the
     * parent.
     *
     * @param amount for exception message;
     */
    InvalidAmountException(final double amount) {
        super("Transaction failed. Amount " + amount + " is invalid.");
    }
}

class AccountFrozenException extends BankingException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for AccountFrozenException which passes the message to the
     * parent.
     */
    AccountFrozenException() {
        super("Access denied. Account is currently frozen.");
    }
}

class AccountNotFoundException extends BankingException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for AccountNotFoundException.
     */
    AccountNotFoundException() {
        super("Bank Account doesn't exist.");
    }
}
