package edu.gcal.sprouse.ryan;

import edu.jenks.dist.gcal.*;

/**
 * Write a description of class GregorianCalendar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GregorianCalendar extends AbstractGregorianCalendar
{
    public String reportLeapYear(int year){
        
        String reply = "";
        if(year < 1582){
             reply += year;
             reply += " is not greater than or equal to 1582.";
        }else{
            if((year % 4) == 0){
                if((year % 100) == 0){
                    if((year % 400) == 0){
                        reply += year;
                        reply += " is a leap year.";
                    }else{
                        reply += year;
                        reply += " is not a leap year.";
                    }
                }else{
                    reply += year;
                    reply += " is a leap year.";
                }
            }else{
                reply += year;
                reply += " is not a leap year.";
            }
        }
        
        return reply;
    }
}
