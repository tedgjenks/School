package edu.gcal.britt.emory;
import edu.jenks.dist.gcal.*;

/**
 * Write a description of class GregorianCalendar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GregorianCalendar extends AbstractGregorianCalendar{
    public String reportLeapYear(int year){
        boolean lYear = isLeapYear(year);
        boolean y1582 = isYear1582(year);
        if(y1582 == true){
            return year + " is not greater than or equal to 1582.";
        }
        if(lYear == true){
            return year + " is a leap year.";
        }
        return year + " is not a leap year.";
    }
    private boolean isLeapYear(int year){
        if(year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0){
                    return true;
                }
                return false;
            }
            return true; 
        }
        return false;
    }
    private boolean isYear1582(int year){
        if(year < 1582){
            return true;
        }
        return false;
    }
}
