package edu.gcal.crosby.amber;

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
        System.out.println("End without error");
    }
    public String reportLeapYear(int year){
        if (year<1582){
            return year + " is not greater than or equal to 1582.";
        }else{
            if (year % 400 != 0 & year % 100 == 0){
                return year + " is not a leap year.";
            }else{
                if (year % 4 == 0){
                    return year + " is a leap year.";
                }else{
                    return year + " is not a leap year.";
                }
            }
        }
        
    }
}
