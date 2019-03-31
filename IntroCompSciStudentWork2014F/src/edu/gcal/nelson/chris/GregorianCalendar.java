/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gcal.nelson.chris;
import edu.jenks.dist.gcal.*;
/**
 *
 * @author chris
 */
public class GregorianCalendar extends AbstractGregorianCalendar{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GregorianCalendar cal = new GregorianCalendar();
        System.out.println(cal.reportLeapYear(1700));
    }
    public String reportLeapYear(int year){
        if(year%4==0 && year>=1582){
            if(year%100 == 0 && year%400!=0){
                return year + " is not a leap year.";
            }
            return year + " is a leap year.";
        }else{
            if(year<1582){
                return year + " is not greater than or equal to 1582.";
            }else{
                return year + " is not a leap year.";
            }
        }
        
    }
    
}
