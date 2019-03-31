package edu.gcal.savelyev.denis;

import edu.jenks.dist.gcal.*;

public class GregorianCalendar extends AbstractGregorianCalendar{
    public static void main(String[] args){
        GregorianCalendar instance = new GregorianCalendar();
        String answer = instance.reportLeapYear(2003);
        String answer1 = instance.reportLeapYear(2004);
        String answer2 = instance.reportLeapYear(1900);
        String answer3 = instance.reportLeapYear(2000);
        String answer4 = instance.reportLeapYear(1400);
        System.out.println(answer);
        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);
        System.out.println(answer4);
    }
    public String reportLeapYear(int yearNum){
        boolean isLeap = false;
        if (yearNum < 1582){
            return yearNum + " is not greater than or equal to 1582.";
        }
        if ((yearNum % 4 == 0) && (yearNum % 100 != 0) || (yearNum % 400 == 0)){
            isLeap = true;
        } else {
            isLeap = false;
        }
        if (isLeap == true){
            return yearNum + " is a leap year.";
        }
        return yearNum + " is not a leap year.";
    }
}
