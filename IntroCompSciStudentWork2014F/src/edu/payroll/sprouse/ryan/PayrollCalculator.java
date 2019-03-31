 package edu.payroll.sprouse.ryan;

import edu.jenks.dist.payroll.*;

/**
 ***IMPORTANT*** You cannot use java.lang.String  use String instead
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PayrollCalculator extends AbstractPayrollCalculator
{
    private static boolean doublesEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < (1.0 / 3600 / 10);
    }
    public static void main(String[] args) {
        System.out.println("Begin");
        PayrollCalculator pc = new PayrollCalculator();
        double act = pc.convertToHours(5, 45, 15);
        double exp = 5.7541666;
        assert doublesEqual(act, exp) : "Convert to hours exp: " + exp + "; act: " + act;
        System.out.println("End without error");
    }
    public double convertToHours(int hours, int minutes, int seconds) {
        double minutesToHours = minutes / 60;
        double secondsToHours = seconds / 3600;
        double hoursWorked = hours + minutesToHours + secondsToHours;
        System.out.println(hours + minutesToHours + secondsToHours);
        return hoursWorked;
    }
    public double calculatePay(double hoursWorked) {
        double pay = 0;
        //pay is being measured in cents. this value will be converted into dollars later to make formatting the paycheck simpler.
        double hours = hoursWorked;
        double overtime = (hours - 40);
        if(hours > 40){
            pay = 1050 * hours * (overtime * 2);
        }else{
            pay = 1050 * hours;
        }
        return pay;
    }
    public String formatPay(double pay){
        int dollars = 0;
        int cents = 0;
        String result = "";
        while(pay > 100) {
            dollars++;
            pay -= 100;
        }
        // make sure this is supposed to be > and not >=
        while(pay >= 1) {
            cents++;
            pay--;
        }
        // most of this is probably wrong, figure out whether or not you need quotation marks around the variables and/or decimal point
        result += '$';
        result += dollars;
        result += '.';
        if(cents < 10){
            result += '0';
            result += cents;
        }else{
            result += cents;
        }
        return result;
    }
}
