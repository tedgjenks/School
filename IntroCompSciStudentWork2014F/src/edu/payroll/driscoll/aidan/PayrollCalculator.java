package edu.payroll.driscoll.aidan;

import edu.jenks.dist.payroll.*;

/**
 * Write a description of class PayrollCalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PayrollCalculator extends AbstractPayrollCalculator
{
    private static boolean doublesEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < (1.0/3600/10);
    }
    public static void main(String[] args){
        System.out.println("Begin");
        PayrollCalculator pc = new PayrollCalculator();
        double act = pc.convertToHours(5, 45, 15);
        double exp = 5.7541666;
        pc.calculatePay(exp);
        System.out.println(pc.calculatePay(exp));
        pc.formatPay(pc.calculatePay(exp));
       assert doublesEqual(act, exp) : "Convert to hours expected;" + exp + ";act;" + act;
       System.out.println("End Without Error");
   }

   public double convertToHours(int hours, int minutes, int seconds){
       double finalHours = hours; 
       double minute = minutes / 60.0;
       double second = seconds / 3600.0;
       finalHours = finalHours + minute + second;
       return finalHours;
   }

   public double calculatePay(double hoursWorked){
       double extra = 0;
       double pay = 0;
       if(hoursWorked > 40){
           pay = 40 * 10.50;
           hoursWorked = hoursWorked - 40;
           extra = hoursWorked * 15.75;
       }else{
          pay = hoursWorked * 10.50; 
       }
       pay = pay + extra;
       return pay;
   }
   
   public String formatPay(double pay){
       pay *= 100;
       pay = Math.round(pay);
       pay/= 100;
       String sPay = String.valueOf(pay);
       int decimal = sPay.indexOf('.');
       int dot = sPay.length() - 3;
       if(dot != decimal){
           sPay += 0;
       }
       System.out.println("$" + sPay); 
       return "$" + sPay;
    }
}

