package edu.gcal.tran.don;

import edu.jenks.dist.gcal.*;

public class GregorianCalendar extends AbstractGregorianCalendar
{
    public static void main (String[] args) {
        GregorianCalendar pc = new GregorianCalendar();
        String act = pc.reportLeapYear(2000);
        System.out.println(act);
    }
    private int random(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;  
    }
    public String reportLeapYear(int year) {
        if(year < 1582) {
            return year + " is not greater than or equal to 1582.";
        }
        if(year % 4 == 0) {
            if(year % 100 != 0) {
                return year + " is a leap year.";
            }
            if(year % 100 == 0) {
                if(year % 400 == 0) {
                    return year + " is a leap year.";
                } else {
                    return year + " is not a leap year.";
                }
            }
        } else {
            return year + " is not a leap year.";
        }
        return "is a year";
    }
}
