package edu.gcal.page.javin;

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
        GregorianCalendar gc = new GregorianCalendar();
        System.out.println(gc.reportLeapYear(2000));
        System.out.println(gc.reportLeapYear(1700));
        System.out.println(gc.reportLeapYear(1900));
        System.out.println("End without error");
    }
    
    public String reportLeapYear(int year){
        if(year >= 1582){
            if(year % 100 == 0){
                if(year %  400 == 0){
                    return year + " is a leap year.";
                }    
            }else {
                if(year % 4 == 0){
                    return year + " is a leap year.";
                }    
            }    
        }else {
            return year + " is not greater than or equal to 1582.";
        }    
        return year + " is not a leap year.";
    }   
    
    private int random(int min, int max){
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
}
