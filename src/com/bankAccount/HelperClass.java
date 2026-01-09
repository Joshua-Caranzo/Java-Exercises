package com.bankAccount;

import java.text.DecimalFormat;

public class HelperClass {
    /**
     * Instance of a Decimal Format with format "0.00".
     */
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(
            "0.00");

    /**
     * Helper function that formats a double number.
     *
     * @param amount to be formatted
     * @return formatted amount
     */
    public String formatBalance(final double amount) {
        return DECIMAL_FORMAT.format(amount);
    }
}
