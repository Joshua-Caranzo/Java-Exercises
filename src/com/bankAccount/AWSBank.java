package com.bankAccount;

import java.util.List;

public final class AWSBank {
    /**
     * Global instance of HelperClass.
     */
    private static final HelperClass HELPER_CLASS = new HelperClass();

    private AWSBank() {
        super();
    }

    /**
     * This functions is a helper function to print the transactions.
     *
     * @param t     for transactions
     * @param title for displaying what this transaction all about.
     */
    public static void printList(final List<Transaction> t,
            final String title) {
        if (t.isEmpty()) {
            return;
        }
        System.out.println("-------" + title + "-------");
        t.forEach(a -> System.out.println(a.toString()));
    }

    /**
     * This functions is a helper function to print the balance of an account.
     *
     * @param b for bank account
     */
    public static void printAccount(final BankAccount b) {
        if (b == null) {
            return;
        }
        System.out.println("Account Balance: PHP "
                + HELPER_CLASS.formatBalance(b.getBalance()));
    }

    /**
     * Main function that demonstrates bank account operations including
     * deposits, withdrawals, account freezing, and transaction filtering.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        BankAccountManager bm = new BankAccountManager();
        SavingsAccount s = new SavingsAccount("Joshua");

        final int num1 = 1000;
        final int num2 = 500;
        final int num3 = 1500;
        final int num4 = 100;

        bm.addAccount(s);

        s.deposit(num1);
        printList(s.getTransactionHistory(), "TRANSACTION HISTORY");

        s.deposit(0);
        s.deposit(-num2);
        s.withdraw(num2);
        s.withdraw(num3);
        s.withdraw(-num4);
        s.freezeAccount();
        s.deposit(num2);
        s.withdraw(num2);
        s.unfreezeAccount();
        s.withdraw(num4);
        s.getBalance();
        bm.listAccounts();

        List<Transaction> ts = s.getTransactionHistory();
        printList(bm.filterTransactionsAbove(num2, ts),
                "FILTERED TRANSACTIONS");
        printList(bm.filterTransactionsAbove(-num2, ts),
                "FILTERED TRANSACTIONS");
        printList(bm.sortTransactionsByAmount(ts), "SORTED TRANSACTIONS");
        printAccount(bm.getAccount(1));
        printAccount(bm.getAccount(2));
    }
}
