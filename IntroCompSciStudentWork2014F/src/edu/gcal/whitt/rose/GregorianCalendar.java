package edu.gcal.whitt.rose;

import edu.jenks.dist.gcal.*;
/**
 * Write a description of class GregorianCalendar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GregorianCalendar extends AbstractGregorianCalendar
{ 
    public static void main(String[] args) {
        //System.out.println("Begin.");
        //int year = 2019;
        GregorianCalendar pc = new GregorianCalendar();
        String thing = pc.reportLeapYear(2020);
        System.out.println(thing);
    }
    public String reportLeapYear(int year) {
        if(year >= 1582){
            String high = year + " is greater than or equal to 1582.";
            if(year % 4 == 0){
                high = year + " may be a leap year.";
                if (year % 100 == 0 && year % 400 != 0){
                    high = year + " is not a leap year.";
                } else {
                    high = year + " is a leap year.";
                }
            } else if (year % 4 != 0) {
                high = year + " is not a leap year.";
            }
            return high;
        } else {
            String notHigh = year + " is not greater than or equal to 1582.";
            return notHigh;
        }
        //return "bjdsf";
    }
}
