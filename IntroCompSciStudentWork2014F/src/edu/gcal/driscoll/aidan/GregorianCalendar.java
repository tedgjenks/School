package edu.gcal.driscoll.aidan;

import edu.jenks.dist.gcal.*;

/**
 * Write a description of class GregorianCalendar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GregorianCalendar extends AbstractGregorianCalendar
{
    public static void main(String[] args){
        System.out.println("Begin");
        GregorianCalendar pc = new GregorianCalendar();
        pc.reportLeapYear(2000);
        System.out.println("End Without Error");
    }
    
    public String reportLeapYear(int year){
        if( year >= 1582){
            if(year %4==0){
               if(year %100 == 0 && year %400 != 0){
                   return year + " is not a leap year.";
                }else{
                   return year + " is a leap year."; 
                }
           }else{
               return year + " is not a leap year.";
           }
        }else{
            return year + " is not greater than or equal to 1582.";
        }
    }
}
