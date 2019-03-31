package edu.gcal.sweezy.kenneth;

import edu.jenks.dist.gcal.*;

public class GregorianCalendar extends AbstractGregorianCalendar {
    public static void main(String[] args) {
        GregorianCalendar instance = new GregorianCalendar();
        int testYear = 1900;
        System.out.println("Testing Year " + testYear + "; " + instance.reportLeapYear(testYear));
    }
    public String reportLeapYear(int year) {
        boolean isLeap = false;
        if(year < 1582) {
            return year + " is not greater than or equal to 1582.";
        }
        if(year % 100 == 0) {
            if(year % 400 == 0){
                isLeap = true;
            }
        } else {
            if(year % 4 == 0) {
                isLeap = true;
            } else {
                isLeap = false;
            }
        }
        if(isLeap) {
            return year + " is a leap year.";
        } else {
            return year + " is not a leap year.";
        }
     }
}
