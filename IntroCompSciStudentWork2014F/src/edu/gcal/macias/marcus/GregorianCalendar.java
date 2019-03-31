package edu.gcal.macias.marcus;
import edu.jenks.dist.gcal.*;

public class GregorianCalendar extends AbstractGregorianCalendar
{
    public static void main(String[] args){
        System.out.println("Begin");
        GregorianCalendar gCal = new GregorianCalendar();
        String act = gCal.reportLeapYear(1600);
        String exp = "year is a leap year.";
        //assert act.equals(exp): "Youre wrong";
        System.out.println(gCal.reportLeapYear(2003));
        System.out.println("End without Error");
    }

    public String reportLeapYear(int year){
       
        if(year < 1582){
            return year + " is not greater than or equal to 1582.";
        }
        if(year % 4 == 0){
            if(year % 100 == 0 && year % 400 == 0){
                return year + " is a leap year.";
            }else if(year % 100 == 0 & year % 400 != 0){
                return year + " is not a leap year.";
            }
            return year + " is a leap year.";
        }
        if(year % 4 != 0){
            return year + " is not a leap year.";
        }
        if(year % 4 > 0){
            return year + " is a leap year.";
        }
        return null;
    }
}
