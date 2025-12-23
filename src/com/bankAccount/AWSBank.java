package com.bankAccount;

public final class AWSBank {

    private AWSBank() {
        super();
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        SavingsAccount s = new SavingsAccount("Joshua");
        final int num1 = 1000;
        final int num2 = 0;
        final int num3 = -500;
        final int num4 = 500;
        final int num5 = 1500;
        final int num6 = -100;
        final int num7 = 11500;
        final int num8 = 500;
        final int num9 = 100;

        s.deposit(num1);
        s.deposit(num2);
        s.deposit(num3);
        s.withdraw(num4);
        s.withdraw(num5);
        s.withdraw(num6);
        s.freezeAccount();
        s.deposit(num7);
        s.withdraw(num8);
        s.unfreezeAccount();
        s.withdraw(num9);
        s.isFrozen();
        s.getBalance();
    }
}
