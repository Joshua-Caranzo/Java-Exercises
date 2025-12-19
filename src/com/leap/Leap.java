package com.leap;

public final class Leap {

    private Leap() {
        super();
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        
        int[] arr = {10,0};
        int i = 0;
        try {
            int answer = arr[i] / arr[i + 1];
        }catch (ArithmeticException ae) {
            System.out.print("Invalid divisor.");
        }
        catch (Exception e) {
            System.out.print("Unknown issues. ");
        } 
            
        System.out.println("Good /* and " + " greeting */ day!");
        final int num1 = 2000;
        final int num2 = 1900;
        final int num3 = 2016;
        final int num4 = 2017;

        LeapYearChecker year1 = new LeapYearChecker(num1);
        System.out.println(year1.isLeapYear());

        LeapYearChecker year2 = new LeapYearChecker(num2);
        System.out.println(year2.isLeapYear());

        LeapYearChecker year3 = new LeapYearChecker(num3);
        System.out.println(year3.isLeapYear());

        LeapYearChecker year4 = new LeapYearChecker(num4);
        System.out.println(year4.isLeapYear());

    }
}

class LeapYearChecker {

    /**
     * @param year that represents year;
     */
    private int year;

    LeapYearChecker(final int year1) {
        year = year1;
    }

    public boolean isLeapYear() {
        final int firstCheck = 100;
        final int secondCheck = 400;
        final int lastCheck = 4;

        if (year % firstCheck == 0) {
            if (year % secondCheck == 0) {
                return true;
            }
            return false;
        } else if (year % lastCheck == 0) {
            return true;
        }
        return false;
    }
}
