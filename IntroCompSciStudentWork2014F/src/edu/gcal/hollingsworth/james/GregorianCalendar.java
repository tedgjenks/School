package edu.gcal.hollingsworth.james;
import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar
{
    public static void main(String args[]) {
        GregorianCalendar gcal = new GregorianCalendar();
        System.out.println(gcal.reportLeapYear(2003));
        System.out.println(gcal.reportLeapYear(2004));
        System.out.println(gcal.reportLeapYear(1900));
        System.out.println(gcal.reportLeapYear(2000));
        System.out.println(gcal.reportLeapYear(1400));
    }
    
    public GregorianCalendar() {}
    
    public String reportLeapYear(int year) {
        if(year < 1582)
            return Integer.toString(year) + " is not greater than or equal to 1582.";
        if((year % 100 == 0 && year % 400 != 0) || year % 4 != 0)
            return Integer.toString(year) + " is not a leap year.";
        return Integer.toString(year) + " is a leap year.";
    }
}
