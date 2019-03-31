package edu.payroll.crosby.amber;

import edu.jenks.dist.payroll.*;
/**
 * Write a description of class PayrollCalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PayrollCalculator extends AbstractPayrollCalculator
{
    private static boolean doublesEqual(double d1, double d2){
        return Math.abs(d1-d2) < (1.0 / 3600 / 10);
    }
    public static void main(String[] args){
        System.out.println("Begin");
        PayrollCalculator pc = new PayrollCalculator();
        double act = pc.convertToHours(5, 45, 15);
        double exp = 5.7541666;
        assert doublesEqual(act, exp) : "Convert to hours exp: " + exp
        + ";act: " + act;
        pc.formatPay(123.5678);
        System.out.println("End without error");
    }
    public double convertToHours(int hours, int minutes, int seconds){
        double timesm = minutes + (seconds / 60.0);
        double timemh = hours + (timesm / 60.0);
        return timemh;
    }
    public double calculatePay(double hoursWorked){
        double pay = 0;
        if (hoursWorked <= 40){
            pay = hoursWorked * 10.50;
        } else{
            pay = 40 * 10.50;
            double overtimePay = (hoursWorked - 40) * 15.75 ;
            pay += overtimePay;
        }
        return pay;
    }
    public String formatPay(double pay){
        double cents = pay * 100;
        long rcents = Math.round(cents);
        double dollars = rcents/100.0;
        String sdollars = String.valueOf(dollars);
        int p = sdollars.indexOf('.');
        if(sdollars.length() - p <= 2){
            sdollars = "$"+ sdollars.substring(0,p+2);
            sdollars += 0;
        }else{
           sdollars = "$"+ sdollars.substring(0,p+3);
        }
        System.out.println(sdollars);
        return sdollars;
    }
}
